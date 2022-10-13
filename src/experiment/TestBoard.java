package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestBoard {

	final static int ROWS = 4;
	final static int COLS = 4;

	private Set<TestBoardCell> targets = new HashSet<TestBoardCell>();
	private Set <TestBoardCell> visited = new HashSet<TestBoardCell>();
	private static TestBoardCell[][] grid = new TestBoardCell[ROWS][COLS];
	private Map<TestBoardCell, Set<TestBoardCell>> AdjacencyMatrix = new HashMap<TestBoardCell, Set<TestBoardCell>>();

	// A constructor that sets up the board.
	public TestBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				TestBoardCell Current = grid[i][j];

				//
				if(i+1 < TestBoard.ROWS){
					Current.addAdjacency(TestBoard.getCell(i+1,j));
				}
				if(j+1 < TestBoard.COLS){
					Current.addAdjacency(TestBoard.getCell(i,j+1));
				}
				if(i-1 >= 0) {
					Current.addAdjacency(TestBoard.getCell(i-1, j));
				}
				if(j-1 >= 0 ) {
					Current.addAdjacency(TestBoard.getCell(i,j-1));
				}
			}
		}
	}
	
	public Map<TestBoardCell, Set<TestBoardCell>> getAdjMatx() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				TestBoardCell cell = getCell(i,j);
				AdjacencyMatrix.put(cell, cell.getAdjList());
			}
		}
		return AdjacencyMatrix;
	}

	public void findAllTargets(TestBoardCell thisCell, int numSteps) {		
	
		//for each adjCell in adjacentCells
		for(TestBoardCell c : thisCell.getAdjList()) {
			
			//need this if numSteps >1
			if(c.getOccupied() == true) {
				continue;
			}
			
			
				
//			System.out.println(c.getColumn());
//			System.out.println(c.getRow());
			//if already in visited list, skip rest of code
			if(visited.contains(c)) {
				//System.out.println("contains");
				continue;
			}
			//else add adjCell to visited list
			visited.add(c);
			
			if(c.isRoom()) {
				targets.add(c);
				continue;
			}
			
			//if numSteps==1, add adjCell to targets
			if(numSteps == 1) {
//				System.out.println("we are here");
				if(c.getOccupied() == false) {
					System.out.println("we are here");
					targets.add(c);
				}
			}
	
			//else call findAllTargets with adjCell and numSteps-1
			else {
				findAllTargets(c, numSteps - 1);
			}
			//remove adjCell from visited
			visited.remove(c);
		}
	}

	// calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(TestBoardCell startCell, int pathlength) {	
		visited.add(startCell);
		findAllTargets(startCell, pathlength);	
	}

	// gets the targets last created by calcTargets()
	public Set<TestBoardCell> getTargets() {
		return targets;
	}

	// TestBoardCell getCell( int row, int col ) – returns the cell from the board
	// at row, col.
	public static TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}

}