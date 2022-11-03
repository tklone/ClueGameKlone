package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
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
		ArrayList<Card> testDeck = new ArrayList<>();
		testDeck = board.getDeck();
		assertEquals(21, testDeck.size());
	}

	@Test
	void solutionCardTypes() {
		board.setTheAnswer();
		assertTrue(board.hasSolutionRoom());
		assertTrue(board.hasSolutionPerson());
		assertTrue(board.hasSolutionWeapon());
	}

}
