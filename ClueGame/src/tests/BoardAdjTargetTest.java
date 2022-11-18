package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
// We make the Board static because we can load it one time and 
// then do all the tests. 
	private static Board board;

	@BeforeAll
	public static void setUp() {
// Board is singleton, get the only instance
		board = Board.getInstance();
// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");

		// Initialize will load config files 
		board.initialize();
	}

// Ensure that player does not move around within room
// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms() {
// we want to test a couple of different rooms.
// Gift Wrapping Station only has a single door but a secret room
		Set<BoardCell> testList = board.getAdjList(2, 2);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(5, 2)));
		assertTrue(testList.contains(board.getCell(22, 25)));
		
// now test the Christmas Tree Factory
		testList = board.getAdjList(20, 12);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(20, 9)));
				
		// one more room, the Toy room
		testList = board.getAdjList(21, 20);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(16, 20)));
		assertTrue(testList.contains(board.getCell(22, 23)));
				
	}

// Ensure door locations include their rooms and also additional walkways
// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyDoor() {
		Set<BoardCell> testList = board.getAdjList(7, 12);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(5, 12)));
		assertTrue(testList.contains(board.getCell(8, 12)));
		testList = board.getAdjList(20, 23);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(21, 23)));
		assertTrue(testList.contains(board.getCell(19, 23)));
		assertTrue(testList.contains(board.getCell(22, 25)));
		testList = board.getAdjList(7, 8);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(8, 8)));
		assertTrue(testList.contains(board.getCell(6, 8)));
		assertTrue(testList.contains(board.getCell(7, 9)));
		assertTrue(testList.contains(board.getCell(7, 5)));
	}

// Test a variety of walkway scenarios
// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways() {
// Test on bottom corner of board
		Set<BoardCell> testList = board.getAdjList(25, 1);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(24, 1)));
		assertTrue(testList.contains(board.getCell(25, 2)));
// Test adj to walkways
		testList = board.getAdjList(15, 4);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(15, 3)));
		assertTrue(testList.contains(board.getCell(14, 4)));
		assertTrue(testList.contains(board.getCell(15, 5)));
		assertTrue(testList.contains(board.getCell(16, 4)));

// Test near a door but not adjacent
		testList = board.getAdjList(16, 3);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(16, 2)));
		assertTrue(testList.contains(board.getCell(15, 3)));
		assertTrue(testList.contains(board.getCell(16, 4)));
// Test next to unused Space
		testList = board.getAdjList(12, 8);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(11, 8)));
		assertTrue(testList.contains(board.getCell(13, 8)));
		assertTrue(testList.contains(board.getCell(12, 7)));
	}

// Tests out of room center, 1, 3 and 4
// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsInCookieRoom() {
// test a roll of 1
		board.calcTargets(board.getCell(7, 5), 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(10, 5)));
		assertTrue(targets.contains(board.getCell(7, 8)));
// test a roll of 3
		board.calcTargets(board.getCell(7, 5), 3);
		targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(10, 3)));
		assertTrue(targets.contains(board.getCell(12, 5)));

// test a roll of 4
		board.calcTargets(board.getCell(7, 5), 4);
		targets = board.getTargets();
		assertEquals(16, targets.size());
		assertTrue(targets.contains(board.getCell(13, 5)));
		assertTrue(targets.contains(board.getCell(10, 8)));
		assertTrue(targets.contains(board.getCell(11, 7)));
		assertTrue(targets.contains(board.getCell(5, 9)));
	}

	@Test
	public void testTargetsInElvesWorkshop() {
// test a roll of 1
		board.calcTargets(board.getCell(22, 25), 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(18, 24)));
		assertTrue(targets.contains(board.getCell(20, 23)));
		assertTrue(targets.contains(board.getCell(2, 2)));
// test a roll of 3
		board.calcTargets(board.getCell(22, 25), 3);
		targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(16, 24)));
		assertTrue(targets.contains(board.getCell(18, 26)));
// test a roll of 4
		board.calcTargets(board.getCell(22, 25), 4);
		targets = board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(15, 24)));
	}

// Tests out of room center, 1, 3 and 4
// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
// test a roll of 1, at door
		board.calcTargets(board.getCell(16, 5), 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(20, 5)));
		assertTrue(targets.contains(board.getCell(15, 5)));
		assertTrue(targets.contains(board.getCell(16, 4)));
		assertTrue(targets.contains(board.getCell(16, 6)));
// test a roll of 3
		board.calcTargets(board.getCell(16, 5), 3);
		targets = board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(20, 5)));
		assertTrue(targets.contains(board.getCell(15, 5)));
		assertTrue(targets.contains(board.getCell(16, 8)));
// test a roll of 4
		board.calcTargets(board.getCell(16, 5), 4);
		targets = board.getTargets();
		assertEquals(17, targets.size());
		assertTrue(targets.contains(board.getCell(20, 5)));
		assertTrue(targets.contains(board.getCell(14, 3)));
	}

	@Test
	public void testTargetsInWalkway1() {
// test a roll of 1
		board.calcTargets(board.getCell(21, 23), 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(20, 23)));
		assertTrue(targets.contains(board.getCell(22, 23)));
// test a roll of 3
		board.calcTargets(board.getCell(21, 23), 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(22, 25)));
		assertTrue(targets.contains(board.getCell(24, 23)));
		assertTrue(targets.contains(board.getCell(18, 23)));
// test a roll of 4
		board.calcTargets(board.getCell(21, 23), 4);
		targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCell(22, 25)));
		assertTrue(targets.contains(board.getCell(17, 23)));
		assertTrue(targets.contains(board.getCell(18, 24)));
	}

	@Test
	public void testTargetsInWalkway2() {
// test a roll of 1
		board.calcTargets(board.getCell(8, 16), 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(7, 16)));
		assertTrue(targets.contains(board.getCell(8, 17)));
// test a roll of 3
		board.calcTargets(board.getCell(8, 16), 3);
		targets = board.getTargets();
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCell(5, 16)));
		assertTrue(targets.contains(board.getCell(9, 16)));
// test a roll of 4
		board.calcTargets(board.getCell(8, 16), 4);
		targets = board.getTargets();
		assertEquals(12, targets.size());
		assertTrue(targets.contains(board.getCell(9, 19)));
		assertTrue(targets.contains(board.getCell(5, 15)));
	}

	@Test
// test to make sure occupied locations do not cause problems
	public void testTargetsOccupied() {
// test a roll of 4 blocked 2 down
		board.getCell(15, 7).setOccupied(true);
		board.calcTargets(board.getCell(12, 7), 4);
		board.getCell(15, 7).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(16, targets.size());
		assertTrue(targets.contains(board.getCell(11, 4)));
		assertTrue(targets.contains(board.getCell(12, 3)));
		assertFalse(targets.contains(board.getCell(15, 7)));
		assertFalse(targets.contains(board.getCell(16, 7)));
// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(11, 25).setOccupied(true);
		board.getCell(10, 24).setOccupied(true);
		board.calcTargets(board.getCell(11, 24), 1);
		board.getCell(11, 25).setOccupied(false);
		board.getCell(10, 24).setOccupied(false);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(11, 23)));
		assertTrue(targets.contains(board.getCell(11, 25)));board.getCell(11, 24).setOccupied(true);
		//board.calcTargets(board.getCell(11, 25), 5);
		board.getCell(11, 24).setOccupied(false);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(12, 24)));
		assertTrue(targets.contains(board.getCell(11, 23)));
	}
}