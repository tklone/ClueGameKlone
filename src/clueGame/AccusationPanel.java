package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AccusationPanel extends JDialog {

	private JComboBox<String> weaponsMenu, playersMenu;

	public AccusationPanel() {
		setLayout(new GridLayout(2, 4));
		
		JLabel weaponsLabel = new JLabel("Select a WEAPON");
		JLabel peopleLabel = new JLabel("Select a PERSON");
		// setModal(true);
		add(weaponsLabel);
		add(peopleLabel);

		weaponsMenu = createWeaponsCombo();
		add(weaponsMenu);

		playersMenu = createPeopleCombo();
		add(playersMenu);

		ComboListener listener = new ComboListener();
		weaponsMenu.addActionListener(listener);
		playersMenu.addActionListener(listener);
		
		
	}

	private JComboBox<String> createPeopleCombo() {
		JComboBox<String> players = new JComboBox<String>();
		Board board = Board.getInstance();
		for (Card c : board.getPeopleCards()) {
			String name = c.getName();
			players.addItem(name);
		}
		players.setVisible(true);
		return players;
	}

	private JComboBox<String> createWeaponsCombo() {
		JComboBox<String> weapons = new JComboBox<String>();
		Board board = Board.getInstance();
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
	
	public static void main(String[] args) {
		AccusationPanel gui = new AccusationPanel();
		gui.setVisible(true);
	}

}
