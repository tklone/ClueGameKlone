package clueGame;

public class Card {
	String cardName;
	CardType cardType;
	
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
		return this.cardType;
	}
	
	public Boolean equals(Card target) {
		//Something should go in here
		return null;
	}
}
