package clueGame;


import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	private Card suggestRoom;
	private Card suggestPerson;
	private Card suggestWeapon;

	// It seems like this would be wrong because I don't know where we would call
	// this
	public Solution createSuggestion(Card room, Card weapon, Card person) {
		Solution computerGuess = new Solution();

		if (!hand.contains(room) && !hand.contains(person) && !hand.contains(weapon) && !seenCards.contains(room)
				&& !seenCards.contains(person) && !seenCards.contains(weapon)) {
			this.suggestRoom = room;
//			computerGuess.setSolutionRoom(room);
			this.suggestPerson = person;
//			computerGuess.setSolutionPerson(person);
			this.suggestWeapon = weapon;
//			computerGuess.setSolutionWeapon(weapon);
			
			computerGuess = new Solution(room, weapon, person);
		}

		return computerGuess;
	}

	public Card getRoomSuggest() {
		return suggestRoom;
	}

	public Card getPersonSuggest() {
		return suggestPerson;
	}

	public Card getWeaponSuggest() {
		return suggestWeapon;
	}

	public BoardCell selectTarget() {
		// if not in seen cards or in hand, then it can be selected
		// if multiple rooms, pick a random one
		ArrayList<BoardCell> seenBoardCell = new ArrayList<>();
		BoardCell returnCell = null;
		
		//Board.getTargets() is nothing, DNE
		for (BoardCell c : Board.getTargets()) {
			if (c.isRoomCenter()) {
				Card currentCard = Board.getCard(c);
				if (!seenCards.contains(currentCard)) {
					//I don't think we want to update seen here?
//					updateSeen(currentCard);
					seenBoardCell.add(c);
				}
			}
		}

		
		if (seenBoardCell.size() == 1) {
			returnCell = seenBoardCell.get(0);
		} else if (seenBoardCell.size() > 1) {
			Random rand = new Random();
			int randIndex = rand.nextInt(seenBoardCell.size() - 1);
			returnCell = seenBoardCell.get(randIndex);
		} else if (seenBoardCell.size() == 0) {
			returnCell = null;
		}
		return returnCell;
	}

}
