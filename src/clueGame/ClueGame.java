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
	
	class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// need to keep track of when its human player turn
			
			// if human player
				// if the humans turn is not finished and they havent rolled
				// roll dice
			
					//repaint if not the first turn of the human
			
				// if the dice is rolled but have not chosen a target
		
			// if it is a computer players turn
			// roll their dice and move player automatically
			// wait for user to click next for the next computer player?
	
			
			
				// go to the next player
				// wait for user to click the next button
		}
			
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
