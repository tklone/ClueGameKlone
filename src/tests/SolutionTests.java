package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
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
		board.setSolution(board.getCard("The Grinch"), board.getCard("Christmas Tree Factory"),
				board.getCard("Broken Christmas Ornaments"));

		// Solution that is correct
		Solution fakeSolutionCorrect = new Solution();
		Card personCard = board.getCard("The Grinch");
		fakeSolutionCorrect.setSolution(board.getCard("The Grinch"), board.getCard("Christmas Tree Factory"),
				board.getCard("Broken Christmas Ornaments"));
		assertTrue(board.checkAccusation(fakeSolutionCorrect));

		// solution with wrong person
		Solution fakeSolutionPerson = new Solution();
		fakeSolutionPerson.setSolution(board.getCard("Mrs. Claus"), board.getCard("Christmas Tree Factory"),
				board.getCard("Broken Christmas Ornaments"));
		assertFalse(board.checkAccusation(fakeSolutionPerson));

		// solution with wrong weapon
		Solution fakeSolutionWeapon = new Solution();
		fakeSolutionWeapon.setSolution(board.getCard("TheGrinch"), board.getCard("Christmas Tree Factory"),
				board.getCard("String of Lights"));
		assertFalse(board.checkAccusation(fakeSolutionWeapon));
		// solution with wrong room
		Solution fakeSolutionRoom = new Solution();
		fakeSolutionRoom.setSolution(board.getCard("TheGrinch"), board.getCard("Bathroom"),
				board.getCard("Broken Christmas Ornaments"));
		assertFalse(board.checkAccusation(fakeSolutionRoom));

	}

	@Test
	public void disproveSuggestion() {
		ComputerPlayer computerPlayer = new ComputerPlayer("The Grinch", "Green", 25, 16);
		Solution guess = new Solution();
		guess.setSolution(board.getCard("Mrs. Claus"), board.getCard("Christmas Tree Factory"),
				board.getCard("Broken Christmas Ornaments"));

		computerPlayer.updateHand(Board.getCard("Mrs. Claus"));
		computerPlayer.updateHand(Board.getCard("String of Lights"));
		computerPlayer.updateHand(Board.getCard("Bathroom"));
		// If player has only one matching card it should be returned
		computerPlayer.disproveSuggestion(guess);
		assertEquals("Mrs. Claus", guess.getSolutionPerson().getName());

		guess.setSolution(board.getCard("Mrs. Claus"), board.getCard("Christmas Tree Factory"),
				board.getCard("String of Lights"));

//		guess.setSolutionRoom(Board.getCard("String of Lights"));
		// If players has >1 matching card, returned card should be chosen randomly
		// If player has no matching cards, null is returned
	}

	@Test
	public void testHandleSuggestion() {
		ArrayList <Player> players = board.getPlayers();
		ArrayList<Player> testPlayers = new ArrayList<>();
		
//		HumanPlayer humanPlayer = new HumanPlayer("Santa Claus", "Red", 25, 8);
		Player computerPlayer = players.get(0);
		Player computerPlayer1 = players.get(1);
		Player computerPlayer2 = players.get(2);

		computerPlayer.updateHand(board.getCard("Poisoned Egg Nog"));
		computerPlayer.updateHand(board.getCard("Candy Cane Crossbow"));
		computerPlayer.updateHand(board.getCard("Santa Claus"));
		
//		ComputerPlayer computerPlayer1 = new ComputerPlayer("Mrs. Claus", "Pink", 10, 1);
		computerPlayer1.updateHand(board.getCard("Reindeer Antler"));
		computerPlayer1.updateHand(board.getCard("Bathroom"));
		computerPlayer1.updateHand(board.getCard("Rudolph"));
		
//		ComputerPlayer computerPlayer2 = new ComputerPlayer("Olive the Other Reindeer", "Blue", 1, 16);
		computerPlayer2.updateHand(board.getCard("Buddy the Elf"));
		computerPlayer2.updateHand(board.getCard("The Grinch"));
		computerPlayer2.updateHand(board.getCard("Reindeer Barn"));
		
		players.add(computerPlayer);
		players.add(computerPlayer1);
		players.add(computerPlayer2);
		// Suggestion no one can disprove returns null
		Solution playerSuggestion = new Solution();
		playerSuggestion.setSolution(board.getCard("Mrs. Claus"), board.getCard("Elve's Workshop"), board.getCard("String of Lights"));
		assertTrue(board.handleSuggestion(playerSuggestion, computerPlayer) == null);
		// Suggestion only suggesting player can disprove returns null
		playerSuggestion.setSolution(board.getCard("Rudolph"), board.getCard("Bathroom"), board.getCard("Reindeer Antler"));
		assertTrue(board.handleSuggestion(playerSuggestion, computerPlayer1) == null);
		// Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		playerSuggestion.setSolution(board.getCard("Olive the Other Reindeer"), board.getCard("Sleigh Storage"), board.getCard("Candy Cane Crossbow"));
		assertEquals("Candy Cane Crossbow", board.handleSuggestion(playerSuggestion, computerPlayer1).getName());
		// Suggestion that two players can disprove, correct player (based on starting
		// with next player in list) returns answer

//		ArrayList<Player> playerList = new ArrayList<Player>();
//		Solution suggestion = new Solution(board.getCard("Cookie Room"), board.getCard("Nutcracker"), board.getCard("Santa Claus"));
//		fail("Not yet implemented");

	}

}
