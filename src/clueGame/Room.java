package clueGame;

public class Room {
	
	private String name;
	private char c;
	private BoardCell centerCell;
	private BoardCell labelCell;

	public Room() {
		super();
	}

	public String getName() {
		System.out.println(name);
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
