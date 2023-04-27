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
	
	String accusationPerson;
	String accusationRoom;
	String accusationWeapon;

	public AccusationPanel() {
		this.setSize(600, 250);

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
		roomsMenu.addActionListener(listener);
		weaponsMenu.addActionListener(listener);
		playersMenu.addActionListener(listener);

		submitButton.addActionListener(new SubmitButtonListener());
		cancelButton.addActionListener(new CancelButtonListener());

		add(cancelButton);
		add(submitButton);
	}

	private JComboBox<String> createRoomCombo() {
		JComboBox<String> rooms = new JComboBox<String>();
		rooms.addItem(" ");
		for (Card c : board.getRoomCards()) {
			String name = c.getName();
			rooms.addItem(name);
		}
		return rooms;
	}

	private JComboBox<String> createPeopleCombo() {
		JComboBox<String> players = new JComboBox<String>();
		players.addItem(" ");
		for (Card c : board.getPeopleCards()) {
			String name = c.getName();
			players.addItem(name);
		}
		players.setVisible(true);
		return players;
	}

	private JComboBox<String> createWeaponsCombo() {
		JComboBox<String> weapons = new JComboBox<String>();
		weapons.addItem(" ");
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
				setWeaponChoice(weaponsMenu.getSelectedItem().toString());
				
				roomsMenu.getSelectedItem().toString();
				setRoomChoice(roomsMenu.getSelectedItem().toString());
				
				playersMenu.getSelectedItem().toString();
				setPlayerChoice(playersMenu.getSelectedItem().toString());
				
			}
		}
	}

	class SubmitButtonListener implements ActionListener {

		SubmitButtonListener() {
			submitButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			board.setAccusationPlayer(getPlayerChoice());
			board.setAccusationWeapon(getWeaponChoice());
			board.setAccusationRoom(getRoomChoice());			
			
			board.makeAccusation();
			dispose();
			String correctAnswer = "Correct Answer: " + board.getTheAnswer().getSolutionPerson().getName() + " with the "+ board.getTheAnswer().getSolutionWeapon().getName() + " in the " + board.getTheAnswer().getSolutionRoom().getName();
			JOptionPane.showMessageDialog(null, "GAME OVER!\n" + correctAnswer);
			System.exit(0);
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
		return accusationWeapon;
	}

	public String getPlayerChoice() {
		return accusationPerson;
	}
	
	public String getRoomChoice() {
		return accusationRoom;
	}
	
	public void setWeaponChoice(String weapon) {
		this.accusationWeapon = weaponsMenu.getSelectedItem().toString();
	}

	public void setPlayerChoice(String player) {
		this.accusationPerson = playersMenu.getSelectedItem().toString();
	}
	
	public void setRoomChoice(String room) {
		this.accusationRoom = roomsMenu.getSelectedItem().toString();
	}
}
