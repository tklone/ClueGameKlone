package experiment;

import java.util.HashSet;
import java.util.Set;

//Class TestBoardCell (in experiment package)- represents one cell in your grid.
public class TestBoardCell {

	public int row, column;
	public Boolean isRoom, isOccupied;
	private Set<TestBoardCell> AdjacencyList = new HashSet<TestBoardCell>();

	// A constructor that has passed into it the row and column for that cell.
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	// A setter to add a cell to this cells adjacency list
	void addAdjacency(TestBoardCell cell) {
		AdjacencyList.add(cell);

	}

	// returns the adjacency list for the cell
	public Set<TestBoardCell> getAdjList() {
		return AdjacencyList;
	}

	// A setter for indicating a cell is part of a room
	public void setRoom(boolean cellInRoom) {

	}

	// getter for indicating a cell is part of a room
	public boolean isRoom() {

		return true;
	}

	// A setter for indicating a cell is occupied by another player
	public void setOccupied(boolean occupied) {

	}

	// a getter for indicating a cell is occupied by another player
	public boolean getOccupied() {
		return true;
	}

}