package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;

class ComputerPlayerAITests {

	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("Clue_Layout.csv", "ClueSetup.txt");
		board.initialize();
	}
	

	@Test
	public void createSuggestion() {
		//Room matches current location
		//If only one weapon not seen, it's selected
		//If only one person not seen, it's selected (can be same test as weapon)
		//If multiple weapons not seen, one of them is randomly selected
		//If multiple persons not seen, one of them is randomly selected
	
	}
	
	@Test
	public void selectTargets() {
		
		Set<BoardCell> targets = new HashSet<BoardCell>();
		//if no rooms in list, select randomly
		//if room in list that has not been seen, select it
		//if room in list that has been seen, each target (including room) selected randomly
	
		
	}
	

}
