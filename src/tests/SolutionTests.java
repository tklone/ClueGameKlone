package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Color;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class SolutionTests {

	private static Board board;
	private static Card card;
	private static Solution theAnswer;
	
	
	@BeforeAll
	public static void setUpBeforeClass() {
		board = Board.getInstance();
		board.setConfigFiles("Clue_Layout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	@Test
	public void checkAccusation() {
		//solution that is correct
		//solution with wrong person
		//solution with wrong weapon
		//solution with wrong room
	}
	
	
	@Test
	public void disproveSuggestion() {

		//If player has only one matching card it should be returned
		//If players has >1 matching card, returned card should be chosen randomly
		//If player has no matching cards, null is returned
	}


	@Test 
	public void handleSuggestion() {
		//Suggestion no one can disprove returns null
		//Suggestion only suggesting player can disprove returns null
		//Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		Solution suggestion = new Solution(card.getCardType("maid"), card.getCardType("Kitchen"), card.getCardType("axe"));
		
		// set up human player
		String humanColor = Player.getColor();
		HumanPlayer humanPlayer = new HumanPlayer("test", humanColor, 0, 0);
		ArrayList<Card> humanHand = new ArrayList<Card>();
		humanHand.add(board.getCardType("guest"));
		humanHand.add(board.getCardType("houseKeeping"));
		humanHand.add(board.getCardType("suitcase"));
		humanPlayer.setCards(humanHand);
		playerList.add(humanPlayer);	
		
		// set up three computer players
		Color computer1Color = board.convertColor("Orange");
		ComputerPlayer computer1Player = new ComputerPlayer("test", computer1Color, 1, 1);
		ArrayList<Card> computer1Hand = new ArrayList<Card>();
		computer1Hand.add(board.getCard("bellhop"));
		computer1Hand.add(board.getCard("Elevator"));
		computer1Hand.add(board.getCard("mop"));
		computer1Player.setCards(computer1Hand);
		playerList.add(computer1Player);
	}

}
