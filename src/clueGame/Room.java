package clueGame;

public class Room {
	
	private String name;
	private char c;
	private BoardCell centerCell;
	private BoardCell labelCell;

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
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
		// TODO Auto-generated method stub
		c = character;
	}

}
