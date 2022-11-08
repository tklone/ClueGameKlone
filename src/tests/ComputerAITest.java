package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;

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
//		assertTrue(computerPlayer.getRoomSuggest() != board.getCard("Sleigh Storage"));
		
	}

}
