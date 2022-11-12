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

	private JLabel peopleInHandLabel;
	private JLabel roomsInHandLabel;
	private JLabel weaponsInHandLabel;
	private JLabel peopleSeenLabel;
	private JLabel roomsSeenLabel;
	private JLabel weaponsSeenLabel;

	private JPanel peopleInHandPanel;
	private JPanel roomsInHandPanel;
	private JPanel weaponsInHandPanel;
	private static JPanel peopleSeenPanel;
	private static JPanel roomsSeenPanel;
	private static JPanel weaponsSeenPanel;

	public KnownCardsPanel() {

		// JPanel 3 rows, 1 column
		JPanel knownCards = new JPanel();
		knownCards.setLayout(new GridLayout(3, 1));

		// people
		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(2, 1));
		peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		peopleInHandPanel = createPeopleInHandPanel();
		peopleSeenPanel = createPeopleSeenPanel();

		peoplePanel.add(peopleInHandPanel, BorderLayout.NORTH);
		peoplePanel.add(peopleSeenPanel, BorderLayout.SOUTH);

		knownCards.add(peoplePanel, BorderLayout.NORTH);

		// rooms
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(2, 1));
		roomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		roomsInHandPanel = createRoomsInHandPanel();
		roomsSeenPanel = createRoomsSeenPanel();

		roomPanel.add(roomsInHandPanel, BorderLayout.NORTH);
		roomPanel.add(roomsSeenPanel, BorderLayout.SOUTH);

		knownCards.add(roomPanel, BorderLayout.CENTER);

		// weapons
		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(2, 1));
		weaponPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		weaponsInHandPanel = createWeaponsInHandPanel();
		weaponsSeenPanel = createWeaponsSeenPanel();

		weaponPanel.add(weaponsInHandPanel, BorderLayout.NORTH);
		weaponPanel.add(weaponsSeenPanel, BorderLayout.SOUTH);

		knownCards.add(weaponPanel, BorderLayout.SOUTH);

		add(knownCards);

	}

	// create people cards in hand
	private JPanel createPeopleInHandPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1)); // 1 column, 0 bc may change
		peopleInHandLabel = new JLabel("In Hand: ");

		peopleInHand = new JTextField();
		peopleInHand.setText("None");
		panel.add(peopleInHandLabel, BorderLayout.NORTH);
		panel.add(peopleInHand, BorderLayout.SOUTH);

		return panel;

	}

	// creates people seen cards
	private JPanel createPeopleSeenPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		peopleSeenLabel = new JLabel("Seen: ");

		peopleSeen = new JTextField(10);
		peopleSeen.setText("None");
		panel.add(peopleSeenLabel, BorderLayout.NORTH);
		panel.add(peopleSeen, BorderLayout.SOUTH);

		return panel;
	}

	// creates room cards in hand
	private JPanel createRoomsInHandPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		roomsInHandLabel = new JLabel("In Hand: ");

		roomsInHand = new JTextField();
		roomsInHand.setText("None");
		panel.add(roomsInHandLabel, BorderLayout.NORTH);
		panel.add(roomsInHand, BorderLayout.SOUTH);

		return panel;
	}

	// creates room seen cards
	private JPanel createRoomsSeenPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		roomsSeenLabel = new JLabel("Seen: ");

		// seen
		roomsSeen = new JTextField();
		roomsSeen.setText("None");
		panel.add(roomsSeenLabel, BorderLayout.NORTH);
		panel.add(roomsSeen, BorderLayout.SOUTH);

		return panel;
	}

	// creates weapon cards in hand
	private JPanel createWeaponsInHandPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		weaponsInHandLabel = new JLabel("In Hand: ");

		weaponsInHand = new JTextField();
		weaponsInHand.setText("None");
		panel.add(weaponsInHandLabel, BorderLayout.NORTH);
		panel.add(weaponsInHand, BorderLayout.SOUTH);

		return panel;
	}

	// creates weapon seen cards
	private JPanel createWeaponsSeenPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		weaponsSeenLabel = new JLabel("Seen: ");

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

		KnownCardsPanel panel = new KnownCardsPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
		panel.setLayout(new GridLayout(1, 1));
		frame.add(panel, BorderLayout.CENTER);

		frame.setVisible(true); // make it visible

	}

	// set player cards in hand
	public void setKnownCards(Card knownCard) {

	}

	// update seen cards
	public static void updateSeenCards(Card knownCard, Player disprovePlayer) {

	}

}
