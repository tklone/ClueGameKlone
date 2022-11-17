package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClueGame extends JFrame{

	public ClueGame() {
		// TODO Auto-generated constructor stub
		super();
		
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.dealCards();
		
		KnownCardsPanel knownCards = new KnownCardsPanel(board);
		GameControlPanel gameControl = new GameControlPanel();
		
		//board center
		//known cards east
		//gc south
		
		this.setSize(1200, 900);
		this.add(knownCards, BorderLayout.EAST);
		this.add(gameControl, BorderLayout.SOUTH);
		this.add(board, BorderLayout.CENTER);
		
		int splashScreen = JOptionPane.INFORMATION_MESSAGE;
		
		JOptionPane.showMessageDialog(null, "Welcome to Clue! You are Santa Claus. Can you guess the "
						+ "murderer, room, and weapon of the crime first?", "ClueGame", splashScreen);
		

		//When the splash screen is closed, we process the first players turn. 
		//This typically should be the human player
		//if(when) user selects ok:

			// deal deck and display on the known cards
			
			// automatically start the game by clicking the next button

	}
	
	public static void main(String[] args) {
		ClueGame actualGame = new ClueGame();
		actualGame.setVisible(true);
				
	}

}
