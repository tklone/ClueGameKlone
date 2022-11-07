package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	
	//It seems like this would be wrong because I don't know where we would call this
	public Solution createSuggestion(ArrayList<Card> rooms, ArrayList<Card> people, ArrayList<Card> weapons) {
		Solution computerGuess = new Solution();
		Random randRoomGuessNum = new Random();
		Random randPersonGuessNum = new Random();
		Random randWeaponGuessNum = new Random();

		int upperBoundRooms = rooms.size() - 1;
		int upperBoundPeople = people.size() - 1;
		int upperBoundWeapons = weapons.size() - 1;
		
		
		int randRooms = randRoomGuessNum.nextInt(upperBoundRooms);
		int randPeople = randPersonGuessNum.nextInt(upperBoundPeople);
		int randWeapons = randWeaponGuessNum.nextInt(upperBoundWeapons);

		if (!hand.contains(rooms.get(randRooms)) && !hand.contains(people.get(randPeople)) && !hand.contains(weapons.get(randWeapons)) && !seenCards.contains(rooms.get(randRooms)) && !seenCards.contains(people.get(randPeople)) && !seenCards.contains(weapons.get(randWeapons))) {
			computerGuess.setSolutionRoom(rooms.get(randRooms));
			computerGuess.setSolutionPerson(people.get(randPeople));
			computerGuess.setSolutionWeapon(weapons.get(randWeapons));
		}
		
		return computerGuess;
	}
	
	public BoardCell selectTarget() {
		return null;
	}
	
}
