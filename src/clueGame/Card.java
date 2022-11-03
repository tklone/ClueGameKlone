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

	/* Duplicate method but we will need to assign them like this somewhere
	public void setCardType(String type) {
		if (type.equals("Person")) {
			cardType = clueGame.CardType.PERSON;
		} else if (type.equals("Weapon")) {
			cardType = clueGame.CardType.WEAPON;
		} else if (type.equals("Room")) {
			cardType = clueGame.CardType.ROOM;
		}
	}*/
	
	public CardType getCardType() {
		return this.cardType;
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
