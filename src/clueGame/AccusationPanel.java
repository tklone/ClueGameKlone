package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AccusationPanel extends JDialog {

	private JComboBox<String> roomsMenu, weaponsMenu, playersMenu;
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");
	Board board = Board.getInstance();

	public AccusationPanel() {
		setLayout(new GridLayout(4, 2));

		JLabel roomLabel = new JLabel("ROOM:");
		JLabel weaponsLabel = new JLabel("WEAPON:");
		JLabel peopleLabel = new JLabel("PERSON:");
		setModal(true);

		roomsMenu = createRoomCombo();
		add(roomLabel);
		add(roomsMenu);

		playersMenu = createPeopleCombo();
		add(peopleLabel);
		add(playersMenu);

		weaponsMenu = createWeaponsCombo();
		add(weaponsLabel);
		add(weaponsMenu);

		ComboListener listener = new ComboListener();
		weaponsMenu.addActionListener(listener);
		playersMenu.addActionListener(listener);

		submitButton.addActionListener(new SubmitButtonListener());
		cancelButton.addActionListener(new CancelButtonListener());

		add(cancelButton);
		add(submitButton);
	}

	private JComboBox<String> createRoomCombo() {
		JComboBox<String> rooms = new JComboBox<String>();
		for (Card c : board.getRoomCards()) {
			String name = c.getName();
			rooms.addItem(name);
		}
		return rooms;
	}

	private JComboBox<String> createPeopleCombo() {
		JComboBox<String> players = new JComboBox<String>();
		for (Card c : board.getPeopleCards()) {
			String name = c.getName();
			players.addItem(name);
		}
		players.setVisible(true);
		return players;
	}

	private JComboBox<String> createWeaponsCombo() {
		JComboBox<String> weapons = new JComboBox<String>();
		for (Card c : board.getWeaponsCards()) {
			String name = c.getName();
			weapons.addItem(name);
		}
		weapons.setVisible(true);
		return weapons;
	}

	private class ComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == weaponsMenu) {
				weaponsMenu.getSelectedItem().toString();
			} else {
				playersMenu.getSelectedItem().toString();
			}
		}
	}

	class SubmitButtonListener implements ActionListener {

		SubmitButtonListener() {
			submitButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			board.makeAccusation();
			dispose();
		}
	}

	class CancelButtonListener implements ActionListener {

		CancelButtonListener() {
			cancelButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	public String getWeaponChoice() {
		String weapon = weaponsMenu.getSelectedItem().toString();
		return weapon;
	}

	public String getPlayerChoice() {
		String player = playersMenu.getSelectedItem().toString();
		return player;
	}
}
