package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Solution;

class SolutionTests {

	private static Board board;
	private static Solution theAnswer;
	
	
	@BeforeAll
	public static void setUpBeforeClass() {
		board = Board.getInstance();
		board.setConfigFiles("Clue_Layout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	@Test
	public void checkAccusation() {
		
	}
	
	
	@Test
	public void disproveSuggestion() {

	}


	@Test 
	public void handleSuggestion() {
	
	}

}
