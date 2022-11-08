package clueGame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	private Card suggestRoom;
	private Card suggestPerson;
	private Card suggestWeapon;
	
	//It seems like this would be wrong because I don't know where we would call this
	public Solution createSuggestion(Card room, Card person, Card weapon) {
		Solution computerGuess = new Solution();

		if (!hand.contains(room) && !hand.contains(person) && !hand.contains(weapon) && !seenCards.contains(room) && !seenCards.contains(person) && !seenCards.contains(weapon)) {
			this.suggestRoom = room;
			computerGuess.setSolutionRoom(room);
			this.suggestPerson = person;
			computerGuess.setSolutionPerson(person);
			this.suggestWeapon = weapon;
			computerGuess.setSolutionWeapon(weapon);
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
	
	
	public BoardCell selectTarget(Set <BoardCell> targets) {
		//if not in seen cards or in hand, then it can be selected
		//if multiple rooms, pick a random one
		ArrayList <BoardCell> seenBoardCell = new ArrayList<>();
		BoardCell returnCell = null;
		for (BoardCell c : targets) {
			if (getSeenCards().contains(Board.getCard(c))) {
				seenBoardCell.add(c);
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
