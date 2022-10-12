package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Board {
	
	private BoardCell[][] grid;
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	
	private String layoutConfigFile;
	private String setupConfigFile;

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
	
     public void setConfigFiles(String csv, String text){
 		setupConfigFile = text;
 		layoutConfigFile = csv;

 	}
	public Object getRoom(char c) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
