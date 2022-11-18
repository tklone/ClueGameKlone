package clueGame;


public class Solution {
	private Card room;
	private Card weapon;
	private Card person;
	
	public Solution() {
		super();
	}
	
//	public Solution(Card room, Card weapon, Card person) {
//		
//	}
//	
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
	
	public void setSolution(Card person, Card room, Card weapon) {
		this.room = room;
		this.weapon = weapon;
		this.person = person;
	}
	
}
