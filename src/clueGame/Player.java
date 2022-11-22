package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	protected ArrayList<Card> hand = new ArrayList<>();
	protected Set<Card> seenCards = new HashSet<Card>();
	private Boolean equalsBool = false;
	private String disproverColor;
	private Boolean turnComplete = false;
	private int startCol;
	private int startRow;
	

	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = color;
		startRow = row;
		startCol = col;
		this.row = row;
		this.col = col;
	}

	public void updateHand(Card card) {
		hand.add(card);
	}
	
	public Set<Card> getSeenCards() {
		return seenCards;
	}

	public void updateSeen(Card seenCard, String color) {
		this.disproverColor = color;
		seenCards.add(seenCard);
	}
	
	public String getDisproverColor() {
		return disproverColor;
	}

	public Card disproveSuggestion(Solution guess) {
		ArrayList<Card> matchingCards = new ArrayList<>();
	
		for (Card c : hand) {
			if (c.getName().equals(guess.getSolutionPerson().getName())) {
				matchingCards.add(c);
			}
			if (c.getName().equals(guess.getSolutionRoom().getName())) {
				matchingCards.add(c);
			}
			if (c.getName().equals(guess.getSolutionWeapon().getName())) {
				matchingCards.add(c);
			}

		}

		Random rand = new Random();
		
		if (matchingCards.size() == 0) {
			return null;
		}
		
		int randomNum = rand.nextInt(matchingCards.size());
		return matchingCards.get(randomNum);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Color getColorPlayer(String color) {

		if (color.equals("RED")) {
			Color col = new Color(226, 30, 30);
			return col;
		} else if (color.equals("PINK")) {
			Color col = new Color(250 , 50, 230);
			return col;
		} else if (color.equals("GREEN")) {
			Color col = new Color(28, 196, 68);
			return col;
		} else if (color.equals("BROWN")) {
			Color col = new Color(137, 91, 46);
			return col;
		} else if (color.equals("BLUE")) {
			Color col = new Color(37, 170, 219);
			return col;
		} else if (color.equals("YELLOW")) {
			Color col = new Color(243, 236, 16);
			return col;
		}
		return 	Color.BLACK;
	}

	public String getColor() {
		return color;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
	
	public BoardCell getLocation() {
		return Board.getCell(this.row, this.col);
	}
	
	public void updatePosition(BoardCell cell) {
		startCol = this.col;
		startRow = this.row;
		this.row = cell.getRow();
		this.col = cell.getCol();
	}
	
	public Boolean equals(Player p) {
		return p.getName().equals(this.getName());
	}
	
	public void drawPlayer(Graphics g, int cellHeight, int cellWidth) {
		int xStart = this.col * cellWidth;
		int yStart = this.row * cellHeight;
				
		g.setColor(getColorPlayer(this.color));
		g.fillOval(xStart + 5, yStart, cellHeight, cellHeight);
		g.setColor(Color.BLACK);
		g.drawOval(xStart + 5, yStart,  cellHeight, cellHeight);

	}

	public boolean turnFinshed() {
		if (startCol != this.col && startRow != this.row) {
			turnComplete = true;
		}
		return turnComplete;
	}
	
	public void setTurnFinished(Boolean fin) {
		turnComplete = fin;
	}
	public boolean getTurnFinished() {
		return turnComplete;
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public int getStartCol() {
		return startCol;
	}
}
