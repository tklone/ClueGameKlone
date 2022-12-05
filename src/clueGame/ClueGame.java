package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClueGame extends JFrame {

	Board board;
	private Player currentPlayer;
	KnownCardsPanel knownCards;
	GameControlPanel gameControl;
	SuggestionPanel suggestionPanel;
	AccusationPanel accusationPanel;
	KnownCardsPanel knownCardsPanel;
	int diceRoll;
	BoardCell startPos;

	public ClueGame() {
		super();
		board = Board.getInstance();
		gameControl = new GameControlPanel();
		suggestionPanel = new SuggestionPanel();
		accusationPanel = new AccusationPanel();
		currentPlayer = board.getCurrentPlayer();
		knownCardsPanel = new KnownCardsPanel();
		knownCards = new KnownCardsPanel();
		board.setKnownCards(knownCards);
		board.setControl(gameControl);
		board.setAccusation(accusationPanel);
		gameControl.setTurn(currentPlayer);

		
		this.setSize(1200, 900);
		this.add(knownCards, BorderLayout.EAST);
		this.add(gameControl, BorderLayout.SOUTH);
		this.add(Board.getInstance(), BorderLayout.CENTER);

	}



	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetupHOM");
		board.initialize();
		board.dealCards();
		
		ClueGame actualGame = new ClueGame();
		actualGame.setVisible(true);
		
		int splashScreen = JOptionPane.INFORMATION_MESSAGE;

		JOptionPane.showMessageDialog(null, "Welcome to Clue! You are William Harvey. Can you guess the "
				+ "murderer, room, and weapon of the crime first?", "ClueGame", splashScreen);
		
		board.firstTurn();
//		board.iterateCurrent();
		
		
		
		//accusation panel is still small window??
		if(board.getGameOver()) {
			//do something
		}

	}

}
