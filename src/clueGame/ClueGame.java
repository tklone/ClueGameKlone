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
	KnownCardsPanel knownCards = new KnownCardsPanel(Board.getInstance());
	GameControlPanel gameControl;
	int diceRoll;
	BoardCell startPos;

	public ClueGame() {
		super();
		board = Board.getInstance();
		gameControl = new GameControlPanel();
		currentPlayer = board.getCurrentPlayer();
		gameControl.setTurn(currentPlayer);
		board.setDiceRoll(0);

		this.setSize(1200, 900);
		this.add(knownCards, BorderLayout.EAST);
		this.add(gameControl, BorderLayout.SOUTH);
		this.add(Board.getInstance(), BorderLayout.CENTER);

	}

	class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getCurrentPlayer() instanceof HumanPlayer) {
				//Have not written turnFinished method yet
				if (!currentPlayer.turnFinshed() && !board.diceRolled()) {
					board.calcTargets(currentPlayer.getLocation(), board.rollDice());
					
				}
			}
			
			board.nextTurn();

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
		
//		board.rollDice();

	}

}
