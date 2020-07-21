package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.constants.CommonConstants;

public class ResetCurrentBetListener extends AbstractActionListener{

	public ResetCurrentBetListener(MainFrame frame) {
		super(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Player selectedPlayer = frame.getToolBar().getSelectedPlayer();
			selectedPlayer.resetBet();
			frame.getToolBar().getRollBtn().setEnabled(false); 	// disabling the roll button
			frame.getSummaryPanel().repopulateCardPanel();
			
			JOptionPane.showMessageDialog(frame, CommonConstants.REMOVE_BET_SUCCESS + " for " + selectedPlayer.getPlayerName());
			
			frame.getToolBar().getBetBtn().grabFocus();	// when there is a zero bet, focus goes back to bet btn from roll
			frame.getToolBar().toggleRollAndResetBetBtns(false);
			frame.getStatusBar().updateStatus(CommonConstants.BET_RESET_MESSAGE);
		} catch(NullPointerException ex) {
			JOptionPane.showMessageDialog(frame, CommonConstants.REMOVE_BET_ERROR);	// when there are no more players
		}
	}

}
