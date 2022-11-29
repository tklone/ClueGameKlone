package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SuggestionPanel extends JDialog {

	
	private JComboBox<String> weaponsMenu, playersMenu;
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");
	Board board = Board.getInstance();
	private String weaponChoice;
	private String playerChoice;
	private String currentRoom;

	public SuggestionPanel() {
//		Board board = Board.getInstance();
		setLayout(new GridLayout(4, 2));

		JLabel roomLabel = new JLabel("ROOM:");
		JLabel weaponsLabel = new JLabel("WEAPON:");
		JLabel peopleLabel = new JLabel("PERSON:");
		
		setModal(true);
		 
		BoardCell cell = board.getCurrentPlayer().getLocation();
		Room room = board.getRoom(cell);
		String roomS = room.getName();
		JTextField currentRoom = new JTextField(roomS);
		currentRoom.setEditable(false);
		add(roomLabel);
		add(currentRoom);
		
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

	private JComboBox<String> createPeopleCombo() {
		JComboBox<String> players = new JComboBox<String>();
		players.addItem("");
		for (Card c : board.getPeopleCards()) {
			String name = c.getName();
			players.addItem(name);
		}
		players.setVisible(true);
		return players;
	}

	private JComboBox<String> createWeaponsCombo() {
		JComboBox<String> weapons = new JComboBox<String>();
		weapons.addItem("");
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
				setWeaponChoice(weaponsMenu.getSelectedItem().toString());
			} 
			
			if (e.getSource() == playersMenu){
				setPlayerChoice(playersMenu.getSelectedItem().toString());
			}

		}

	}

	class SubmitButtonListener implements ActionListener {

		SubmitButtonListener() {
			submitButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
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

	public void setWeaponChoice(String weaponChoice) {
		this.weaponChoice = weaponChoice;
	}
	
	public void setPlayerChoice(String playerChoice) {
		this.playerChoice = playerChoice;
	}
	
	public String getWeaponChoice() {
		return weaponChoice;
	}

	public String getPlayerChoice() {
		return playerChoice;
	}

	public void setRoom(String name) {
		currentRoom = name;
	}
	
	public String getRoom() {
		return currentRoom;
	}
}
