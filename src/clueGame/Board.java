package clueGame;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
	
	private BoardCell[][] grid;
	private int numRows;
	private int numCols;
	
	private String layoutConfigFile;
	private String setupConfigFile;
	
	private Map<Character, Room> roomMap; 
	
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	

	  /*
     * variable and methods used for singleton pattern
     */
     private static Board theInstance = new Board();
     // constructor is private to ensure only one can be created
     private Board() {
            super() ;
     }
     // this method returns the only Board
     public static Board getInstance() {
            return theInstance;
     }
     /*
      * initialize the board (since we are using singleton pattern)
      */
     public void initialize(){
    	 
     }
     
     public void loadSetupConfig(){
    	 
     }
     
     public void loadLayoutConfig(){
    	 
     }
	
     public void setConfigFiles(String csv, String text){
 		setupConfigFile = text;
 		layoutConfigFile = csv;

 	}
	public Room getRoom(char c) {
		// TODO Auto-generated method stub
		return null;
	}
	public Room getRoom(BoardCell cell) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getNumRows() {
		// TODO Auto-generated method stub
		return numRows;
	}
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numCols;
	}
	public BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
