package clueGame;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {

	private static JTextField theGuess;
	private static JTextField theGuessResult;
	private static JTextField whoseTurn;
	private static JTextField roll;
	private static int getRollVal;
	private static int counter = 0;
	JButton nextButton = new JButton("NEXT!");
	public static JButton accusationButton = new JButton("Make Accusation");

	public GameControlPanel() {

		setLayout(new GridLayout(1, 1));

		// larger panel to add smaller panels to
		JPanel gameControlPanel = new JPanel();
		gameControlPanel.setLayout(new GridLayout(2, 1));

		// upper half(turn, roll, and buttons)
		JPanel upperHalf = new JPanel();
		upperHalf.setLayout(new GridLayout(1, 4));

		// next or accusations panel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));

		// adding "make accusation" to left and "next" to right
		JButton accusationButton = createAccusationButton();
		buttonsPanel.add(accusationButton, BorderLayout.WEST);
		accusationButton.addActionListener(new MakeAccusationButtonListener());
		//Next Button
		JButton nextButton = createNextButton();
		buttonsPanel.add(nextButton, BorderLayout.EAST);
		nextButton.addActionListener(new NextButtonListener());

		// left side of upper half
		JPanel upperLeft = new JPanel();

		// adding turn
		JPanel turnPanel = new JPanel();
		turnPanel.setLayout(new GridLayout(1, 1));
		// adding roll
		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new GridLayout(1, 1));

		// turn
		JPanel turnTextField = createTurnTextField();
		turnPanel.add(turnTextField, BorderLayout.SOUTH);

		// roll
		JPanel rollTextField = createRollTextField();
		rollPanel.add(rollTextField, BorderLayout.EAST);

		upperHalf.add(turnPanel, BorderLayout.WEST);
		upperHalf.add(rollPanel, BorderLayout.WEST);
		upperHalf.add(buttonsPanel, BorderLayout.EAST);

		// guess panel
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(0, 2));

		// adding theGuess and theGuessResult to left and right sides of panel,
		// respectively
		JPanel guessTextField = createGuess();
		guessPanel.add(guessTextField, BorderLayout.WEST);
		JPanel theGuessResult = createGuessResult();
		guessPanel.add(theGuessResult, BorderLayout.EAST);

		gameControlPanel.add(upperHalf, BorderLayout.NORTH);
		gameControlPanel.add(guessPanel, BorderLayout.SOUTH);

		add(gameControlPanel);

	}

	class NextButtonListener implements ActionListener {

		NextButtonListener() {
			nextButton.addActionListener(this);
		}

//		@Override
		public void actionPerformed(ActionEvent e) {

			Board board = Board.getInstance();
			getRollVal = board.getDiceRoll();

			board.iterateCurrent();

			board.nextTurn();
			setTurn(board.getCurrentPlayer());
			setRoll(board.getDiceRoll());

		}
	}
	
	class MakeAccusationButtonListener implements ActionListener {
		MakeAccusationButtonListener() {
			accusationButton.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Board board = Board.getInstance();
			//String weapon needs to get weapon from a menu
			//Player player needs to get a player from a menu
			String weapon;
			Player player;
			
			//This also feels wrong...
			createDropDownMenu();
			
		}
	}

	
	//This all feels wrong
	private JFrame createDropDownMenu() {
		JFrame frame = new JFrame();
		//This feels wrong
		Board board = Board.getInstance();
		ArrayList<Card> weaponsCards = new ArrayList<Card>(board.getWeaponsCards());
		Card[] weaponOptions = weaponsCards.toArray(new Card[0]);
		JComboBox<Card> jComboBox = new JComboBox<>(weaponOptions);
		frame.add(jComboBox);
		return frame;
	}
	
	private JPanel createTurnTextField() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JLabel label = new JLabel("Whose Turn?");
		whoseTurn = new JTextField();
		panel.add(label, BorderLayout.NORTH);
		panel.add(whoseTurn, BorderLayout.SOUTH);
		return panel;

	}

	private JPanel createRollTextField() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		JLabel label = new JLabel("Roll:");
		roll = new JTextField(10);
		panel.add(label, BorderLayout.WEST);
		panel.add(roll, BorderLayout.EAST);
		return panel;
	}

	private JPanel createGuess() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		theGuess = new JTextField(10);
		theGuess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		panel.add(theGuess);
		return panel;
	}

	private JPanel createGuessResult() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		theGuessResult = new JTextField();
		theGuessResult.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		panel.add(theGuessResult);
		return panel;
	}

	private JButton createAccusationButton() {
		JButton button = new JButton();
		button.setLayout(new GridLayout(1, 1));
		button.add(accusationButton);
		return button;
	}

	private JButton createNextButton() {
		JButton nextButton = new JButton("NEXT!");
		nextButton.setLayout(new GridLayout(1, 1));
		return nextButton;
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

//		panel.setRoll(board.rollDice());

//		NextButtonListener nextButtonListener = new NextButtonListener();

//		nextButtonListener.actionPerformed(e);
	}

	// Setters to update data
	public void setTurn(Player player) { // , Integer numSteps
		whoseTurn.setText(player.getName());
//		roll.setText(numSteps.toString());
	}

	public void setGuess(String guess) {
		theGuess.setText(guess);
	}

	public void setGuessResult(String guessResult) {
		theGuessResult.setText(guessResult);
	}

	public void setRoll(int rolledDice) {
		String diceText = Integer.toString(rolledDice);
		roll.setText(diceText);
	}
}
