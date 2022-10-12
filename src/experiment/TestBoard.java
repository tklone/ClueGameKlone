package experiment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestBoard {

	final static int ROWS = 4;
	final static int COLS = 4;

	private Set<TestBoardCell> targets = new HashSet<TestBoardCell>();
	private TestBoardCell[][] matrix = new TestBoardCell[ROWS][COLS];
//	private Set <TestBoardCell> visited;
//	public Set<TestBoardCell> AdjacencyList = new HashSet<TestBoardCell>();

	// A constructor that sets up the board.
	public TestBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = new TestBoardCell(i, j);
			}
		}

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				TestBoardCell Current = matrix[i][j];

				// Middle rows and columns
				if (i != 0 && j != 0 && i != ROWS - 1 && j != COLS - 1) {
					Current.addAdjacency(matrix[i - 1][j]);
					Current.addAdjacency(matrix[i][j - 1]);
					Current.addAdjacency(matrix[i][j + 1]);
					Current.addAdjacency(matrix[i + 1][j]);
				}

				// Top left corner
				if (i == 0 && j == 0) {
					Current.addAdjacency(matrix[i + 1][j]);
					Current.addAdjacency(matrix[i][j + 1]);
				}

				// Bottom right corner
				if (i == ROWS - 1 && j == COLS - 1) {
					Current.addAdjacency(matrix[i - 1][j]);
					Current.addAdjacency(matrix[i][j - 1]);
				}

				// Top right corner
				if (i == ROWS - 1 && j == 0) {
					Current.addAdjacency(matrix[i - 1][j]);
					Current.addAdjacency(matrix[i][j + 1]);
				}

				// Bottom left corner
				if (i == ROWS && j == 0) {
					Current.addAdjacency(matrix[i][j - 1]);
					Current.addAdjacency(matrix[i + 1][j]);
				}

				// Anywhere on the top edge
				if (i == 0 && j != 0 && j != COLS - 1) {
					Current.addAdjacency(matrix[i][j - 1]);
					Current.addAdjacency(matrix[i][j + 1]);
					Current.addAdjacency(matrix[i + 1][j]);
				}

				// Anywhere on the bottom edge
				if (i == ROWS - 1 && j != 0 && j != COLS - 1) {
					Current.addAdjacency(matrix[i][j - 1]);
					Current.addAdjacency(matrix[i][j + 1]);
					Current.addAdjacency(matrix[i - 1][j]);
				}

				// Anywhere on the left edge
				if (j == 0 && i != 0 && i != ROWS - 1) {
					Current.addAdjacency(matrix[i + 1][j]);
					Current.addAdjacency(matrix[i - 1][j]);
					Current.addAdjacency(matrix[i][j + 1]);
				}

				// Anywhere on the right edge
				if (j == COLS - 1 && i != 0 && i != ROWS - 1) {
					Current.addAdjacency(matrix[i + 1][j]);
					Current.addAdjacency(matrix[i - 1][j]);
					Current.addAdjacency(matrix[i][j - 1]);
				}
			}
		}
	}

	public void findAllTargets(TestBoardCell thisCell, int numSteps) {		
		List <TestBoardCell> targets = new ArrayList<>();
		List <TestBoardCell> visited = new ArrayList<>();
		//Base Case
		if (visited.isEmpty()) {
			visited.add(thisCell);
		}
		
		Boolean isPresent = false;

		for (TestBoardCell c : visited) {
			if (thisCell == c) {
				isPresent = true;
				break;
			}
		}
		
		if (!isPresent) {
			visited.add(thisCell);
		}
		
//		thisCell.getAdjList();
		if (numSteps == 1) {
//			targets.add(thisCell.getAdjList());
		}
		else {
//			findAllTargets(thisCell, numSteps - 1);
		}
//		
//		for (TestBoardCell c : visited) {
//			if (thisCell == c) {
//				visited.remove(c);
//			}
		}
		
//	}

	// calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(TestBoardCell startCell, int pathlength) {
//		List<TestBoardCell> visited = new ArrayList<>();
//		List<TestBoardCell> targets = new ArrayList<>();
//		
		findAllTargets(startCell, pathlength);
		
	}

	// We think?

	// gets the targets last created by calcTargets()
	public Set<TestBoardCell> getTargets() {
		return targets;
	}

	// TestBoardCell getCell( int row, int col ) – returns the cell from the board
	// at row, col.
	public TestBoardCell getCell(int row, int col) {
		return matrix[row][col];
	}

}