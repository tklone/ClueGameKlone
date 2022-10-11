package tests;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

class BoardTestsExp {
	
	TestBoard board;

	@BeforeEach
	public  void setUpBeforeClass(){
		board = new TestBoard();
	}

	
	@Test //Followed Canvas example
	public void testAdjacency() {
		//top left corner (i.e., location [0][0])
		TestBoardCell topLeftCorner = board.getCell(0, 0);
		Set<TestBoardCell> testList = topLeftCorner.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertEquals(2, testList.size());
				
		//bottom right corner (i.e., location [3][3])
		TestBoardCell bottomRightCorner = board.getCell(3,3);
		testList = bottomRightCorner.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertEquals(2, testList.size());
				
		//a right edge (e.g., location [1][3])
		TestBoardCell topRightCell = board.getCell(1, 3);
		testList = topRightCell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0,3)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertEquals(3, testList.size());
				
		//a left edge (e.g., location [3][0])
		TestBoardCell bottomLeftCell = board.getCell(3,0);
		testList = bottomLeftCell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertTrue(testList.contains(board.getCell(3,1)));
		Assert.assertEquals(2, testList.size());
		
		//middle of matrix (e.g., location [2][2])
		TestBoardCell middleCell = board.getCell(2,2);
		testList = middleCell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,1)));
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertEquals(4, testList.size());
			
	}

	/*
	 * Methods (minimum 5) to test target creation on a 4x4 board (see examples below)
	 * Test for behavior on empty 4x4 board.
	 * Test for behavior with at least one cell being flagged as occupied.  
	 * A player cannot move into an occupied cell.
	 * Test for behavior with at least one cell being flagged as a room.   
	 * A player used up all movement points upon entering a room.
	 */
	@Test
	public void testTargetsNormal() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testTargetsMixed() {

		board.getCell(0, 2).setOccupied(true);
		board.getCell(1, 2).setRoom(true);
		TestBoardCell cell = board.getCell(0, 3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();

		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
	}
}