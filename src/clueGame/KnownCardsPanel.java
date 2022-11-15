package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class KnownCardsPanel extends JPanel {

	private JTextField peopleInHand;
	private JTextField roomsInHand;
	private JTextField weaponsInHand;
	private static JTextField peopleSeen;
	private static JTextField roomsSeen;
	private static JTextField weaponsSeen;

	private JPanel peoplePanel;
	private JPanel roomsPanel;
	private JPanel weaponsPanel;

	private Player humanPlayer;
	private Board board;

	public KnownCardsPanel(Board board) {
		this.board = board;
		humanPlayer = board.getHumanPlayer();

		// JPanel 3 rows, 1 column
		setLayout(new GridLayout(3, 1));
		setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));

		updatePanels();
		peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		add(peoplePanel);

		// rooms
		roomsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		add(roomsPanel);
		weaponsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		add(weaponsPanel, BorderLayout.SOUTH);
	}

	// create people cards in hand
	private JPanel createPeoplePanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel peopleInHandLabel = new JLabel("In Hand: ");
		panel.add(peopleInHandLabel, BorderLayout.NORTH);
		
	
		//Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> peopleInHandList = new ArrayList<>();
		for (Card c : humanPlayer.getHand()) {
			System.out.println(c.getName());
			if (c.getCardType().equals(CardType.PERSON)) {
				peopleInHandList.add(c);
			}
		}

		//If there are no weapons in their hand, we want to print "none"
		if (peopleInHandList.size() == 0) {
			peopleInHand = new JTextField();
			peopleInHand.setText("None");
			panel.add(peopleInHand, BorderLayout.SOUTH);
		} else { //Otherwise, set the text to whatever the name of the card it
			for (Card c : peopleInHandList) {
				peopleInHand = new JTextField();
				peopleInHand.setText(c.getName());
				panel.add(peopleInHand, BorderLayout.SOUTH);
			}
		}
		
		
		JLabel peopleSeenLabel = new JLabel("Seen: ");
		panel.add(peopleSeenLabel);
		
		ArrayList<Card> peopleInSeenList = new ArrayList<>();
		System.out.println("SEEN CARDS");

		for (Card c : humanPlayer.getSeenCards()) {
			System.out.println(c.getName());
			if (c.getCardType().equals(CardType.PERSON)) {
				peopleInSeenList.add(c);
			}
		}

		
		//If there are no weapons in their hand, we want to print "none"
		if (peopleInSeenList.size() == 0) {
			peopleSeen = new JTextField();
			peopleSeen.setText("None");
			panel.add(peopleSeen, BorderLayout.SOUTH);
		} else { //Otherwise, set the text to whatever the name of the card it
			for (Card c : peopleInHandList) {
				peopleSeen = new JTextField();
				peopleSeen.setText(c.getName());
				panel.add(peopleSeen, BorderLayout.SOUTH);
			}
		}

		return panel;
	}

	// creates room cards in hand
	private JPanel createRoomsPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel roomsInHandLabel = new JLabel("In Hand: ");
		panel.add(roomsInHandLabel, BorderLayout.NORTH);

		//Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> roomsInHandList = new ArrayList<>();
		for (Card c : humanPlayer.getHand()) {
			if (c.getCardType().equals(CardType.ROOM)) {
				roomsInHandList.add(c);
			}
		}

		
		//If there are no weapons in their hand, we want to print "none"
		if (roomsInHandList.size() == 0) {
			roomsInHand = new JTextField();
			roomsInHand.setText("None");
			panel.add(weaponsInHand, BorderLayout.SOUTH);
		} else { //Otherwise, set the text to whatever the name of the card it
			for (Card c : roomsInHandList) {
				roomsInHand = new JTextField();
				roomsInHand.setText(c.getName());
				panel.add(roomsInHand, BorderLayout.SOUTH);
			}
		}
		
		JLabel roomsSeenLabel = new JLabel("Seen: ");
		roomsSeen = new JTextField();
		roomsSeen.setText("None");
		panel.add(roomsSeenLabel, BorderLayout.NORTH);
		panel.add(roomsSeen, BorderLayout.SOUTH);

		return panel;
	}

	// creates weapon cards in hand
	private JPanel createWeaponsPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel weaponsInHandLabel = new JLabel("In Hand: ");
		panel.add(weaponsInHandLabel, BorderLayout.NORTH);

		//Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> weaponsInHandList = new ArrayList<>();
		for (Card c : humanPlayer.getHand()) {
			if (c.getCardType().equals(CardType.WEAPON)) {
				weaponsInHandList.add(c);
			}
		}

		
		//If there are no weapons in their hand, we want to print "none"
		if (weaponsInHandList.size() == 0) {
			weaponsInHand = new JTextField();
			weaponsInHand.setText("None");
			panel.add(weaponsInHand, BorderLayout.SOUTH);
		} else { //Otherwise, set the text to whatever the name of the card it
			for (Card c : weaponsInHandList) {
				weaponsInHand = new JTextField();
				weaponsInHand.setText(c.getName());
				panel.add(weaponsInHand, BorderLayout.SOUTH);
			}
		}
		
		JLabel weaponsSeenLabel = new JLabel("Seen: ");
		weaponsSeen = new JTextField();
		weaponsSeen.setText("None");
		panel.add(weaponsSeenLabel, BorderLayout.NORTH);
		panel.add(weaponsSeen, BorderLayout.SOUTH);
		
		return panel;

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame(); // create the frame
		frame.setSize(180, 750); // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close

		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.dealCards();

		KnownCardsPanel panel = new KnownCardsPanel(board);
		frame.add(panel, BorderLayout.CENTER);

		frame.setVisible(true); // make it visible

	}

	// set player cards in hand
	/*
	 * public void setKnownCards(Card knownCard) { peopleInHandPanel.removeAll();
	 * roomsInHandPanel.removeAll(); weaponsInHandPanel.removeAll();
	 * 
	 * if (knownCard.getCardType().equals(CardType.PERSON)) { //Add card to IN HAND
	 * personName.setText(knownCard.getName()); peopleInHandPanel.add(personName,
	 * BorderLayout.NORTH); } else if
	 * (knownCard.getCardType().equals(CardType.WEAPON)) { //Add card to IN HAND
	 * weaponName.setText(knownCard.getName()); weaponsInHandPanel.add(weaponName,
	 * BorderLayout.NORTH); } else if
	 * (knownCard.getCardType().equals(CardType.ROOM)) { //Add card to IN HAND
	 * roomName.setText(knownCard.getName()); roomsInHandPanel.add(roomName,
	 * BorderLayout.NORTH); } }
	 */

	public void updatePanels() {
		peoplePanel = createPeoplePanel();
		roomsPanel = createRoomsPanel();
		weaponsPanel = createWeaponsPanel();

	}
}
