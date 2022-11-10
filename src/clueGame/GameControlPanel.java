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

		// larger panel to add smaller panels to
		JPanel gameControlPanel = new JPanel();
		gameControlPanel.setLayout(new GridLayout(1, 1));
		
		//upper half(turn, roll, and buttons)
		JPanel upperHalf = new JPanel();
		upperHalf.setLayout(new GridLayout(1, 4));

		// next or accusations panel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));

		// adding "make accusation" to left and "next" to right
		JPanel accusationButton = createAccusationButton();
		buttonsPanel.add(accusationButton, BorderLayout.WEST);
		JPanel nextButton = createNextButton();
		buttonsPanel.add(nextButton, BorderLayout.EAST);

		upperHalf.add(buttonsPanel, BorderLayout.EAST);

		// guess panel
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1, 2));

		// adding theGuess and theGuessResult to left and right sides of panel,
		// respectively
		JPanel guessTextField = createGuess();
		guessPanel.add(guessTextField, BorderLayout.WEST);
		JPanel theGuessResult = createGuessResult();
		guessPanel.add(theGuessResult, BorderLayout.EAST);

		gameControlPanel.add(guessPanel, BorderLayout.SOUTH);
		gameControlPanel.add(upperHalf, BorderLayout.NORTH);

		add(gameControlPanel);

	}

	private JPanel createGuess() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		theGuess = new JTextField();
		theGuess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}

	private JPanel createGuessResult() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		theGuessResult = new JTextField();
		theGuessResult.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}

	private JPanel createAccusationButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(accusationButton);
		return panel;
	}

	private JPanel createNextButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(nextButton);
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
