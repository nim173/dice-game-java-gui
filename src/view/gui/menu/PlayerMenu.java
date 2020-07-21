package view.gui.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import controller.AddPlayerListener;
import controller.RemoveAllPlayersListener;
import controller.RemoveCurrentPlayerListener;
import controller.ResetCurrentBetListener;
import view.gui.MainFrame;
import view.model.constants.CommonConstants;

public class PlayerMenu extends JMenu {
	public PlayerMenu(MainFrame frame) {
		super("Player");
		this.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem addPlayer = new JMenuItem("Add Player", KeyEvent.VK_A);
		addPlayer.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
		addPlayer.setToolTipText(CommonConstants.ADD);
		addPlayer.addActionListener(new AddPlayerListener(frame));
		
		JMenuItem resetBet = new JMenuItem("Reset Bet");
		resetBet.setToolTipText(CommonConstants.RESET_CURRENT_BET);
		resetBet.addActionListener(new ResetCurrentBetListener(frame));
		
		JMenuItem removeCurrent = new JMenuItem("Remove Current Player");
		removeCurrent.setToolTipText(CommonConstants.REMOVE);
		removeCurrent.addActionListener(new RemoveCurrentPlayerListener(frame));
		
		JMenuItem removeAll = new JMenuItem("Remove All Players");
		removeAll.setToolTipText(CommonConstants.REMOVE_All);
		removeAll.addActionListener(new RemoveAllPlayersListener(frame));

		this.add(addPlayer);
		this.add(resetBet);
		this.add(new JSeparator());
		this.add(removeCurrent);
		this.add(removeAll);
	}
}
