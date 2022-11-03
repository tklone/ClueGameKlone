package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private ArrayList <Card> hand = new ArrayList<>();
	
	//I think this is wrong, but I'm not entirely sure how we'd do it
//	Player() {
//		Player humanPlayer = new HumanPlayer();
//		Player computerPlayers[] = new ComputerPlayer[5];
//	}
	
	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	
	public void updateHand(Card card) {
		hand.add(card);
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
	
	public String getColor() {
		return color;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
}
