package clueGame;

public class Room {
	
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell secretPassageCell;
	private BoardCell doorway;
	private Boolean hasSP;
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
	
//	public void setSPStart(BoardCell startCell) {
//		spStart = startCell;
//	}
//	
//	public BoardCell getSPStart() {
//		return spStart;
//	}
//	
//	public void setSPEnd(BoardCell endCell) {
//		spEnd = endCell;
//	}
//	
//	public BoardCell getSPEnd() {
//		return spEnd;
//	}
	
	public void setDoorway(BoardCell cell) {
		doorway = cell;
	}
	
	public BoardCell getDoorway() {
		return doorway;
	}

	public String getName() {
//		System.out.println(name);
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
