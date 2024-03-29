package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
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

	private Player player;
	private Solution testSuggestion = new Solution();

	Board board = Board.getInstance();

	
	ArrayList<Card> testSeenCardsList = new ArrayList<>();


	public KnownCardsPanel() {
		player = board.getCurrentPlayer();
		setSize(300, 700);
		setLayout(new GridLayout(3, 1));
		setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
		updatePanels();
	}

	// create people cards in hand
	private JPanel createPeoplePanel() {
//		setTestSeen();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel peopleInHandLabel = new JLabel("In Hand: ");
		panel.add(peopleInHandLabel, BorderLayout.NORTH);

		// Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> peopleInHandList = new ArrayList<>();
		for (Card c : player.getHand()) {
			if (c.getCardType().equals(CardType.PERSON)) {
				peopleInHandList.add(c);
			}
		}

		if (peopleInHandList.size() == 0) {
			peopleInHand = new JTextField();
			peopleInHand.setText("None");
			peopleInHand.setEditable(false);
			panel.add(peopleInHand, BorderLayout.SOUTH);
		} else {
			for (Card c : peopleInHandList) {
				peopleInHand = new JTextField();
				peopleInHand.setText(c.getName());
				peopleInHand.setEditable(false);
				panel.add(peopleInHand, BorderLayout.SOUTH);
			}
		}

		JLabel peopleSeenLabel = new JLabel("Seen: ");
		panel.add(peopleSeenLabel);

		ArrayList<Card> peopleInSeenList = new ArrayList<>();
		for (Card c : player.getSeenCards()) {
			if (c.getCardType().equals(CardType.PERSON)) {
				peopleInSeenList.add(c);
			}
		}
		
		Color color = null;
		if (peopleInSeenList.size() == 0) {
			peopleSeen = new JTextField();
			peopleSeen.setText("None");
			peopleSeen.setEditable(false);
			panel.add(peopleSeen, BorderLayout.SOUTH);
		} else {
			for (Card c : peopleInSeenList) {
				color = c.getCardOwner();
				peopleSeen = new JTextField();
				peopleSeen.setText(c.getName());
				peopleSeen.setBackground(color);
				peopleSeen.setEditable(false);
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

		// Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> roomsInHandList = new ArrayList<>();
		for (Card c : player.getHand()) {
			if (c.getCardType().equals(CardType.ROOM)) {
				roomsInHandList.add(c);
			}
		}

		if (roomsInHandList.size() == 0) {
			roomsInHand = new JTextField();
			roomsInHand.setText("None");
			roomsInHand.setEditable(false);
			panel.add(roomsInHand, BorderLayout.SOUTH);
		} else {
			for (Card c : roomsInHandList) {
				roomsInHand = new JTextField();
				roomsInHand.setText(c.getName());
				roomsInHand.setEditable(false);
				panel.add(roomsInHand, BorderLayout.SOUTH);
			}
		}

		JLabel roomsSeenLabel = new JLabel("Seen: ");
		panel.add(roomsSeenLabel);

		ArrayList<Card> roomsInSeenList = new ArrayList<>();
		for (Card c : player.getSeenCards()) {
			if (c.getCardType().equals(CardType.ROOM)) {
				roomsInSeenList.add(c);
			}
		}

		Color color = null;
		if (roomsInSeenList.size() == 0) {
			roomsSeen = new JTextField();
			roomsSeen.setText("None");
			roomsSeen.setEditable(false);
			panel.add(roomsSeen, BorderLayout.SOUTH);
		} else { // Otherwise, set the text to whatever the name of the card it
			for (Card c : roomsInSeenList) {
				color = c.getCardOwner();
				roomsSeen = new JTextField();
				roomsSeen.setText(c.getName());
				roomsSeen.setBackground(color);
				roomsSeen.setEditable(false);
				panel.add(roomsSeen, BorderLayout.SOUTH);
			}
		}

		return panel;
	}

	// creates weapon cards in hand
	private JPanel createWeaponsPanel() {
//		setTestSeen();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel weaponsInHandLabel = new JLabel("In Hand: ");
		panel.add(weaponsInHandLabel, BorderLayout.NORTH);

		// Creating an ArrayList of the weapons in the human player's hand
		ArrayList<Card> weaponsInHandList = new ArrayList<>();
		for (Card c : player.getHand()) {
			if (c.getCardType().equals(CardType.WEAPON)) {
				weaponsInHandList.add(c);
			}
		}

		// If there are no weapons in their hand, we want to print "none"
		if (weaponsInHandList.size() == 0) {
			weaponsInHand = new JTextField();
			weaponsInHand.setText("None");
			weaponsInHand.setEditable(false);
			panel.add(weaponsInHand, BorderLayout.SOUTH);
		} else { // Otherwise, set the text to whatever the name of the card it
			for (Card c : weaponsInHandList) {
				weaponsInHand = new JTextField();
				weaponsInHand.setText(c.getName());
				weaponsInHand.setEditable(false);
				panel.add(weaponsInHand, BorderLayout.SOUTH);
			}
		}

		JLabel weaponsSeenLabel = new JLabel("Seen: ");
		panel.add(weaponsSeenLabel);

		ArrayList<Card> weaponsInSeenList = new ArrayList<>();
		for (Card c : player.getSeenCards()) {
//		for (Card c : testSeenCardsList) {
			if (c.getCardType().equals(CardType.WEAPON)) {
				weaponsInSeenList.add(c);
			}
		}
		
		Color color = null;
		if (weaponsInSeenList.size() == 0) {
			weaponsSeen = new JTextField();
			weaponsSeen.setText("None");
			weaponsSeen.setEditable(false);
			panel.add(weaponsSeen, BorderLayout.SOUTH);
		} else { // Otherwise, set the text to whatever the name of the card it
			for (Card c : weaponsInSeenList) {
				color = c.getCardOwner();
				weaponsSeen = new JTextField();
				weaponsSeen.setText(c.getName());
				weaponsSeen.setBackground(color);
				weaponsSeen.setEditable(false);
				panel.add(weaponsSeen, BorderLayout.SOUTH);
			}
		}

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
				
		KnownCardsPanel panel = new KnownCardsPanel();
		frame.add(panel, BorderLayout.CENTER);

		frame.setVisible(true); // make it visible

	}

	public void updatePanels() {
		
		peoplePanel = createPeoplePanel();
		roomsPanel = createRoomsPanel();
		weaponsPanel = createWeaponsPanel();
		
		this.removeAll();

		
		//people
		peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		this.add(peoplePanel);

		// rooms
		roomsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		this.add(roomsPanel);
		
		//weapons
		weaponsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		this.add(weaponsPanel, BorderLayout.SOUTH);
		
	}

}
