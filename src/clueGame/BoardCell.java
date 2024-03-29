package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

public class BoardCell {

	private int row, col;
	private char initial;
	private String label;
	// private DoorDirection doorDirection;
	private boolean roomLabel = false;
	private boolean roomCenter;
	private char secretPassage;
	public Boolean doorway = false;
	public boolean isRoom = false;
	public boolean isOccupied = false;
	private boolean isTarget = false;

//	private boolean isHighlighted = false;

	private Set<BoardCell> adjList = new HashSet<>();
	private Boolean isSP = false;

	private int cellDim = 10;

	public BoardCell() {
		super();
	}

	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}

//	public void setIsHighlighted(boolean highlight) {
//		this.isHighlighted = highlight;
//	}
//	
//	public boolean getIsHighlighted() {
//		return isHighlighted;
//	}

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
		if ((label.length() > 1) && (label.charAt(0) == 'W') && (label.charAt(1) == '>' || label.charAt(1) == '<'
				|| label.charAt(1) == '^' || label.charAt(1) == 'v')) {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		if (label.length() > 1 && label.charAt(0) == 'W') {
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

	// secret passage bool and char
	public Boolean isSecretPassage() {
		isSP = false;
		Character c = label.charAt(0);
		String currentLabel = this.getLabel();
		if (currentLabel.equals("GE") || currentLabel.equals("EG") || currentLabel.equals("RS")
				|| currentLabel.equals("SR")) {
			isSP = true;
		}
		return isSP;
	}

	public char getSecretPassage() {
		String label = this.getLabel();
		secretPassage = label.charAt(1);
		return secretPassage;
	}

	// getter and setter for Initial
	public char getInitial() {
		return initial;
	}

	public void setInitial(String str) {
		initial = str.charAt(0);
	}

	// getters and setters for rows and columns
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

	// getter and setter and bool for Label
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

	public void drawCell(Graphics g, int cellHeight, int cellWidth, boolean isHighlighted) {
		// Start positions
		int xStart = this.col * cellWidth;
		int yStart = this.row * cellHeight;
		if (this.isRoom()) {
			// color cell TAN
			Color tan = new Color(194, 167, 128);
			g.setColor(tan);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
		}
		if (this.isWalkway()) {
			// color cell GRAY
			Color gray = new Color(112, 111, 108);
			g.setColor(gray);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
			g.setColor(Color.black);
			g.drawRect(xStart, yStart, cellWidth, cellHeight);
		}
		if (this.isDoorway()) {
			DoorDirection dd = this.getDoorDirection();
			Color gray = new Color(112, 111, 108);
			g.setColor(gray);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);

			g.setColor(Color.WHITE);
			if (dd == DoorDirection.DOWN) {
				// make the SOUTH line thicker
				g.fillRect(xStart, yStart + 20, cellWidth, cellHeight - 20);
			} else if (dd == DoorDirection.UP) {
				// make the NORTH line thicker
				g.fillRect(xStart, yStart, cellWidth, cellHeight - 20);
			} else if (dd == DoorDirection.LEFT) {
				// make the WEST line thicker
				g.fillRect(xStart, yStart, cellWidth - 29, cellHeight);
			} else if (dd == DoorDirection.RIGHT) {
				// make the EAST line thicker
				g.fillRect(xStart + 29, yStart, cellWidth, cellHeight);
			}
			g.setColor(Color.black);
			g.drawRect(xStart, yStart, cellWidth, cellHeight);//
		}
		if (this.getInitial() == 'X') {
			// color cell BLACK
			g.setColor(Color.BLACK);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
		}
		if (this.isSecretPassage()) {
			// color cell RED
			Color red = new Color(143, 63, 63);
			g.setColor(red);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
		}
//		if (isHighlighted.contains(this)) {  //Maybe make a getHighlightedCells method

		// add a conditional if cell currently on, then set colors
		if (isHighlighted) {
			Color cyan = new Color(52, 235, 229);
			g.setColor(cyan);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
			g.setColor(Color.BLACK);
			g.drawRect(xStart, yStart, cellWidth, cellHeight);
		}
	}

	// bool isTarget to get
	// if contains the cell we are on
	// tell board to repaint after we get the targets
	public boolean isTarget() {
		if (Board.getInstance().getTargets().contains(this)) {
			isTarget = true;
		}
		return isTarget;
	}

	public void drawTarget(Graphics g, int cellHeight, int cellWidth, Boolean containsTarget) {

		if (containsTarget) {
			int xStart = this.col * cellWidth;
			int yStart = this.row * cellHeight;

			Color cyan = new Color(52, 235, 229);
			g.setColor(cyan);
			g.fillRect(xStart, yStart, cellWidth, cellHeight);
			g.setColor(Color.BLACK);
			g.drawRect(xStart, yStart, cellWidth, cellHeight);
		}
	}

	public boolean contains(int mouseX, int mouseY, int cellHeight, int cellWidth) {
		Rectangle cell = new Rectangle(col, row, cellWidth, cellHeight);
		if (cell.contains(new Point(mouseX, mouseY))) {
			return true;
		}
		return false;
	} 

}
