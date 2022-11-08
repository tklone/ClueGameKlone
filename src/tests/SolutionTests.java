package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Color;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class SolutionTests {

	private static Board board;
	private static Card card;
	private static Solution theAnswer;
	
	
	@BeforeAll
	public static void setUpBeforeClass() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	@Test
	public void testCheckAccusation() {
		board.setSolution(board.getCard("Christmas Tree Factory"), board.getCard("Candlestick"), board.getCard("Grinch"));
		
		//Solution that is correct
		Solution fakeSolutionCorrect = new Solution(board.getCard("Christmas Tree Factory"), board.getCard("Candlestick"), board.getCard("Grinch"));
		assertTrue(board.checkAccusation(fakeSolutionCorrect));
		//solution with wrong person
		Solution fakeSolutionPerson = new Solution(board.getCard("Christmas Tree Factory"), board.getCard("Candlestick"), board.getCard("Mrs. Claus"));		
		assertFalse(board.checkAccusation(fakeSolutionPerson));
		//solution with wrong weapon
		Solution fakeSolutionWeapon = new Solution(board.getCard("Christmas Tree Factory"), board.getCard("String of Lights"), board.getCard("Grinch"));		
		assertFalse(board.checkAccusation(fakeSolutionWeapon));
		//solution with wrong room
		Solution fakeSolutionRoom = new Solution(board.getCard("Bathroom"), board.getCard("Candlestick"), board.getCard("Grinch"));		
		assertFalse(board.checkAccusation(fakeSolutionRoom));

	}
	
	
	@Test
	public void disproveSuggestion() {
		ComputerPlayer computerPlayer = new ComputerPlayer("The Grinch", "Green", 25, 16);
		Solution guess = new Solution();
//		board.getCard("Santa Claus"), board.getCard("Christmas Tree Factory"), board.getCard("Candlestick"));
		guess.setSolutionPerson(Board.getCard("Mrs. Claus"));
		guess.setSolutionRoom(Board.getCard("Christmas Tree Factory"));
		guess.setSolutionWeapon(Board.getCard("Candlestick"));
//		
		computerPlayer.updateHand(Board.getCard("Mrs. Claus"));
		computerPlayer.updateHand(Board.getCard("String of Lights"));
		computerPlayer.updateHand(Board.getCard("Bathroom"));
		//If player has only one matching card it should be returned
		computerPlayer.disproveSuggestion(guess);
		assertEquals("Mrs. Claus", guess.getSolutionPerson().getName());
		
		guess.setSolutionRoom(Board.getCard("String of Lights"));
//		assertEquals()
		//If players has >1 matching card, returned card should be chosen randomly
		//If player has no matching cards, null is returned
	}


	@Test 
	public void handleSuggestion() {
		//Suggestion no one can disprove returns null
		
		//Suggestion only suggesting player can disprove returns null
		//Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
	
		ArrayList<Player> playerList = new ArrayList<Player>();
//		Solution suggestion = new Solution(board.getCard("Cookie Room"), board.getCard("Nutcracker"), board.getCard("Santa Claus"));
		
		
		
		
		
	}

}
