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

	public BoardCell() {
		super();
	}

	public void setLabel(String str) {
		label = str;
	}
	
	public Boolean roomLabel() {
		if (this.label.length() >= 1 && label.charAt(1) == '#') {
			roomLabel = true;
		}
		return roomLabel;
	}
	
	public String getLabel() {
		return label;
	}
	

	public boolean isRoomCenter() {
		if (label.length() != 1 && label.charAt(1) == '*') {
			roomCenter = true;
		}
		return roomCenter;
	}
	
	public void setInitial(String str) {
			initial = str.charAt(0);
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
	
	public char getSecretPassage() {
		String label = this.getLabel();
		secretPassage = label.charAt(1);
		return secretPassage;
	}

	public char getInitial() {
		return initial;

	}

	public void setOccupied(boolean b) {
		// TODO Auto-generated method stub
		
	}

	
	public Set<BoardCell> getAdjList(int i, int j) {
		Set <BoardCell> adjList = new HashSet<>();
		BoardCell cell = new BoardCell();
		Room room = new Room();
		
		
		/*
		Walkways with doors will also connect to the room center the door points to.
		The cell that represents the Room (i.e. connects to walkway) is the cell with a 
		second character of ‘*’ (no other cells in a room should have adjacencies).
		Room center cells ONLY connect to 1) door walkways that enter the room and 
		2) another room center cell if there is a secret passage connecting.*/
		
		//walkways connect to adjacent walkways
		/*
		 * 
		 * if(Board.getCell(row, col).getInitial() == 'W') {
				if(adjCell.getInitial() == 'W') {
					if(!adjCell.isOccupied()) {
						adjList.add(adjCell);
					}
				}
			}
		 * 
		 */
		
		//walkways connect to adjacent walkways
		if(Board.getCell(i, j).isWalkway()) {
			if(adjList.)
		}
		
		
		if (Board.getCell(i, j).isRoomCenter()) {
			
		}
		
		return adjList;
	}
}
