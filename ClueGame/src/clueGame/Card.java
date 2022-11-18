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
