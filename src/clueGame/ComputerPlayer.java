package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

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
		
//		Random randRoomGuessNum = new Random();
//		Random randPersonGuessNum = new Random();
//		Random randWeaponGuessNum = new Random();
//
//		int upperBoundRooms = room.size() - 1;
//		int upperBoundPeople = person.size() - 1;
//		int upperBoundWeapons = weapon.size() - 1;
//		
//		
//		int randRooms = randRoomGuessNum.nextInt(upperBoundRooms);
//		int randPeople = randPersonGuessNum.nextInt(upperBoundPeople);
//		int randWeapons = randWeaponGuessNum.nextInt(upperBoundWeapons);

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
	
	
	public BoardCell selectTarget() {
		return null;
	}
	
}
