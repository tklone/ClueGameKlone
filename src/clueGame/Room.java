package clueGame;

public class Room {
	
	private String name;
	private char c;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell sp;
	private BoardCell doorway;

	public Room() {
		super();
	}
	
	public void setSP(BoardCell cell) {
		sp = cell;
	}
	
	public BoardCell getSP() {
		return sp;
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
