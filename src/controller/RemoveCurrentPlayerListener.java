package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.constants.CommonConstants;

public class RemoveCurrentPlayerListener extends AbstractActionListener {

	public RemoveCurrentPlayerListener(MainFrame frame) {
		super(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Player selectedPlayer = frame.getToolBar().getSelectedPlayer();
			frame.getGameEngine().removePlayer(selectedPlayer);
			frame.getToolBar().removeSelectedPlayer();
			frame.getToolBar().refreshSelectedPlayer();	// refreshing view data for the selected player
			
			frame.getSummaryPanel().repopulateCardPanel();
			frame.getWinLossRegister().removePlayer(selectedPlayer.getPlayerId());
			
			// checks whether if there are no players, if so resets dies
			if (frame.getToolBar().getPlayerComboBox().getItemCount() == 0) {
				frame.getDicePanel().setToDefault();
			}
			
			frame.getStatusBar().updateStatus(CommonConstants.PLAYER_REMOVED);
			frame.getStatusBar().updatePlayerCount();
			
			if (frame.getGameEngine().getAllPlayers().size() == 0) {
				frame.getToolBar().toggleBetBtn(false);	// to diasble bet button when there are no more players
			}
	
		} catch(NullPointerException ex) {
			JOptionPane.showMessageDialog(frame, CommonConstants.REMOVE_PLAYER_ERROR);	// when there are no more players to remove
			frame.getStatusBar().updateStatus(CommonConstants.ADD_PLAYER_STATUS);
		}
	}
}