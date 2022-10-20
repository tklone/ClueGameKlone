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
//		System.out.println("!!" + str);
		if (str.length() == 1) {
//			System.out.println("dis one: " + str);
			initial = str.charAt(0);
		}
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
		if ((label.length() > 1) && (label.charAt(1) == '>' || label.charAt(1) == '<' || label.charAt(1) == '^' || label.charAt(1) == 'v')) {
			doorway = true;
			return doorway;
		}
		return doorway;
	}

	public boolean isRoomCenter() {
		if (label.length() != 1 && label.charAt(1) == '*') {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
//		System.out.println(label);
		if (label.length() > 1) {
//			System.out.println(label.charAt(1));
			DoorDirection dir;

			if (label.charAt(1) == '<') {
				doorway = true;
//				dir = DoorDirection.RIGHT;
//				System.out.println(doorway + " " + dir);
				return DoorDirection.LEFT;
			} else if (label.charAt(1) == '>') {
//				doorway = true;
				dir = DoorDirection.RIGHT;
//				System.out.println(doorway + " " + dir);

				return DoorDirection.RIGHT;
			} else if (label.charAt(1) == 'v') {
//				doorway = true;
				dir = DoorDirection.DOWN;
//				System.out.println(doorway + " " + dir);

				return dir;
			} else if (label.charAt(1) == '^') {
//				doorway = true;
				dir = DoorDirection.UP;
//				System.out.println(doorway + " " + dir);

				return dir;
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

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}

	public char getInitial() {
		return initial;

	}

	// +addAdj (adj:BoardCell):void

}
