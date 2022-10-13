package clueGame;

import java.io.File;
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
	
	//variable and methods used for singleton pattern
	private static Board theInstance = new Board();

	//constructor is private to ensure only one can be created
	private Board() {
		super();
	}
	//this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	//initialize the board(since we are using singleton pattern
	public void initialize() {
		
	}
	public void loadSetupConfig() {
		try {
			File setupFile = new File("ClueSetup.txt");
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
		} 
		catch (FileNotFoudException e) {
			System.out.println("Can't open file");
		}
	}
	public void loadLayoutConfig() {
		
	}
	public void setConfigFiles(String layoutConfig, String setupConfig) {
		this.layoutConfigFile = layoutConfig;
		this.setupConfigFile = setupConfig;
	}
	public Room getRoom(char c) {
		Room room = new Room();
//		String roomName = room.getName();
		if (c == 'C') {
			room.setName("Cookie Room");
		} else if (c == 'R') {
			room.setName("Reindeer Barn");
		}else if (c == 'E') {
			room.setName("Elve's Workshop");
		}else if (c == 'L') {
			room.setName("Santa's Lair");
		}else if (c == 'B') {
			room.setName("Bathroom");
		}else if (c == 'T') {
			room.setName("Toy Room");
		}else if (c == 'F') {
			room.setName("Christmas Tree Factory");
		}else if (c == 'G') {
			room.setName("Gift Wrapping Station");
		}else if (c == 'S') {
			room.setName("Sleigh Storage");
		}else if (c == 'X') {
			room.setName("Unused");
		}else if (c == 'W') {
			room.setName("Walkway");
		}
		return room;
	}
	
	public BoardCell getCell(int row, int col) {
		//return matrix[row][col];
		return null;
	}
	
	public int getNumRows() {
		return 0;
	}
	
	public int getNumColumns() {
		return 0;
	}
	
	public Room getRoom(BoardCell cell) {
		
		return null;
	}


}
