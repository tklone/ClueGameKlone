package clueGame;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class BoardCell {
	
	private int row, col;
	private char initial;
	DoorDirection doorDirection;
	private boolean doorway = false;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList = new HashSet<BoardCell>();

	public BoardCell() {
		// TODO Auto-generated constructor stub
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return doorDirection;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return doorway;
	}

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean isLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
