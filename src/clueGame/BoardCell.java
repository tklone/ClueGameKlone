package clueGame;

import java.util.HashSet;
import java.util.Set;

public class BoardCell {

	private int row, col;
	private char initial;
	private String label;
	private DoorDirection doorDirection;
	private boolean roomLabel = false;
	private boolean roomCenter;
	private char secretPassage;
	public Boolean doorway = false;
	public boolean isRoom = false;
	public boolean isOccupied = false;
	private Set<BoardCell> adjList = new HashSet<>();

	public BoardCell() {
		super();
	}
	
	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<BoardCell> getAdjListCell() {
		return adjList;
	}

	public boolean isRoomCenter() {
		if (label.length() != 1 && label.charAt(1) == '*') {
			roomCenter = true;
		}
		return roomCenter;
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

	public boolean isDoorway() {
		if ((label.length() > 1) && (label.charAt(0) == 'W') && (label.charAt(1) == '>' || label.charAt(1) == '<' || label.charAt(1) == '^' || label.charAt(1) == 'v')) {
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
			} else if (label.charAt(1) == 'V' || label.charAt(1) == 'v') {
				return DoorDirection.DOWN;
			} else if (label.charAt(1) == '^') {
				return DoorDirection.UP;
			}
		}
		return null;
	}

	//secret passage bool and char
	public Boolean isSecretPassage() {
		Character c = label.charAt(0);
		if (label.length() != 1 && (c != '<' && c != '>' && c != '^' && c != 'v' && c != '#' && c != '*')) {
			return true;
		}
		return false;
	}
	
	public char getSecretPassage() {
		String label = this.getLabel();
		secretPassage = label.charAt(1);
		return secretPassage;
	}
	
	//getter and setter for Initial
	public char getInitial() {
		return initial;

	}
	public void setInitial(String str) {
		initial = str.charAt(0);
	}
	
	
	//getters and setters for rows and columns
	public void setRow(int r) {
		this.row = r;
	}

	public void setCol(int c) {
		this.col = c;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	//getter and setter and bool for Label
	public Boolean roomLabel() {
		if (this.label.length() >= 1 && label.charAt(1) == '#') {
			roomLabel = true;
		}
		return roomLabel;
	}
	
	public boolean isLabel() {
		if (label.length() != 1 && label.charAt(1) == '#') {
			return true;
		}
		return false;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String str) {
		label = str;
	}

	// A setter for indicating a cell is occupied by another player
	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}

	// a getter for indicating a cell is occupied by another player
	public boolean getOccupied() {
		return isOccupied;
	}

}
