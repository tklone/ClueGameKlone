package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class Board {

	private static BoardCell[][] grid;
	private int numRows;
	private int numCols;
	private String setupConfig;
	private String layoutConfig;
	private Map<Character, Room> roomMap = new HashMap<>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private ArrayList<Card> deck = new ArrayList<Card>();

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

	//reading in the text file
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File setupFile = new File("data/" + setupConfig);
			Scanner setupReader = new Scanner(setupFile);
			Card card = new Card();
			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				if (wholeLine.charAt(0) != '/') {
					String[] arrOfStr = wholeLine.split(", ");
					if (arrOfStr[0].equals("Room") || arrOfStr[0].equals("Space")) {
						String current = arrOfStr[2];
						Character c = current.charAt(0);
						Room newRoom = new Room();
						newRoom.setName(arrOfStr[1]);
						newRoom.setChar(c);
						roomMap.put(c, newRoom);
						if (arrOfStr[0].equals("Room")) {
							card.setCardType(CardType.ROOM);
							card.setName(arrOfStr[0]);
							deck.add(card);
						}
					} else  if (arrOfStr[0].equals("Weapon")){
						card.setCardType(CardType.WEAPON);
						card.setName(arrOfStr[1]);
						deck.add(card);
					} else if (arrOfStr[0].equals("Player")) {
						card.setCardType(CardType.PLAYER);
						card.setName(arrOfStr[1]);
						deck.add(card);
					} else {
//						throw new BadConfigFormatException();
					}
				}
			}
			setupReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file SETUP");
		}
	}

	//reading in the csv file
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
				}
			}

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file LAYOUT");

		}
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

	public Set<BoardCell> getTargets() {
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

			if (c.isRoom()) {
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

	public ArrayList<Card> getDeck() {
		return deck;
	}
	
}
