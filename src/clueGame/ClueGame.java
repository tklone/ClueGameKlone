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
	private int currentPlayerInt = 0;
	private Player currentPlayer;
	KnownCardsPanel knownCards = new KnownCardsPanel(Board.getInstance());
	GameControlPanel gameControl;
	int diceRoll;
	BoardCell startPos;

	public ClueGame() {
		super();
		board = Board.getInstance();
		gameControl = new GameControlPanel();
		currentPlayer = board.getPlayer(currentPlayerInt);
		gameControl.setTurn(currentPlayer);

		this.setSize(1200, 900);
		this.add(knownCards, BorderLayout.EAST);
		this.add(gameControl, BorderLayout.SOUTH);
		this.add(Board.getInstance(), BorderLayout.CENTER);

	}

	
	class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("NEXT PUSHED");
			// need to keep track of when its human player turn
			if (currentPlayer instanceof HumanPlayer) {
//				if (!currentPlayer.isFinished()) {
				if (currentPlayerInt < 6) {
					currentPlayerInt++;
				} else if (currentPlayerInt >= 6) {
					currentPlayerInt = 0;
				}
				currentPlayer = board.getPlayer(currentPlayerInt);
				diceRoll = board.rollDice();
				startPos = currentPlayer.getLocation();
				board.calcTargets(startPos, diceRoll);
//				board.highlightTargets(board.getTargets());
//					}
			}
		}

//		}

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
