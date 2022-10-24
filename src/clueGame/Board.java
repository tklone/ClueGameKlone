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

	private static BoardCell[][] matrix;
	private int numRows;
	private int numCols;
	private String setupConfig;
	private String layoutConfig;
	private Map<Character, Room> roomMap = new HashMap<>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
//	private Set<BoardCell> adjList = new HashSet<>();
	private BoardCell labelCell1 = new BoardCell();
	private Set<BoardCell> visited = new HashSet<BoardCell>();

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

	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File setupFile = new File("data/" + setupConfig);
			Scanner setupReader = new Scanner(setupFile);
			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				String[] arrOfStr = wholeLine.split(", ");
				if (arrOfStr[0].equals("Room") || arrOfStr[0].equals("Space")) {
					String current = arrOfStr[2];
					Character c = current.charAt(0);
					Room newRoom = new Room();
					newRoom.setName(arrOfStr[1]);
					newRoom.setChar(c);
					roomMap.put(c, newRoom);
				} else {
//					throw new BadConfigFormatException();
				}
			}
			setupReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file SETUP");
		}
	}

	public void loadLayoutConfig() {
		try {
			File layoutFile = new File("data/" + layoutConfig);
			Scanner layoutReader = new Scanner(layoutFile);

			ArrayList<String> eachRow = new ArrayList<>();
			while (layoutReader.hasNext()) {
				String currentLine = layoutReader.nextLine();
				eachRow.add(currentLine);
			}

			matrix = new BoardCell[numRows][numCols];
			String[] currentString;

			for (int i = 0; i < eachRow.size(); i++) {
				String rowStrings = eachRow.get(i);
				currentString = rowStrings.split(",");
				numCols = currentString.length;
			}
			numRows = eachRow.size();

			matrix = new BoardCell[numRows][numCols];
			Room room = new Room();
			for (int i = 0; i < eachRow.size(); i++) {
				String rowStrings = eachRow.get(i);
				currentString = rowStrings.split(",");
				for (int j = 0; j < currentString.length; j++) {
					BoardCell newCell = new BoardCell();
					matrix[i][j] = newCell;
					newCell.setLabel(currentString[j]);
					newCell.setCol(i);
					newCell.setRow(j);
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
//					if (newCell.getLabel().length() > 1 && (newCell.getLabel().charAt(1) != '^' || newCell.getLabel().charAt(1) != 'v' || newCell.getLabel().charAt(1) != '<' || newCell.getLabel().charAt(1) != '>')) {
//						room.addDoorway(newCell);
//					}
				}
			}

			layoutReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file LAYOUT");

		}
	}

	public void setConfigFiles(String layoutConfig, String setupConfig) {
		this.layoutConfig = layoutConfig;
		this.setupConfig = setupConfig;
	}

	public Room getRoom(Character c) {
		Room room = new Room();
		room = roomMap.get(c);
		return room;
	}

	public static BoardCell getCell(int row, int col) {
		return matrix[row][col];
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

	
	public Set<BoardCell> getAdjList(int i, int j) {
		return matrix[i][j].getAdjList();
	}
	
	
	public Set<BoardCell> calcAdjList(int i, int j) {

		BoardCell theCell = new BoardCell();
		theCell = matrix[i][j];
		BoardCell newCell = new BoardCell();
		Room room = new Room();

		if (theCell.isRoom()) {
			room = getRoom(theCell);
			getNearestDoor();
		
			//Never runs this because there are apparently no doorways in the study
			for (BoardCell c : room.getDoorway()) {
				theCell.addAdjacency(c);
			}
			
			if (room.getHasSP()) {
				if (theCell == matrix[2][2]) {
				newCell = room.getSecretPassageCell();
				char c = newCell.getSecretPassage();
				room = getRoom(c);
				theCell.addAdjacency(room.getCenterCell());
				}
			}
			

		}
		// This is for seeing if cell is a walkway, then adding adj cells to adjList
		else if (theCell.isWalkway()) {
			if (i + 1 < numRows && matrix[i + 1][j].getInitial() != 'X') {
				if (matrix[i + 1][j].isRoom()) {
					theCell.addAdjacency(getRoom(matrix[i + 1][j]).getCenterCell());
				} else {
					theCell.addAdjacency(matrix[i + 1][j]);
				}
			}
			if (j + 1 < numCols && matrix[i][j + 1].getInitial() != 'X') {
				if (matrix[i][j + 1].isRoom()) {
					theCell.addAdjacency(getRoom(matrix[i][j + 1]).getCenterCell());
				} else {
					theCell.addAdjacency(matrix[i][j + 1]);
				}
			}
			if (i - 1 >= 0 && matrix[i - 1][j].getInitial() != 'X') {
				if (matrix[i - 1][j].isRoom()) {
					theCell.addAdjacency(getRoom(matrix[i - 1][j]).getCenterCell());
				} else {
					theCell.addAdjacency(matrix[i - 1][j]);
				}
			}
			if (j - 1 >= 0 && matrix[i][j - 1].getInitial() != 'X') {
				if (matrix[i][j - 1].isRoom()) {
					theCell.addAdjacency(getRoom(matrix[i][j - 1]).getCenterCell());
				} else {
					theCell.addAdjacency(matrix[i][j - 1]);
				}
			}
		}

		return theCell.getAdjList();
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
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
		for (BoardCell c : thisCell.getAdjList(0, 0)) {

			// need this if numSteps >1
			if (c.getOccupied() == true) {
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
		Room room = new Room();

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				BoardCell cell = matrix[i][j];
				if (cell.isDoorway()) {
					dir = cell.getDoorDirection();
					switch (dir) {
					case LEFT:
//						System.out.println("left");
						roomNearestCell = matrix[i][j - 1];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
//						continue;
					case RIGHT:
//						System.out.println("right");
						roomNearestCell = matrix[i][j + 1];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
//						continue;
					case UP:
//						System.out.println("up");
						roomNearestCell = matrix[i + 1][j];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
//						continue;
					case DOWN:
//						System.out.println("down");
						roomNearestCell = matrix[i - 1][j];
						room = getRoom(roomNearestCell);
						room.addDoorway(cell);
//						continue;
					default:
						break;
					}
				}
			}
		}
	}

}
