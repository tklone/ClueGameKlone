package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
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
	}
	
	public static void main(String[] args) {
		ClueGame actualGame = new ClueGame();
		actualGame.setVisible(true);
				
	}

}
