package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.gui.MainFrame;
import view.gui.ToolBar;
import view.model.PlayerDecorator;
import view.model.constants.CommonConstants;

public class ComboBoxActionListener implements ItemListener {
	private MainFrame frame;
	
	public ComboBoxActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			ToolBar toolBar = frame.getToolBar();
			PlayerDecorator selectedPlayer = toolBar.getSelectedPlayerDecorator();
			
			// setting starting dice values for each player when selected
			frame.getDicePanel().setDice1(String.valueOf(selectedPlayer.getDie1().getValue()));
			frame.getDicePanel().setDice2(String.valueOf(selectedPlayer.getDie2().getValue()));
			
			if (frame.getGameEngine().getPlayer(selectedPlayer.getPlayer().getPlayerId()).getBet() == 0) {
				// disable roll and reset buttons, enable bet button and grab focus, set tool tips
				toolBar.toggleBetBtn(true);
				toolBar.getBetBtn().grabFocus();
				toolBar.toggleRollAndResetBetBtns(false);
				toolBar.setRollBtnToolTip(CommonConstants.ROLL);
				frame.getStatusBar().updateStatus(CommonConstants.ROLL);
			} else {
				if (selectedPlayer.getRolled() || selectedPlayer.getRolling()) {
					if (selectedPlayer.getRolling()) { 
						// when the player is switched when rolling and user returns to rolling player status is updated accordingly
						frame.getStatusBar().updateStatus(CommonConstants.ALREADY_ROLLED);
					}
					// all buttons disabled when player is rolling or already rolled (till next round starts)
					toolBar.toggleBetBtn(false);
					toolBar.toggleRollAndResetBetBtns(false);
					toolBar.setRollBtnToolTip(CommonConstants.ALREADY_ROLLED);
					frame.getStatusBar().updateStatus(CommonConstants.ALREADY_ROLLED);
				} else {
					toolBar.toggleRollAndResetBetBtns(true);
					if (toolBar.checkOtherPlayerRolling()){
						// when player is switched to another when the previous player is rolling
						// -> roll button is disabled so that only one player can roll at a time
						frame.getStatusBar().updateStatus(CommonConstants.ROLL_WAIT);
						toolBar.getRollBtn().setEnabled(false);
					} else {
						// when player has bet, but is yet to roll
						toolBar.getRollBtn().setEnabled(true);
						frame.getStatusBar().updateStatus("Roll");
					}
					// common for above two instances - bet button enabled for both instances
					toolBar.toggleBetBtn(true);
					toolBar.setRollBtnToolTip("Roll");
				}
			}
		}
	}

}
