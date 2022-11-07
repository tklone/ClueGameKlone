package clueGame;

import java.util.ArrayList;

public class Solution {
	private Card room;
	private Card weapon;
	private Card person;
	
	public void setSolution(Card room, Card weapon, Card person) {
		ArrayList<Solution> fullSolution = new ArrayList<Solution>();
		Solution items = new Solution(room, weapon, person);
		fullSolution.add(items);
	}
	
	public void setSolutionRoom(Card room) {
		this.room = room;
	}
	
	public Card getSolutionRoom() {
		return room;
	}
	
	public void setSolutionWeapon(Card weapon) {
		this.weapon = weapon;
	}
	
	public Card getSolutionWeapon() {
		return weapon;
	}
	
	public void setSolutionPerson(Card person) {
		this.person = person;
	}
	
	public Card getSolutionPerson() {
		return person;
	}
	
}
