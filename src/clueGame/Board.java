package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import experiment.TestBoardCell;

public class Board {

	private BoardCell[][] matrix;
	private int numRows;
	private int numCols;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	public ArrayList<ArrayList<Character>> cells = new ArrayList<>();

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
			File setupFile = new File("data/ClueSetup.txt");
			Scanner setupReader = new Scanner(setupFile);

			String info = setupReader.nextLine();
			while (setupReader.hasNext()) {
				String wholeLine = setupReader.nextLine();
				String[] arrOfStr = wholeLine.split(", ", 3);
				if (arrOfStr[0] == "Room") {
					String current = arrOfStr[2];
					char c = current.charAt(0);
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
			File layoutFile = new File("data/ClueLayout.csv");
			Scanner layoutReader = new Scanner(layoutFile);

			ArrayList<String> csvCharacterStrings = new ArrayList<>();
			while (layoutReader.hasNext()) {
				String currentLine = layoutReader.nextLine();
				csvCharacterStrings.add(currentLine);
			}

			for (int i = 0; i < csvCharacterStrings.size(); i++) {
				String currentString = csvCharacterStrings.get(i);
				ArrayList<Character> characters = new ArrayList<>();
				for (int j = 0; j < currentString.length(); j++) {
					Character currentChar = currentString.charAt(j);
					if (currentChar != ',') {
						characters.add(currentChar);
					}
				}
				cells.add(characters);
			}
			layoutReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file LAYOUT");

		}

		for (int i = 0; i < cells.size(); i++) {
			ArrayList<Character> currentRow = new ArrayList<>();
			currentRow = cells.get(i);
			for (int j = 0; j < currentRow.size(); j++) {
				System.out.print(currentRow.get(j));
			}
			System.out.println();
		}
	}

	public void setConfigFiles(String layoutConfig, String setupConfig) {
//		this.layoutConfigFile = layoutConfig;
//		this.setupConfigFile = setupConfig;
	}

	public Room getRoom(char c) {
		Room room = new Room();
		if (c == 'C') {
			room.setName("Cookie Room");
		} else if (c == 'R') {
			room.setName("Reindeer Barn");
		} else if (c == 'E') {
			room.setName("Elve's Workshop");
		} else if (c == 'L') {
			room.setName("Santa's Lair");
		} else if (c == 'B') {
			room.setName("Bathroom");
		} else if (c == 'T') {
			room.setName("Toy Room");
		} else if (c == 'F') {
			room.setName("Christmas Tree Factory");
		} else if (c == 'G') {
			room.setName("Gift Wrapping Station");
		} else if (c == 'S') {
			room.setName("Sleigh Storage");
		} else if (c == 'X') {
			room.setName("Unused");
		} else if (c == 'W') {
			room.setName("Walkway");
		}
		return room;
	}

	public BoardCell getCell(int row, int col) {
		BoardCell currentCell = new BoardCell();
		return currentCell;
//		return null;
	}

	public int getNumRows() {
		numRows = cells.size();
		return numRows;
	}

	public int getNumColumns() {
		for (int i = 0; i < cells.size(); i++) {
			ArrayList<Character> singleRow = new ArrayList<>();
			singleRow = cells.get(i);
			numCols = singleRow.size();
		}
		return numCols;
	}

	
	//We already did this but passed in a character instead of a cell
	public Room getRoom(BoardCell cell) {
		char c = cell.getInitial();
		Room room = new Room();
		if (c == 'C') {
			room.setName("Cookie Room");
		} else if (c == 'R') {
			room.setName("Reindeer Barn");
		} else if (c == 'E') {
			room.setName("Elve's Workshop");
		} else if (c == 'L') {
			room.setName("Santa's Lair");
		} else if (c == 'B') {
			room.setName("Bathroom");
		} else if (c == 'T') {
			room.setName("Toy Room");
		} else if (c == 'F') {
			room.setName("Christmas Tree Factory");
		} else if (c == 'G') {
			room.setName("Gift Wrapping Station");
		} else if (c == 'S') {
			room.setName("Sleigh Storage");
		} else if (c == 'X') {
			room.setName("Unused");
		} else if (c == 'W') {
			room.setName("Walkway");
		}
		return room;
	}

}
