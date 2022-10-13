package clueGame;

import java.util.Map;

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
		
	}
	public void loadLayoutConfig() {
		
	}
	public void setConfigFiles(String layoutConfig, String setupConfig) {
		this.layoutConfigFile = layoutConfig;
		this.setupConfigFile = setupConfig;
	}
	public Room getRoom(char c) {
		return null;
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
