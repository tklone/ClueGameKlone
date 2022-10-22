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
	private Set<BoardCell> adjList = new HashSet<>();

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

			// This just picks up that first line of the file that has only the information
//			String info = setupReader.nextLine();
			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				String[] arrOfStr = wholeLine.split(", ");
				if (arrOfStr[0].equals("Room") || arrOfStr[0].equals("Space")) {
					String current = arrOfStr[2];
					Character c = current.charAt(0);
					
//					System.out.println(arrOfStr[0] + " " + arrOfStr[1] + " " + arrOfStr[2]);
//					System.out.println(c);
					
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
		room = roomMap.get(c);
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
//		System.out.println(c);
		Room room = new Room();
		room = roomMap.get(c);
		return room;
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		
		BoardCell theCell = new BoardCell();
		theCell = matrix[i][j];
		Room room = new Room();
		room = getRoom(theCell);
		
		if (theCell.isRoom()) {
			room = getRoom(theCell);
			
//			System.out.println(theCell.getInitial());
			
			adjList.add(room.getDoorway());
			if (room.getHasSP()) {
				adjList.add(room.getSPEnd());
			}
		}
		else if (theCell.isWalkway()) {
			if (i + 1 < numRows && matrix[i+1][j].getInitial() != 'X') {
				adjList.add(matrix[i + 1][j]);
			}
			if ( j + 1 < numCols && matrix[i][j + 1].getInitial() != 'X') {
				adjList.add(matrix[i][j + 1]);
			}
			if (i - 1 >= 0 && matrix[i - 1][j].getInitial() != 'X') {
				adjList.add(matrix[i - 1][j]);
			}
			if (j - 1 >= 0 && matrix[i][j - 1].getInitial() != 'X') {
				adjList.add(matrix[i][j - 1]);
			}
		}
	
		return adjList;
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return targets;
	}

	public void calcTargets(BoardCell cell, int i) {
		// TODO Auto-generated method stub

	}

	public void getDoorways() {
		int rowDoor;
		int colDoor;
		DoorDirection dir;		
		BoardCell doorCell = new BoardCell();
		BoardCell roomNearest = new BoardCell();
		Room currentRoom = new Room();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (doorCell.isDoorway()) {
					dir = doorCell.getDoorDirection();
					rowDoor = i;
					colDoor = j;
					switch (dir) {
					case LEFT:
						roomNearest = matrix[rowDoor][colDoor - 1];
						currentRoom = getRoom(roomNearest);
						currentRoom.setDoorway(doorCell);
					case RIGHT:
						roomNearest = matrix[rowDoor][colDoor + 1];
						currentRoom = getRoom(roomNearest);
						currentRoom.setDoorway(doorCell);
					case UP:
						roomNearest = matrix[rowDoor + 1][colDoor];
						currentRoom = getRoom(roomNearest);
						currentRoom.setDoorway(doorCell);
					case DOWN:
						roomNearest = matrix[rowDoor - 1][colDoor];
						currentRoom = getRoom(roomNearest);
						currentRoom.setDoorway(doorCell);
					default:
						break;
					}
				}
			}
		}

	}
	
	public void secretPassages() {
		Boolean secretPassagePresent = false;
		BoardCell spCell = new BoardCell();
		BoardCell spEndCell = new BoardCell();
		Room room = new Room();

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				spCell = matrix[i][j];
				String newLabel = spCell.getLabel();
				char endRoomChar = newLabel.charAt(1);
				char startRoomChar = newLabel.charAt(0);
				if (spCell.isSecretPassage()) {
					secretPassagePresent = true;
					room = getRoom(startRoomChar);
					room.setHasSP(secretPassagePresent);

				}

				String spCellLabel = "";
				spCellLabel = spCellLabel + endRoomChar + startRoomChar;				
				if (matrix[i][j].getLabel().equals(spCellLabel)) {
					spEndCell = matrix[i][j];
				}
			}
		}
		room.setSPStart(spCell);
		room.setSPEnd(spEndCell);
	}
}
