package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	private Board board;


	public KnownCardsPanel(Board board) {
		this.board = board;
		player = board.getHumanPlayer();

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
		panel.setLayout(new GridLayout(0, 1)); // 1 column, 0 bc may change
		JLabel peopleInHandLabel = new JLabel("In Hand: ");

		peopleInHand = new JTextField();
		peopleInHand.setText("None");
		panel.add(peopleInHandLabel, BorderLayout.NORTH);
		panel.add(peopleInHand, BorderLayout.SOUTH);

		JLabel peopleSeenLabel = new JLabel("Seen: ");

		peopleSeen = new JTextField(10);
		peopleSeen.setText("None");
		panel.add(peopleSeenLabel, BorderLayout.NORTH);
		panel.add(peopleSeen, BorderLayout.SOUTH);

		return panel;
	}

	// creates room cards in hand
	private JPanel createRoomsPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel roomsInHandLabel = new JLabel("In Hand: ");

		roomsInHand = new JTextField();
		roomsInHand.setText("None");
		roomsInHand.setEditable(false);
		
		
		panel.add(roomsInHandLabel, BorderLayout.NORTH);
		panel.add(roomsInHand, BorderLayout.SOUTH);

		JLabel roomsSeenLabel = new JLabel("Seen: ");

		// seen
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

		weaponsInHand = new JTextField();
		weaponsInHand.setText("None");
		panel.add(weaponsInHandLabel, BorderLayout.NORTH);
		panel.add(weaponsInHand, BorderLayout.SOUTH);

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
	public void setKnownCards(Card knownCard) {
		peopleInHandPanel.removeAll();
		roomsInHandPanel.removeAll();
		weaponsInHandPanel.removeAll();
		
		if (knownCard.getCardType().equals(CardType.PERSON)) {
			//Add card to IN HAND
			personName.setText(knownCard.getName());
			peopleInHandPanel.add(personName, BorderLayout.NORTH);
		} else if (knownCard.getCardType().equals(CardType.WEAPON)) {
			//Add card to IN HAND
			weaponName.setText(knownCard.getName());
			weaponsInHandPanel.add(weaponName, BorderLayout.NORTH);
		} else if (knownCard.getCardType().equals(CardType.ROOM)) {
			//Add card to IN HAND
			roomName.setText(knownCard.getName());
			roomsInHandPanel.add(roomName, BorderLayout.NORTH);
		}
	}
	*/


	public void updatePanels() {
		peoplePanel = createPeoplePanel();
		roomsPanel = createRoomsPanel();
		weaponsPanel = createWeaponsPanel();

	}
}
