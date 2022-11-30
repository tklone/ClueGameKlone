package clueGame;

import java.awt.Color;

public class Card {
	String cardName;
	CardType cardType;
	String cardOwner;
	
	public void setCardOwner(String color) {
		this.cardOwner = color;
	}
	
	public Color getCardOwner() {
		if (cardOwner.equals("RED")) {
			Color col = new Color(226, 30, 30);
			return col;
		} else if (cardOwner.equals("PINK")) {
			Color col = new Color(250 , 50, 230);
			return col;
		} else if (cardOwner.equals("GREEN")) {
			Color col = new Color(28, 196, 68);
			return col;
		} else if (cardOwner.equals("BROWN")) {
			Color col = new Color(137, 91, 46);
			return col;
		} else if (cardOwner.equals("BLUE")) {
			Color col = new Color(37, 170, 219);
			return col;
		} else if (cardOwner.equals("YELLOW")) {
			Color col = new Color(243, 236, 16);
			return col;
		}
		return 	Color.BLACK;
	}
	
	public void setName(String cardName) {
		this.cardName = cardName;
	}
	
	public String getName() {
		return this.cardName;
	}
	
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	
	public Boolean equals(Card target) {
		if(target.getCardType().equals(this.cardType)){
			return true;
		}
		else {
			return false;
		}
	}
}
