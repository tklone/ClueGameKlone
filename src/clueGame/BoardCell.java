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
	public Boolean doorway = false;

	public BoardCell() {
		// TODO Auto-generated constructor stub
	}

	public void setLabel(String str) {
		label = str;
//		System.out.println("!!" + label);
	}
	
	public String getLabel() {
		return label;
	}

	public void setInitial(String str) {
//		if (str.length() == 1) {
			initial = str.charAt(0);
//			System.out.println();
//		}
	}
	
	public Boolean isRoom() {
		if (!this.isDoorway() && this.initial != 'X' && this.initial != 'W') {
			return true;
		}
		return false;
	}
	
	public Boolean isWalkway() {
		if (this.initial == 'W') {
			return true;
		}
		return false;
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
		if ((label.length() > 1) && (label.charAt(0) == 'W') && (label.charAt(1) == '>' || label.charAt(1) == '<' || label.charAt(1) == '^' || label.charAt(1) == 'v')) {
			return true;
		}
		return false;
	}

	public boolean isRoomCenter() {
		if (label.length() != 1 && label.charAt(1) == '*') {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		if (label.length() > 1  && label.charAt(0) == 'W') {
			if (label.charAt(1) == '<') {
				return DoorDirection.LEFT;
			} else if (label.charAt(1) == '>') {
				return DoorDirection.RIGHT;
			} else if (label.charAt(1) == 'v') {
				return DoorDirection.DOWN;
			} else if (label.charAt(1) == '^') {
				return DoorDirection.UP;
			}
		}
		return null;
	}
	


	public boolean isLabel() {
		if (label.length() != 1 && label.charAt(1) == '#') {
			return true;
		}
		return false;
	}

	public Boolean isSecretPassage() {
		Character c = label.charAt(0);
		if (label.length() != 1 && (c != '<' && c != '>' && c != '^' && c != 'v' && c != '#' && c != '*')) {
			return true;
		}
		return false;
	}
	
	public void setSecretPassage() {
		
	}
	
	public char getSecretPassage() {
		char c;
		c = 'K';
		return c;
	}

	public char getInitial() {
//		System.out.println(initial);
		return initial;

	}

	public void setOccupied(boolean b) {
		// TODO Auto-generated method stub
		
	}

	// +addAdj (adj:BoardCell):void

}
