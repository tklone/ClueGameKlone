package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

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

		
		computerGuess.setSolutionRoom(rooms.get(randRooms));
		computerGuess.setSolutionPerson(people.get(randPeople));
		computerGuess.setSolutionWeapon(weapons.get(randWeapons));

		return computerGuess;
	}
	
	public BoardCell selectTarget() {
		return null;
	}
	
}
