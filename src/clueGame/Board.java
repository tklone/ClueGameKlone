package clueGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel { // implements MouseListener

	private static BoardCell[][] grid;
	private int numRows;
	private int numCols;
	private int diceRoll;

	private String setupConfig;
	private String layoutConfig;
	private String suggestionWeapon, suggestionPlayer, suggestionRoom;
	private String accusationWeapon, accusationPlayer, accusationRoom;

	private static Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Set<BoardCell> highlightedCells = new HashSet<>();

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
	private Boolean diceRolled = false;
	private Boolean isFirstTurn = true;
	private Boolean gameOver = false;

	private boolean validClick = false;

	private int currentPlayerInt = 0;

	private GameControlPanel control;
	private SuggestionPanel suggestionPanel;
	private AccusationPanel accusation;
	private KnownCardsPanel knownCardsPanel;

	public void setSuggestionWeapon(String weapon) {
		suggestionWeapon = weapon;
	}

	public String getSuggestionWeapon() {
		return suggestionWeapon;
	}

	public void setSuggestionPlayer(String player) {
		this.suggestionPlayer = player;
	}

	public String getSuggestionPlayer() {
		return suggestionPlayer;
	}

	public void setSuggestionRoom(String room) {
		this.suggestionRoom = room;
	}

	public String getSuggestionRoom() {
		return suggestionRoom;
	}

	public void setAccusationWeapon(String weapon) {
		accusationWeapon = weapon;
	}

	public String getAccusationWeapon() {
		return accusationWeapon;
	}

	public void setAccusationPlayer(String player) {
		this.accusationPlayer = player;
	}

	public String getAccusationPlayer() {
		return accusationPlayer;
	}

	public void setAccusationRoom(String room) {
		this.accusationRoom = room;
	}

	public String getAccusationRoom() {
		return accusationRoom;
	}

	// need a reference to Game Control Panel
	public void setControl(GameControlPanel control) {
		this.control = control;
	}

	public void setSuggestion(SuggestionPanel suggestion) {
		this.suggestionPanel = suggestion;
	}

	public void setAccusation(AccusationPanel accusation) {
		this.accusation = accusation;
	}

	// variable and methods used for singleton pattern
	private static Board theInstance = new Board();

	// constructor is private to ensure only one can be created
	private Board() {
		super();
	}

	public int getHeight1() {
		return this.getHeight();
	}

	public int getWidth1() {
		return this.getWidth();
	}

	public void iterateCurrent() {
		if (currentPlayerInt < 5) {
			currentPlayerInt++;
		} else if (currentPlayerInt >= 5) {
			currentPlayerInt = 0;
		}
		// currentPlayer = players.get(currentPlayerInt);
	}

	class boardMouseListener implements MouseListener {
		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {

			int height = getHeight1();
			int width = getWidth1();
			int cellHeight = height / numRows;
			int cellWidth = width / numCols;

			BoardCell clickedCell = null;

			int xComp = (int) e.getPoint().getX() / cellWidth;
			int yComp = (int) e.getPoint().getY() / cellHeight;

			clickedCell = grid[yComp][xComp];

			Player currentPlayer = getCurrentPlayer();

			if (currentPlayer instanceof HumanPlayer) {
				if (targets.contains(clickedCell)) {
					currentPlayer.updatePosition(clickedCell);
					currentPlayer.setTurnFinished(true);
					targets.clear();
					repaint();
					if (clickedCell.isRoom()) {
						suggestionPanel = new SuggestionPanel();
						setSuggestion(suggestionPanel);

						Player player = getCurrentPlayer();
						BoardCell location = player.getLocation();

						setSuggestionRoom(getRoom(location).getName());
						suggestionPanel.setRoom(getRoom(location).getName());
						suggestionPanel.setVisible(true);
						setSuggestionPlayer(suggestionPanel.getPlayerChoice());
						setSuggestionWeapon(suggestionPanel.getWeaponChoice());

						handleSuggestion();
					}
				} else if (!targets.contains(clickedCell)) {
					JOptionPane.showMessageDialog(null, "This is not a valid move");
					currentPlayer.setTurnFinished(false);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}

	public void setDiceRoll(int n) {
		this.diceRoll = n;
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
		// boardListener = new boardClicked();
		try {
			loadSetupConfig();
			loadLayoutConfig();
			theInstance.addMouseListener(new boardMouseListener());
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
						if (typeName.equals("William Harvey")) {
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

	public ArrayList<Card> getWeapons() {
		return weapons;
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
				// c.setIsHighlighted(true);
				continue;
			}

			// if numSteps==1, add adjCell to targets
			if (numSteps == 1) {
				if (c.getOccupied() == false) {
					targets.add(c);
					// c.setIsHighlighted(true);
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


		theAnswer = new Solution(randomRoom, randomWeapon, randomPlayer);
		System.out.println(theAnswer.getSolutionRoom().getName() + ", " +
				theAnswer.getSolutionPerson().getName() + ", "
					+ theAnswer.getSolutionWeapon().getName());
		
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
			deckNoSolution.get(0).setCardOwner(players.get(i).getColor());
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
		Room room;
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

	public Player getPlayer(String player) {
		for (Player p : players) {
			if (p.getName().equals(player)) {
//				System.out.println("Board 640" + p.getName());
				return p;
			}
		}
		return null;
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

	public void handleSuggestion() { // Player accuser, String weapon, Player playerGuess

		String weaponS = getSuggestionWeapon();
		String playerS = getSuggestionPlayer();
		String roomS = getSuggestionRoom();
		
		Player accusationPlayer = getPlayer(playerS);
		Player accuser = getCurrentPlayer();
		
		ArrayList<Card> disproveCards = new ArrayList<>();

		accusationPlayer.updatePosition(accuser.getLocation());
		repaint();

		char roomInitial = accuser.getLocation().getInitial();
		Room room = getRoom(roomInitial);

		Card roomCard = getCard(roomS);
		Card weaponCard = getCard(weaponS);
		Card personCard = getCard(playerS);
		Solution suggestion = new Solution(roomCard, weaponCard, personCard);

		Color disproverColor = null;
		String colorString = "";

		for (Player p : players) {
			if (!p.equals(accuser)) {
				Card disprove = p.disproveSuggestion(suggestion);
				if (disprove != null) {
					disproveCards.add(disprove);
					colorString = p.getColor();
					disproverColor = p.getColorPlayer(p.getColor());
				}
			} else {
				continue;
			}
			getCurrentPlayer().setTurnFinished(true);
		}

		if (disproveCards.size() == 0) {
			control.setGuessResult("No new Clue");
		}

		if (disproveCards.size() == 1) {
			control.setGuessResult("Suggestion Disproved");
			accuser.updateSeen(disproveCards.get(0).getName());
		} else if (disproveCards.size() > 0) {
			Random rand = new Random();
			int randInt = rand.nextInt(disproveCards.size() - 1);
			accuser.updateSeen(disproveCards.get(randInt).getName());
			control.setGuessResult("Suggestion Disproved");
		}

		knownCardsPanel.updatePanels();

		if (getSuggestionPlayer() != null && getSuggestionWeapon() != null) {
			control.setGuess(getSuggestionPlayer() + " with the " + getSuggestionWeapon() + " in the "
					+ getSuggestionRoom() + ".");
		}

		repaint();
		revalidate();
	}

	public Player getHumanPlayer() {
		return players.get(0);
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerInt);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int height = this.getHeight();
		int width = this.getWidth();
		int cellHeight = height / numRows;
		int cellWidth = width / numCols;

		for (BoardCell[] cell : grid) {
			for (BoardCell c : cell) {
				c.drawCell(g, cellHeight, cellWidth, highlightedCells.contains(c));
			}
		}

		for (Player p : players) {
			p.drawPlayer(g, cellHeight, cellWidth);
		}

		for (BoardCell[] cell : grid) {
			for (BoardCell c : cell) {
				Room room = getRoom(c);
				if (c.isLabel()) {
					room.drawRoomLabel(g, cellHeight, cellWidth);
				}
			}
		}

	}

	public void rollDice() {
		Random rand = new Random();
		int roll = rand.nextInt((6 - 1) + 1) + 1;
		this.diceRoll = roll;
	}

	public int getDiceRoll() {
		return diceRoll;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Boolean getGameOver() {
		return gameOver;
	}

	public void firstTurn() {
		rollDice();
		calcTargets(players.get(0).getLocation(), getDiceRoll());

		highlightedCells = getTargets();

		control.setTurn(getCurrentPlayer());
		control.setRoll(getDiceRoll());
		control.setWhoseTurnColor(currentPlayerColor());

		repaint();
	}

	public void nextTurn() {

		if (getCurrentPlayer() instanceof HumanPlayer) {
			if (!getCurrentPlayer().getTurnFinished()) {
				JOptionPane.showMessageDialog(null, "Finish your turn first!");
				return;
			}
		}

		rollDice();
		iterateCurrent();
		control.setWhoseTurnColor(currentPlayerColor());
		control.setTurn(getCurrentPlayer());
		control.setRoll(getDiceRoll());
		if ((getCurrentPlayer() instanceof HumanPlayer) && getCurrentPlayer().getTurnFinished()) {
			getCurrentPlayer().setTurnFinished(false);

			calcTargets(getCurrentPlayer().getLocation(), getDiceRoll());
			highlightedCells = getTargets();
			repaint();

		}

		if (getCurrentPlayer() instanceof ComputerPlayer) {
			calcTargets(getCurrentPlayer().getLocation(), getDiceRoll());

			int randLoc;
			int upperBound;

			if (targets.size() <= 1) {
				upperBound = 0;
			} else {
				upperBound = targets.size() - 1;
			}

			Random rand = new Random();
			upperBound = targets.size() - 1;
			randLoc = rand.nextInt(upperBound);

			ArrayList<BoardCell> targetsList = new ArrayList<>(targets);
			BoardCell newLocation = targetsList.get(randLoc);

			getCurrentPlayer().updatePosition(newLocation);
			highlightedCells.clear();
			repaint();
			getCurrentPlayer().setTurnFinished(true);

			if (getCurrentPlayer().getLocation().isRoom()) {
				setSuggestionRoom(getRoom(getCurrentPlayer().getLocation()).getName());

				ArrayList<Card> weaponsNotInHand = new ArrayList<>();
				ArrayList<Card> playersNotInHand = new ArrayList<>();
				ArrayList<Card> roomsNotInHand = new ArrayList<>();

				roomsNotInHand = rooms;
				for (Card c : getCurrentPlayer().getHand()) {
					if (c.getCardType() == CardType.ROOM) {
						roomsNotInHand.remove(c);
					}
				}

				for (Card c : getCurrentPlayer().getSeenCards()) {
					if (c.getCardType() == CardType.ROOM) {
						roomsNotInHand.remove(c);
					}
				}

				weaponsNotInHand = weapons;
				for (Card c : getCurrentPlayer().getHand()) {
					if (c.getCardType() == CardType.WEAPON) {
						weaponsNotInHand.remove(c);
					}
				}

				for (Card c : getCurrentPlayer().getSeenCards()) {
					if (c.getCardType() == CardType.WEAPON) {
						weaponsNotInHand.remove(c);
					}
				}

				playersNotInHand = people;
				for (Card c : getCurrentPlayer().getHand()) {
					if (c.getCardType() == CardType.PERSON) {
						playersNotInHand.remove(c);
					}
				}

				for (Card c : getCurrentPlayer().getSeenCards()) {
					if (c.getCardType() == CardType.PERSON) {
						playersNotInHand.remove(c);
					}
				}

				if (weaponsNotInHand.size() != 1) {
					Random randW = new Random();
					int upperBoundWeapons = weaponsNotInHand.size() - 1;
					int randomW = randW.nextInt(upperBoundWeapons);

					setSuggestionWeapon(weaponsNotInHand.get(randomW).getName());
				}

				if (playersNotInHand.size() != 1) {
					Random randP = new Random();
					int upperBoundPeople = weaponsNotInHand.size() - 1;
					int randomP = randP.nextInt(upperBoundPeople);

					setSuggestionPlayer(playersNotInHand.get(randomP).getName());
				}

				if (playersNotInHand.size() != 1 && weaponsNotInHand.size() != 1 && roomsNotInHand.size() != 1) {
					handleSuggestion();
				} else if (playersNotInHand.size() == 1 && weaponsNotInHand.size() == 1 && roomsNotInHand.size() == 1) {
					setAccusationPlayer(playersNotInHand.get(0).getName());
					setAccusationWeapon(weaponsNotInHand.get(0).getName());
					setAccusationRoom(roomsNotInHand.get(0).getName());
					computerPlayerAccusation();
				}
			}
		}
	}

	private void computerPlayerAccusation() {
		Solution theAnswer = getTheAnswer();
		String correctRoom = theAnswer.getSolutionRoom().getName();
		String correctWeapon = theAnswer.getSolutionWeapon().getName();
		String correctPlayer = theAnswer.getSolutionPerson().getName();

		if (!correctRoom.equals(getSuggestionRoom()) && !correctWeapon.equals(getSuggestionWeapon())
				&& !correctPlayer.equals(getSuggestionPlayer())) {
			JOptionPane.showMessageDialog(null, "Guess is incorrect, you lose.");
		} else {
			JOptionPane.showMessageDialog(null, "Guess is correct, YOU WIN!");
		}
		setGameOver(true);
	}

	public Boolean diceRolled() {
		if (diceRoll == 0) {
			diceRolled = false;
		}
		return diceRolled;
	}

	public void makeAccusation() {
		
		Solution theAnswer = getTheAnswer();
		String correctRoom = theAnswer.getSolutionRoom().getName();
		String correctWeapon = theAnswer.getSolutionWeapon().getName();
		String correctPlayer = theAnswer.getSolutionPerson().getName();		
		
		String suggestionRoom = getAccusationRoom();
		String suggestionWeapon = getAccusationWeapon();
		String suggestionPerson = getAccusationPlayer();
		
		if (correctRoom.equals(suggestionRoom) &&
				correctWeapon.equals(suggestionWeapon) &&
					correctPlayer.equals(suggestionPerson)) {
			JOptionPane.showMessageDialog(null, "Guess is correct, YOU WIN!");
		} else {
			JOptionPane.showMessageDialog(null, "Guess is incorrect, you lose.");
		}
	}

	public Color currentPlayerColor() {
		String colorS = getCurrentPlayer().getColor();
		Color color = getCurrentPlayer().getColorPlayer(colorS);
		return color;
	}

	public void setKnownCards(KnownCardsPanel knownCardsPanel) {
		this.knownCardsPanel = knownCardsPanel;
	}
}