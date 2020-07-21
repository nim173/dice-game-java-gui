package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.gui.MainFrame;
import view.gui.ToolBar;
import view.model.constants.CommonConstants;

public class RemoveAllPlayersListener extends AbstractActionListener {

	public RemoveAllPlayersListener(MainFrame frame) {
		super(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameEngine gameEngine = frame.getGameEngine();
		// converting unmodifiable collection to an array list (to prevent ConcurrentModificationException)
		ArrayList<Player> players = new ArrayList<Player>(gameEngine.getAllPlayers());	
		if (players.size() == 0) {
			JOptionPane.showMessageDialog(frame, CommonConstants.REMOVE_PLAYER_ERROR);	// when there are no more players to delete
			frame.getStatusBar().updateStatus(CommonConstants.ADD_PLAYER_STATUS);
			return;
		}
		
		// prompting for confirmation
		int reply = JOptionPane.showConfirmDialog(frame, CommonConstants.REMOVE_CONFIRM, CommonConstants.REMOVE_All, JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION) {
			for (Player player: players) {
				gameEngine.removePlayer(player);
			}
			
			ToolBar toolBar = frame.getToolBar();
			toolBar.getPlayerComboBox().removeAllItems();
			toolBar.toggleBetBtn(false);	// when there are no players in the game
			
			frame.getSummaryPanel().repopulateCardPanel();
			frame.getWinLossRegister().removeALL();
			frame.getDicePanel().setToDefault();	// resetting player dies
			frame.getStatusBar().updatePlayerCount();
			frame.getStatusBar().updateStatus(CommonConstants.ALL_PLAYERS_REMOVED);
		}
	}
}
