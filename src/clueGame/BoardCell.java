package clueGame;

import java.util.Set;

public class BoardCell {
	
	private int row, col;
	private char initial;
	private String label;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList;
	

	public BoardCell() {
		// TODO Auto-generated constructor stub
	}

	public void setLabel(String str) {
		
	}
	
	public void setInitial(char c) {
		initial = c;
	}
	
	public void setRow(int r) {
		row = r;
	}

	public void setCol(int c) {
		col = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isDoorway() {
		// TODO Auto-generated method stub
		if (initial == '<' || initial == '>' || initial == 'v' || initial == '^') {
			return true;
		}
		return false;
	}


	public boolean isRoomCenter() {
		if (initial == '*') {
			return true;
		}
		return false;
	}


	public DoorDirection getDoorDirection() {
		if (initial == '<') {
			return doorDirection.LEFT;
		} else if (initial == '>') {
			return doorDirection.RIGHT;
		} else if (initial == 'v') {
			return doorDirection.DOWN;
		} else if (initial == '^') {
			return doorDirection.UP;
		}
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


	public char getInitial() {
		return initial;
		
	}
	
	//+addAdj (adj:BoardCell):void

}
