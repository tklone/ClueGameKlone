package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Room {
	
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell secretPassageCell;
	private Set<BoardCell> doorway = new HashSet<>();
	private Boolean hasSP = false;
	private char c;

	public Room() {
		super();
	}
	
	public void setSecretPassageCell(BoardCell cell) {
		secretPassageCell = cell;
	}
	
	public BoardCell getSecretPassageCell() {
		return secretPassageCell;
	}
	
	public void setHasSP(Boolean hasSecretPassage) {
		hasSP = hasSecretPassage;
	}
	
	public Boolean getHasSP() {
		return hasSP;
	}
	
	
	public void addDoorway(BoardCell cell) {
		doorway.add(cell);
	}
	
	public Set<BoardCell> getDoorway() {
		return doorway;
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String roomName) {
		name = roomName;
	}

	public void setLabelCell(BoardCell cell) {
		labelCell = cell;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}

	public void setCenterCell(BoardCell cell) {
		centerCell = cell;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}

	public void setChar(char character) {
		c = character;
	}

}
