package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String name;
	private Color color;
	private int row, col;
	private ArrayList <Card> hand = new ArrayList<>();
	
	//I think this is wrong, but I'm not entirely sure how we'd do it
//	Player() {
//		Player humanPlayer = new HumanPlayer();
//		Player computerPlayers[] = new ComputerPlayer[5];
//	}
	
	
	public void updateHand(Card card) {
		hand.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
}
