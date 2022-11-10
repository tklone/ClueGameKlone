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
	public GameControlPanel() {

//		Display of the roll of the die
//		Display of whose turn it is
//		Display of guesses made by players and the result
//		Be able to set or update the information in the fields with setters.

		//layout with 2 rows, 0 because we can change as we go
		setLayout(new GridLayout(2, 0));

		// GameControlPanel
		JPanel gameControlPanel = new JPanel();
		gameControlPanel.setLayout(new GridLayout(2, 1));

		JPanel rightSide = new JPanel();
		rightSide.setLayout(new GridLayout(1, 4));

		// make accusation button
		JPanel accusationButton = createAccusationButton();
		rightSide.add(accusationButton, BorderLayout.WEST);

		// Button to move to the next player
		JPanel nextButton = createNextButton();
		rightSide.add(nextButton, BorderLayout.EAST);

		gameControlPanel.add(rightSide, BorderLayout.NORTH);

		JPanel textFields = new JPanel();
		textFields.setLayout(new GridLayout(1, 2));

		// theGuess text field
		JPanel guessTextField = createGuess();
		textFields.add(guessTextField, BorderLayout.WEST);

		// theGuessResult text field
		JPanel guessResultTextField = createGuessResult();
		textFields.add(guessResultTextField, BorderLayout.EAST);

		gameControlPanel.add(textFields, BorderLayout.SOUTH);

		add(gameControlPanel, BorderLayout.NORTH);
	}

	// creates the Make Accusation button
	private JPanel createAccusationButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(accusationButton);
		return panel;
	}

	// creates the NEXT! button
	private JPanel createNextButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(nextButton);
		return panel;
	}

	// Make "Guess" text field
	private JPanel createGuess() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		theGuess = new JTextField(5);
		theGuess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		panel.add(theGuess, BorderLayout.CENTER);

		return panel;
	}

	// creates "Guess Result" text field
	private JPanel createGuessResult() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		theGuessResult = new JTextField(10);
		theGuessResult.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		panel.add(theGuessResult, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel(); // create the panel
		JFrame frame = new JFrame(); // create the frame
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180); // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close

		frame.setVisible(true); // make it visible

		// test filling in the data
		panel.setTurn(new ComputerPlayer("Col. Mustard", "orange", 0, 0), 5);
		panel.setGuess("I have no guess!");
		panel.setGuessResult("So you have nothing?");
	}

	// Setters to update data
	public void setTurn(Player computerPlayer, int numSteps) {

	}

	public void setGuess(String guess) {
		theGuess.setText(guess);
	}

	public void setGuessResult(String guessResult) {
		theGuessResult.setText(guessResult);
	}
}
