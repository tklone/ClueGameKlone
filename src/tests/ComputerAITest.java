package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Room;

class ComputerAITest {

	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();

	}


	@Test
	void testCreateSuggestion() {
		// Room matches current location
		// If only one weapon not seen, it's selected
		// If only one person not seen, it's selected (can be same test as weapon)
		// If multiple weapons not seen, one of them is randomly selected
		// If multiple persons not seen, one of them is randomly selected
		
		ComputerPlayer computerPlayer = new ComputerPlayer("The Grinch", "Green", 25, 16);
		
		ArrayList <Card> tempHand = new ArrayList<>();
		Card personCard = new Card();
		personCard.setCardType(CardType.PERSON);
		personCard.setName("Rudolph");
		tempHand.add(personCard);
		Card weaponCard = new Card();
		weaponCard.setCardType(CardType.WEAPON);
		weaponCard.setName("Nutcracker");
		tempHand.add(weaponCard);
		Card roomCard = new Card();
		roomCard.setCardType(CardType.ROOM);
		roomCard.setName("Sleigh Storage");
		tempHand.add(roomCard);
		
		
		computerPlayer.createSuggestion(board.getCard("Christmas Tree Factory"), board.getCard("Grinch"), board.getCard("String of Lights"));
		assertTrue(!tempHand.contains(computerPlayer.getRoomSuggest()));
		assertTrue(!tempHand.contains(computerPlayer.getPersonSuggest()));
		assertTrue(!tempHand.contains(computerPlayer.getWeaponSuggest()));		
	}

	
	@Test
	void testSelectTargets() {
		Set<BoardCell> tempTargets = new HashSet<BoardCell>();
		
		ArrayList <Card> tempHand = new ArrayList<>();
		Card personCard = new Card();
		personCard.setCardType(CardType.PERSON);
		personCard.setName("Rudolph");
		tempHand.add(personCard);
		Card weaponCard = new Card();
		weaponCard.setCardType(CardType.WEAPON);
		weaponCard.setName("Nutcracker");
		tempHand.add(weaponCard);
		Card roomCard = new Card();
		roomCard.setCardType(CardType.ROOM);
		roomCard.setName("Sleigh Storage");
		tempHand.add(roomCard);
		
		ComputerPlayer computerPlayer = new ComputerPlayer("The Grinch", "Green", 25, 16);

		board.calcTargets(computerPlayer.getLocation(), 1);
		tempTargets = board.getTargets();
		//Making sure no room is in the targetList
		for (BoardCell c : tempTargets) {
			assertTrue(!c.isRoomCenter());
		}
		tempTargets.clear();
		
		//if room in list that has not been seen, select it
		board.findAllTargets(board.getCell(25, 16), 7);
		board.calcTargets(computerPlayer.getLocation(), 7);
		tempTargets = board.getTargets();
		
		board.findAllTargets(board.getCell(25, 16), 7);
		BoardCell target = computerPlayer.selectTarget();
		assertEquals(board.getCell(20, 12), target);
		//Making sure that the room center is in the target list
		
		
		
	}
}
