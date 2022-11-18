package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClueGame extends JFrame {
	
	Board board;
	private int currentPlayer = 0;
	KnownCardsPanel knownCards = new KnownCardsPanel(Board.getInstance());
	GameControlPanel gameControl;

	public ClueGame() {
		super();
		board = Board.getInstance();
		gameControl = new GameControlPanel();
		gameControl.setTurn(board.getPlayer(currentPlayer));
		
		//

		//counter to keep track of player
		//int player = 0;
		
		//current player = 0
		// board center
		// known cards east
		// gc south

		this.setSize(1200, 900);
		this.add(knownCards, BorderLayout.EAST);
		this.add(gameControl, BorderLayout.SOUTH);
		this.add(Board.getInstance(), BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.dealCards();
		
		ClueGame actualGame = new ClueGame();
		actualGame.setVisible(true);


		int splashScreen = JOptionPane.INFORMATION_MESSAGE;

		JOptionPane.showMessageDialog(null, "Welcome to Clue! You are Santa Claus. Can you guess the "
				+ "murderer, room, and weapon of the crime first?", "ClueGame", splashScreen);
		
	}

}
