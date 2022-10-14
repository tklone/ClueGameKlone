package clueGame;

import java.util.Set;

public class BoardCell {
	
	private int row, col;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList;
	

	public BoardCell() {
		// TODO Auto-generated constructor stub
	}


	public boolean isDoorway() {
		// TODO Auto-generated method stub
		
		return false;
	}


	public boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return false;
	}


	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isLabel() {
		// TODO Auto-generated method stub
		return false;
	}


	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//+addAdj (adj:BoardCell):void

}
