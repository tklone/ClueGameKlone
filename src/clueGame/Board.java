package clueGame;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {

	private static BoardCell[][] grid;
	private int numRows;
	private int numCols;

	private String setupConfig;
	private String layoutConfig;

	private static Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();

	private Map<Character, Room> roomMap = new HashMap<>();

	private static ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> deckNoSolution = new ArrayList<Card>();
	private ArrayList<Card> weapons = new ArrayList<>();
	private ArrayList<Card> people = new ArrayList<>();
	private ArrayList<Card> rooms = new ArrayList<>();

	private ArrayList<Player> players = new ArrayList<Player>();

	private Solution theAnswer = new Solution();

	private Boolean roomCheck = false;
	private Boolean personCheck = false;
	private Boolean weaponCheck = false;
	private Boolean accusationCheck = false;

	// variable and methods used for singleton pattern
	private static Board theInstance = new Board();

	// constructor is private to ensure only one can be created
	private Board() {
		super();
	}

	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public static Card getCard(String string) {

		for (Card c : deck) {
			if (c.getName().equals(string)) {
				return c;
			}
		}
		// I don't like this \/
		return null;
	}

	public static Card getCard(BoardCell c) {
		String name = c.getLabel();
		Card card = getCard(name);
		return card;
	}

	public void setConfigFiles(String layoutConfig, String setupConfig) {
		this.layoutConfig = layoutConfig;
		this.setupConfig = setupConfig;
	}

	// initialize the board(since we are using singleton pattern
	public void initialize() {
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				calcAdjList(i, j);
			}
		}
	}

	// reading in the text file
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File setupFile = new File("data/" + setupConfig);
			Scanner setupReader = new Scanner(setupFile);

			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				Card card = new Card();
				if (wholeLine.charAt(0) != '/') {
					String[] arrOfStr = wholeLine.split(", ");
					String type = arrOfStr[0];
					String typeName = arrOfStr[1];
					if (type.equals("Room") || type.equals("Space")) {
						String current = arrOfStr[2];
						Character c = current.charAt(0);
						Room newRoom = new Room();
						newRoom.setName(typeName);
						newRoom.setChar(c);
						roomMap.put(c, newRoom);
						if (type.equals("Room")) {
							card.setCardType(CardType.ROOM);
							card.setName(typeName);
							rooms.add(card);
							deck.add(card);
						}
					} else if (type.equals("Weapon")) {
						card.setCardType(CardType.WEAPON);
						card.setName(typeName);
						weapons.add(card);
						deck.add(card);
					} else if (type.equals("Player")) {
						String name = typeName;
						String color = arrOfStr[2];
						String rowS = arrOfStr[3];
						String colS = arrOfStr[4];

						int row = Integer.parseInt(rowS);
						int col = Integer.parseInt(colS);

						// To make it so it's not hard coding, maybe do a
						// if (a number == 0) meaning like that's the first iteration maybe?
						if (typeName.equals("Santa Claus")) {
							Player human = new HumanPlayer(name, color, row, col);
							players.add(human);
						} else {
							Player computer = new ComputerPlayer(name, color, row, col);
							players.add(computer);
						}
						card.setCardType(CardType.PERSON);
						card.setName(typeName);
						people.add(card);
						deck.add(card);

					} else {
						throw new BadConfigFormatException();
					}
				}
			}
			setupReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file SETUP");
		}
	}

	// reading in the CSV file
	public void loadLayoutConfig() {
		try {
			File layoutFile = new File("data/" + layoutConfig);
			Scanner in = new Scanner(layoutFile);

			ArrayList<String> eachRow = new ArrayList<>();
			while (in.hasNext()) {
				String currentLine = in.nextLine();
				eachRow.add(currentLine);
			}

			String[] currentString;

			for (int i = 0; i < eachRow.size(); i++) {
				String rowStrings = eachRow.get(i);
				currentString = rowStrings.split(",");
				numCols = currentString.length;
			}
			numRows = eachRow.size();

			grid = new BoardCell[numRows][numCols];
			Room room = new Room();
			for (int i = 0; i < eachRow.size(); i++) {
				String rowStrings = eachRow.get(i);
				currentString = rowStrings.split(",");
				for (int j = 0; j < currentString.length; j++) {
					BoardCell newCell = new BoardCell();
					grid[i][j] = newCell;
					newCell.setLabel(currentString[j]);
					newCell.setCol(j);
					newCell.setRow(i);
					newCell.setInitial(newCell.getLabel());
					room = getRoom(newCell);
					if (newCell.isLabel()) {
						room.setLabelCell(newCell);
					}
					if (newCell.isRoomCenter()) {
						room.setCenterCell(newCell);
					}
					if (newCell.getLabel().length() > 1 && newCell.getLabel().charAt(1) != '*'
							&& newCell.getLabel().charAt(1) != '#' && !newCell.isDoorway()) {
						room.setHasSP(true);
						room.setSecretPassageCell(newCell);
					}
					if (currentString.length != numCols) {
						throw new BadConfigFormatException();
					}
					if (!roomMap.containsKey(newCell.getInitial())) {
						throw new BadConfigFormatException();
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't open file LAYOUT");
			System.out.println(e);

		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		return grid[i][j].getAdjListCell();
	}

	public Set<BoardCell> calcAdjList(int i, int j) {

		BoardCell theCell = new BoardCell();
		theCell = grid[i][j];
		BoardCell newCell = new BoardCell();
		Room room = new Room();

		if (theCell.isRoom()) {
			room = getRoom(theCell);

			if (room.getHasSP()) {
				newCell = room.getSecretPassageCell();
				char c = newCell.getSecretPassage();
				room = getRoom(c);
				theCell.addAdjacency(room.getCenterCell());
			}

		}
		// This is for seeing if cell is a walkway, then adding adj cells to adjList
		else if (theCell.isWalkway()) {
			if (i + 1 < numRows && grid[i + 1][j].getInitial() != 'X') {
				if (theCell.getDoorDirection() == DoorDirection.DOWN) {
					theCell.addAdjacency(getRoom(grid[i + 1][j]).getCenterCell());
					getRoom(grid[i + 1][j]).getCenterCell().addAdjacency(theCell);
				} else if (!grid[i + 1][j].isRoom()) {
					theCell.addAdjacency(grid[i + 1][j]);
				}
			}
			if (j + 1 < numCols && grid[i][j + 1].getInitial() != 'X') {
				if (theCell.getDoorDirection() == DoorDirection.RIGHT) {
					theCell.addAdjacency(getRoom(grid[i][j + 1]).getCenterCell());
					getRoom(grid[i][j + 1]).getCenterCell().addAdjacency(theCell);
				} else if (!grid[i][j + 1].isRoom()) {
					theCell.addAdjacency(grid[i][j + 1]);
				}
			}
			if (i - 1 >= 0 && grid[i - 1][j].getInitial() != 'X') {
				if (theCell.getDoorDirection() == DoorDirection.UP) {
					theCell.addAdjacency(getRoom(grid[i - 1][j]).getCenterCell());
					getRoom(grid[i - 1][j]).getCenterCell().addAdjacency(theCell);
				} else if (!grid[i - 1][j].isRoom()) {
					theCell.addAdjacency(grid[i - 1][j]);
				}
			}
			if (j - 1 >= 0 && grid[i][j - 1].getInitial() != 'X') {
				if (theCell.getDoorDirection() == DoorDirection.LEFT) {
					theCell.addAdjacency(getRoom(grid[i][j - 1]).getCenterCell());
					getRoom(grid[i][j - 1]).getCenterCell().addAdjacency(theCell);
				} else if (!grid[i][j - 1].isRoom()) {
					theCell.addAdjacency(grid[i][j - 1]);
				}
			}
		}

		return theCell.getAdjListCell();
	}

	public static Set<BoardCell> getTargets() {
		return targets;
	}

	// calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(BoardCell startCell, int pathlength) {
		visited.clear();
		targets.clear();
		visited.add(startCell);
		findAllTargets(startCell, pathlength);
	}

	public void findAllTargets(BoardCell thisCell, int numSteps) {

		// for each adjCell in adjacentCells
		for (BoardCell c : thisCell.getAdjListCell()) {

			// need this if numSteps >1
			if (c.getOccupied() && !c.isRoom()) {
				continue;
			}

			// if already in visited list, skip rest of code
			if (visited.contains(c)) {
				continue;
			}
			// else add adjCell to visited list
			visited.add(c);

			if (c.isRoomCenter()) {
				targets.add(c);
				continue;
			}

			// if numSteps==1, add adjCell to targets
			if (numSteps == 1) {
				if (c.getOccupied() == false) {
					targets.add(c);
				}
			}

			// else call findAllTargets with adjCell and numSteps-1
			else {
				findAllTargets(c, numSteps - 1);
			}
			// remove adjCell from visited
			visited.remove(c);
		}
	}

	public void getNearestDoor() {
		DoorDirection dir;
		BoardCell roomNearestCell = new BoardCell();
		Room room;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				BoardCell cell = grid[i][j];
				if (cell.isDoorway()) {
					dir = cell.getDoorDirection();
					switch (dir) {

					case LEFT:
						roomNearestCell = grid[i][j - 1];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
					case RIGHT:
						roomNearestCell = grid[i][j + 1];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
					case UP:
						roomNearestCell = grid[i - 1][j];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
					case DOWN:
						roomNearestCell = grid[i + 1][j];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
					default:
						break;
					}
				}
			}
		}
	}

	public void dealCards() {

		Card randomWeapon;
		Card randomPlayer;
		Card randomRoom;

		Random randW = new Random();
		int upperBoundW = weapons.size() - 1;
		int int_randomW = randW.nextInt(upperBoundW);

		Random randP = new Random();
		int upperBoundP = people.size() - 1;
		int int_radomP = randP.nextInt(upperBoundP);

		Random randR = new Random();
		int upperBoundR = rooms.size() - 1;
		int int_radomR = randR.nextInt(upperBoundR);

		randomWeapon = weapons.get(int_randomW);
		randomPlayer = people.get(int_radomP);
		randomRoom = rooms.get(int_radomR);

		theAnswer.setSolution(randomPlayer, randomRoom, randomWeapon);

		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i) != randomWeapon && deck.get(i) != randomPlayer && deck.get(i) != randomRoom) {
				deckNoSolution.add(deck.get(i));
			}
		}

		Collections.shuffle(deckNoSolution);
		int i = 0;
		while (!deckNoSolution.isEmpty()) {
			if (i == players.size()) {
				i = 0;
			}
			players.get(i).updateHand(deckNoSolution.get(0));
			deckNoSolution.remove(0);
			i++;
		}

	}

	public void setSolution(Card name, Card room, Card weapon) {
		theAnswer.setSolutionPerson(name);
		theAnswer.setSolutionRoom(room);
		theAnswer.setSolutionWeapon(weapon);
	}

	public Room getRoom(Character c) {
		Room room = new Room();
		room = roomMap.get(c);
		return room;
	}

	public static BoardCell getCell(int row, int col) {
		return grid[row][col];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numCols;
	}

	public Room getRoom(BoardCell cell) {
		Character c = cell.getInitial();
		Room room = new Room();
		room = roomMap.get(c);
		return room;
	}

	public static ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> getWeaponsCards() {
		return weapons;
	}

	public void addToWeaponsCards(Card c) {
		weapons.add(c);
	}

	public ArrayList<Card> getPeopleCards() {
		return people;
	}

	public void addToPeopleCards(Card c) {
		people.add(c);
	}

	public ArrayList<Card> getRoomCards() {
		return rooms;
	}

	public void addToRoomsCards(Card c) {
		rooms.add(c);
	}

	public ArrayList<Card> getDeckNoSoln() {
		return deckNoSolution;
	}

	public Solution getTheAnswer() {
		return theAnswer;
	}

	public Boolean hasSolutionRoom() {
		if (theAnswer.getSolutionRoom().getCardType().equals(CardType.ROOM)) {
			roomCheck = true;
		}
		return roomCheck;
	}

	public Boolean hasSolutionPerson() {
		if (theAnswer.getSolutionPerson().getCardType().equals(CardType.PERSON)) {
			personCheck = true;
		}
		return personCheck;
	}

	public Boolean hasSolutionWeapon() {
		if (theAnswer.getSolutionWeapon().getCardType().equals(CardType.WEAPON)) {
			weaponCheck = true;
		}
		return weaponCheck;
	}

	// Check an accusation [class Board] – returns true if accusation matches
	// theAnswer
	// (i.e. the player guessed who did it, where and with what).
	public Boolean checkAccusation(Solution playerGuess) {
		if (playerGuess.getSolutionPerson() == theAnswer.getSolutionPerson()
				&& playerGuess.getSolutionRoom() == theAnswer.getSolutionRoom()
				&& playerGuess.getSolutionWeapon() == theAnswer.getSolutionWeapon()) {
			accusationCheck = true;
		} else {
			accusationCheck = false;
		}
		return accusationCheck;
	}

	public Card handleSuggestion(Solution suggestion, Player accuser) {

		ArrayList<Card> disproveCards = new ArrayList<>();

		for (Player p : players) {
			if (!p.equals(accuser)) {
				Card disprove = p.disproveSuggestion(suggestion);
				if (disprove != null) {
					disproveCards.add(disprove);
					// We think this makes sense to have here but we aren't entirely sure??
					accuser.updateSeen(disprove, p.getColor());
					System.out.println("this is called");
				}
			} else {
				continue;
			}
		}

		if (disproveCards.size() > 0) {
			Random rand = new Random();
			int randInt = rand.nextInt(disproveCards.size() - 1);
			return disproveCards.get(randInt);

		}

		return null;
	}

	public Player getHumanPlayer() {
		return players.get(0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//For background, just make a rectangle
		
		int height = this.getHeight();
		int width = this.getWidth();
		int cellHeight = height / numRows;
		int cellWidth = width / numCols;
		
		for (BoardCell[] cell : grid) {
			for (BoardCell c : cell) {
				c.drawCell(g, cellHeight, cellWidth);			
			}
		}
	}

	// maybe put this in paintComponent
//	public void drawBoardCell() {
//		
//	}
	
	
}
