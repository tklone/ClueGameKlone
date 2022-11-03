package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.Solution;

class PlayerTests {
	private static Board board;

//	@BeforeEach
	// Something here

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}

	@Test
	void testDeckSize() {
		// This one should return 21
		ArrayList<Card> testDeck = new ArrayList<>();
		testDeck = board.getDeck();
		assertEquals(21, testDeck.size());
	}

	@Test
	void testSolutionCardTypes() {
		ArrayList<Card> testDeck = new ArrayList<>();
		testDeck = board.getDeckNoSoln();
		board.setTheAnswer();
		assertTrue(board.hasSolutionRoom());
		assertTrue(board.hasSolutionPerson());
		assertTrue(board.hasSolutionWeapon());
		// Tests the size of the deck after the solution cards are removed
		assertEquals(18, testDeck.size());
	}

	@Test
	void testNumPlayers() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		assertEquals(6, testList.size());
	}

	@Test
	void testHandSize() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		board.drawHands();
		assertEquals(3, testList.get(0).getHand().size());
		assertEquals(3, testList.get(1).getHand().size());
		assertEquals(3, testList.get(2).getHand().size());
		assertEquals(3, testList.get(3).getHand().size());
		assertEquals(3, testList.get(4).getHand().size());
		assertEquals(3, testList.get(5).getHand().size());
	}

	@Test
	void testStartingLocation() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		
		
	}
}
