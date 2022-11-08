package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Player;

class PlayerTests {
	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.dealCards();
	}

	//Testing that full deck is 21 cards
	@Test
	void testDeckSize() {
		// This one should return 21
		ArrayList<Card> testDeck = new ArrayList<>();
		testDeck = board.getDeck();
		assertEquals(21, testDeck.size());
	}

	//testing that the solution has one card of each (room, person, and weapon)
	@Test
	void testSolutionCardTypes() {
		assertTrue(board.hasSolutionRoom());
		assertTrue(board.hasSolutionPerson());
		assertTrue(board.hasSolutionWeapon());
	}

	//There are 6 players
	@Test
	void testNumPlayers() {
		ArrayList<Card> testList = new ArrayList<>();
		testList = board.getPeopleCards();
		assertEquals(6, testList.size());
	}
	
	//There are 6 weapons
	@Test
	void testNumWeapons() {
		ArrayList<Card> testList = new ArrayList<>();
		testList = board.getWeaponsCards();
		assertEquals(6, testList.size());
	}
	
	//there are 9 rooms
	@Test
	void testNumRooms() {
		ArrayList<Card> testList = new ArrayList<Card>();
		testList = board.getRoomCards();
		assertEquals(9, testList.size());
	}

	//each player gets 3 cards
	@Test
	void testHandSize() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		assertEquals(3, testList.get(0).getHand().size());
		assertEquals(3, testList.get(1).getHand().size());
		assertEquals(3, testList.get(2).getHand().size());
		assertEquals(3, testList.get(3).getHand().size());
		assertEquals(3, testList.get(4).getHand().size());
		assertEquals(3, testList.get(5).getHand().size());
	}

	//each player starts at the correct positions that was read in from ClueSetup.txt
	@Test
	void testStartingLocation() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		assertEquals(25, testList.get(0).getRow());
		assertEquals(8, testList.get(0).getCol());

		assertEquals(10, testList.get(1).getRow());
		assertEquals(1, testList.get(1).getCol());

		assertEquals(25, testList.get(2).getRow());
		assertEquals(16, testList.get(2).getCol());

		assertEquals(5, testList.get(3).getRow());
		assertEquals(27, testList.get(3).getCol());

		assertEquals(1, testList.get(4).getRow());
		assertEquals(16, testList.get(4).getCol());

		assertEquals(17, testList.get(5).getRow());
		assertEquals(1, testList.get(5).getCol());

	}
	
	//each player is the correct color read in from ClueSetup.txt
	@Test
	void testColor() {
		ArrayList<Player> testList = new ArrayList<>();
		testList = board.getPlayers();
		assertEquals("Red", testList.get(0).getColor());
		assertEquals("Pink", testList.get(1).getColor());
		assertEquals("Green", testList.get(2).getColor());
		assertEquals("Brown", testList.get(3).getColor());
		assertEquals("Blue", testList.get(4).getColor());
		assertEquals("Yellow", testList.get(5).getColor());
	}
}
