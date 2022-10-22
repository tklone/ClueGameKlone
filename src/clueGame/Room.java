package clueGame;

public class Room {
	
	private String name;
	private char c;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell spStart;
	private BoardCell spEnd;
	private BoardCell doorway;
	private Boolean hasSP;

	public Room() {
		super();
	}
	
	public void setHasSP(Boolean hasSecretPassage) {
		hasSP = hasSecretPassage;
	}
	
	public Boolean getHasSP() {
		return hasSP;
	}
	
	public void setSPStart(BoardCell startCell) {
		spStart = startCell;
	}
	
	public BoardCell getSPStart() {
		return spStart;
	}
	
	public void setSPEnd(BoardCell endCell) {
		spEnd = endCell;
	}
	
	public BoardCell getSPEnd() {
		return spEnd;
	}
	
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

	public BoardCell getLabelCell() {
		return labelCell;
	}

	public BoardCell getCenterCell() {
		return centerCell;
	}

	public void setChar(char character) {
		c = character;
	}

}
