package controller;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.constants.CommonConstants;

public class BetActionListener extends AbstractActionListener {

	public BetActionListener(MainFrame frame) {
		super(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player selectedPlayer = frame.getToolBar().getSelectedPlayer();
		// minimum of zero with no maximum value specified with increments of one to simulate integers
		JSpinner amount = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		// showing current bet in the spinner
		amount.setValue((int) selectedPlayer.getBet());
		amount.grabFocus();
		
		final JComponent[] inputs = new JComponent[] {
		        new JLabel("Enter Bet for " + selectedPlayer.getPlayerName() + ":*"),
		        amount
		};
		Object[] options = {"PLACE BET", "CANCEL"};
		
		int ret = JOptionPane.showOptionDialog(frame, inputs, CommonConstants.BET, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		if (ret == JOptionPane.OK_OPTION) {
			int bet = (int) amount.getValue();
			if (frame.getGameEngine().placeBet(selectedPlayer, bet)) {
				if (bet == 0) {
					frame.getToolBar().toggleRollAndResetBetBtns(false); 	// disabling the roll and reset buttons 
					JOptionPane.showMessageDialog(frame, CommonConstants.BET_RESET_MESSAGE, "Success", JOptionPane.INFORMATION_MESSAGE);
					frame.getSummaryPanel().repopulateCardPanel();
					frame.getStatusBar().updateStatus(CommonConstants.BET_RESET_MESSAGE);
				} else {
					frame.getToolBar().toggleRollAndResetBetBtns(true); 	// enabling the roll and reset buttons after valid bet
					frame.getSummaryPanel().repopulateCardPanel();
					JOptionPane.showMessageDialog(frame, bet + " point bet placed for " + selectedPlayer.getPlayerName(), "Success", JOptionPane.INFORMATION_MESSAGE);
					frame.getToolBar().getRollBtn().grabFocus(); 	// set focus to roll button
					frame.getStatusBar().updateStatus(bet + " point bet placed");
				}
			} else {
				JOptionPane.showMessageDialog(frame, CommonConstants.INVALID_BET_ERROR, "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
