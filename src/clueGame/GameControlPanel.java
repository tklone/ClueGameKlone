package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {
	
	private static JTextField theGuess;
	private static JTextField theGuessResult;
	public static JButton nextButton = new JButton("NEXT!");
	public static JButton accusationButton = new JButton("Make Accusation");

	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel()  {
		
//		Button to make an accusation
//		Display of the roll of the die
//		Display of whose turn it is
//		Display of guesses made by players and the result
//		Be able to set or update the information in the fields with setters.
		
		setLayout(new GridLayout(1,1));

		//GameControlPanel
		JPanel gameControlPanel = new JPanel();
		gameControlPanel.setLayout(new GridLayout(2,1));
		
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(1,4));
		
//		Button to move to the next player
		
		
	

		// make accusation button
		JPanel accusationButton = createAccusationButton();
		controls.add(accusationButton, BorderLayout.EAST);

		// next button
		JPanel JButton1x4x2 = createNextButton();
		controls.add(JButton1x4x2, BorderLayout.EAST);


		JPanel JPanel0x2 = new JPanel();
		JPanel0x2.setLayout(new GridLayout(1,2));

		// theGuess text field
		JPanel JPanel1x0x1 = createGuess();
		JPanel0x2.add(JPanel1x0x1, BorderLayout.WEST);

		// theGuessResult text field
		JPanel JPanel1x0x2 = createGuessResult();
		JPanel0x2.add(JPanel1x0x2, BorderLayout.EAST);

		gameControlPanel.add(controls, BorderLayout.NORTH);
		gameControlPanel.add(JPanel0x2, BorderLayout.SOUTH);

		add(gameControlPanel, BorderLayout.NORTH);
	}
	
	// creates the NEXT! button
	private JPanel createNextButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(nextButton);
//		nextButton.addActionListener(nextListener);
		return panel;
	}
	// creates the Make Accusation button
	private JPanel createAccusationButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(nextButton);
//		nextButton.addActionListener(nextListener);
		return panel;
	}
	
	//Make "Guess" text field
	private JPanel createGuess() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		theGuess = new JTextField(10);
		theGuess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		theGuess.setEditable(false);
		panel.add(theGuess, BorderLayout.CENTER);

		return panel;
	}

	// creates "Guess Result" text field
	private JPanel createGuessResult() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		theGuessResult = new JTextField(10);
		theGuessResult.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		theGuessResult.setEditable(false);
		panel.add(theGuessResult, BorderLayout.CENTER);

		return panel;
	}
	
	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
//		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true); // make it visible
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", "orange", 0, 0), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");
	}


	//Setters to update data
	public void setTurn(Player computerPlayer, int numSteps) {
		
	}
	
	public void setGuess(String guess) {
	    theGuess.setText(guess);
	}

	public void setGuessResult(String guessResult) {
		theGuessResult.setText(guessResult);
	}
}
