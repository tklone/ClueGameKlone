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

	private BoardCell[][] matrix;
	private int numRows;
	private int numCols;
	private String layoutConfigFile = "data/ClueLayout.csv";
	private String setupConfigFile = "data/ClueSetup.txt";
	private Map<Character, Room> roomMap = new HashMap<>();
	private Set<BoardCell> targets = new HashSet<>();

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
		theInstance.loadSetupConfig();
		theInstance.loadLayoutConfig();
	}

	public void loadSetupConfig() {
		try {
			File setupFile = new File(setupConfigFile);
			Scanner setupReader = new Scanner(setupFile);

			String info = setupReader.nextLine();
			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				String[] arrOfStr = wholeLine.split(", ");
				if (arrOfStr[0].equals("Room")) {
					String current = arrOfStr[2];
					Character c = current.charAt(0);
					Room newRoom = new Room();
					newRoom.setName(arrOfStr[1]);
					newRoom.setChar(c);
					roomMap.put(c, newRoom);
				}
			}
			setupReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file SETUP");
		}
	}

	public void loadLayoutConfig() {
		try {
			File layoutFile = new File(layoutConfigFile);
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

			for (int i = 0; i < eachRow.size(); i++) {
				String rowStrings = eachRow.get(i);
				currentString = rowStrings.split(",");
//				numCols = currentString.length;
				for (int j = 0; j < currentString.length; j++) {
					BoardCell newCell = new BoardCell();
					matrix[i][j] = newCell;
					newCell.setLabel(currentString[j]);
					newCell.setCol(i);
					newCell.setRow(j);
					newCell.setInitial(newCell.getLabel());
				}
			}

			layoutReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file LAYOUT");

		}
	}

	public void setConfigFiles(String layoutConfig, String setupConfig) {

	}

	public Room getRoom(Character c) {
		Room room = new Room();
		if (c.equals('C')) {
			room.setName("Cookie Room");
		} else if (c.equals('R')) {
			room.setName("Reindeer Barn");
		} else if (c.equals('E')) {
			room.setName("Elve's Workshop");
		} else if (c.equals('L')) {
			room.setName("Santa's Lair");
		} else if (c.equals('B')) {
			room.setName("Bathroom");
		} else if (c.equals('T')) {
			room.setName("Toy Room");
		} else if (c.equals('F')) {
			room.setName("Christmas Tree Factory");
		} else if (c.equals('G')) {
			room.setName("Gift Wrapping Station");
		} else if (c.equals('S')) {
			room.setName("Sleigh Storage");
		} else if (c.equals('X')) {
			room.setName("Unused");
		} else if (c.equals('W')) {
			room.setName("Walkway");
		}
		return room;
	}

	public BoardCell getCell(int row, int col) {
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
		if (c.equals('C')) {
			room.setName("Cookie Room");
		} else if (c.equals('R')) {
			room.setName("Reindeer Barn");
		} else if (c.equals('E')) {
			room.setName("Elve's Workshop");
		} else if (c.equals('L')) {
			room.setName("Santa's Lair");
		} else if (c.equals('B')) {
			room.setName("Bathroom");
		} else if (c.equals('T')) {
			room.setName("Toy Room");
		} else if (c.equals('F')) {
			room.setName("Christmas Tree Factory");
		} else if (c.equals('G')) {
			room.setName("Gift Wrapping Station");
		} else if (c.equals('S')) {
			room.setName("Sleigh Storage");
		} else if (c.equals('X')) {
			room.setName("Unused");
		} else if (c.equals('W')) {
			room.setName("Walkway");
		}
		return room;
	}

	public Set<BoardCell> getAdjList(int i, int j) {

		Set<BoardCell> adjList = new HashSet<>();
		// I think we need to only add to the adjacency list if it is a walkway
		// Or a room. So if it's a door or a wall or something, don't add it

		BoardCell theCell = new BoardCell();
		if (i + 1 < numRows) {
			theCell = theInstance.getCell(i + 1, j);
			if (!theCell.isDoorway() && theCell.getInitial() != 'X') {
//				System.out.println("gets here");
				adjList.add(theCell);
			}
		}
		if (j + 1 < numCols) {
			theCell = theInstance.getCell(i, j + 1);
			if (!theCell.isDoorway() && theCell.getInitial() != 'X') {
				adjList.add(theCell);
			}
		}
		if (i - 1 >= 0) {
			theCell = theInstance.getCell(i - 1, j);
			if (!theCell.isDoorway() && theCell.getInitial() != 'X') {
				adjList.add(theCell);
			}
		}
		if (j - 1 >= 0) {
			theCell = theInstance.getCell(i, j - 1);
			if (!theCell.isDoorway() && theCell.getInitial() != 'X') {
				adjList.add(theCell);
			}
		}
		// Middle numRows and columns

//		if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
//			adjList.add(theInstance.getCell(i - 1, j));
//			adjList.add(theInstance.getCell(i, j - 1));
//			adjList.add(theInstance.getCell(i, j + 1));
//			adjList.add(theInstance.getCell(i + 1, j));
//		}
//
//		// Top left corner
//		if (i == 0 && j == 0) {
//			adjList.add(theInstance.getCell(i + 1, j));
//			adjList.add(theInstance.getCell(i, j + 1));
//		}
//
//		// Bottom right corner
//		if (i == numRows - 1 && j == numCols - 1) {
//			adjList.add(theInstance.getCell(i - 1, j));
//			adjList.add(theInstance.getCell(i, j - 1));
//		}
//
//		// Top right corner
//		if (i == numRows - 1 && j == 0) {
//			adjList.add(theInstance.getCell(i - 1, j));
//			adjList.add(theInstance.getCell(i, j + 1));
//		}
//
//		// Bottom left corner
//		if (i == numRows && j == 0) {
//			adjList.add(theInstance.getCell(i, j - 1));
//			adjList.add(theInstance.getCell(i + 1, j));
//		}
//
//		// Anywhere on the top edge
//		if (i == 0 && j != 0 && j != numCols - 1) {
//			adjList.add(theInstance.getCell(i, j - 1));
//			adjList.add(theInstance.getCell(i, j + 1));
//			adjList.add(theInstance.getCell(i + 1, j));
//		}
//
//		// Anywhere on the bottom edge
//		if (i == numRows - 1 && j != 0 && j != numCols - 1) {
//			adjList.add(theInstance.getCell(i, j - 1));
//			adjList.add(theInstance.getCell(i, j + 1));
//			adjList.add(theInstance.getCell(i - 1, j));
//		}
//
//		// Anywhere on the left edge
//		if (j == 0 && i != 0 && i != numRows - 1) {
//			adjList.add(theInstance.getCell(i + 1, j));
//			adjList.add(theInstance.getCell(i - 1, j));
//			adjList.add(theInstance.getCell(i, j + 1));
//		}
//
//		// Anywhere on the right edge
//		if (j == numCols - 1 && i != 0 && i != numRows - 1) {
//			adjList.add(theInstance.getCell(i + 1, j));
//			adjList.add(theInstance.getCell(i - 1, j));
//			adjList.add(theInstance.getCell(i, j - 1));
//		}
		return adjList;
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return targets;
	}

	public void calcTargets(BoardCell cell, int i) {
		// TODO Auto-generated method stub

	}

}
