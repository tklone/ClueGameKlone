package clueGame;

public class Room {
	
	private String name;
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

}
