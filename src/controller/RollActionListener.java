package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.gui.MainFrame;
import view.gui.ToolBar;
import view.model.PlayerDecorator;
import view.model.constants.CommonConstants;

public class RollActionListener extends AbstractActionListener {
	private PlayerDecorator currentRoller; // only one player at a time can roll
	
	public RollActionListener(MainFrame frame) {
		super(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolBar toolBar = frame.getToolBar();
		GameEngine gameEngine = frame.getGameEngine();
		
		new Thread() {
			public void run() {
				currentRoller = toolBar.getSelectedPlayerDecorator();
				currentRoller.setRolling(true);	// specifies that a player is currently rolling
				
				frame.getStatusBar().updateStatus(CommonConstants.PLAYER_ROLLING); // updating status for player roll
				
				gameEngine.rollPlayer(currentRoller.getPlayer(), CommonConstants.INITIAL_DELAY_1, CommonConstants.FINAL_DELAY_1, 
					CommonConstants.DELAY_INCREMENT_1, CommonConstants.INITIAL_DELAY_1, CommonConstants.FINAL_DELAY_2, CommonConstants.DELAY_INCREMENT_2);
				currentRoller.setRolling(false);
				
				toolBar.refreshSelectedPlayer();	// facilitates switching player concurrency during roll
				
				// rolling house if all players have rolled
				if (toolBar.checkHouseRoll()) {
					// updating previous points of all players in the win loss register before results are updated
					frame.getWinLossRegister().updateRegisterBeforeRoll(gameEngine.getAllPlayers());
					
					JOptionPane.showConfirmDialog(frame, CommonConstants.HOUSE_ROLL_MESSAGE, CommonConstants.HOUSE_ROLL_TITLE, JOptionPane.PLAIN_MESSAGE);
					
					// updating status bar for house roll
					frame.getStatusBar().updateStatus(CommonConstants.HOUSE_ROLLING);
					
					gameEngine.rollHouse(CommonConstants.INITIAL_DELAY_1, CommonConstants.FINAL_DELAY_1, 
							CommonConstants.DELAY_INCREMENT_1, CommonConstants.INITIAL_DELAY_1, CommonConstants.FINAL_DELAY_2, CommonConstants.DELAY_INCREMENT_2);
					
					frame.getSummaryPanel().repopulateCardPanel();	// updating summary panel
					
					
					// Next Round
					int ret = JOptionPane.showConfirmDialog(frame, CommonConstants.PLAY_AGAIN, CommonConstants.PLAY_AGAIN, JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION) {
						// check if any players have reached 0 points
						String playerNames = null;
						List<Player> playersRemoved = new ArrayList<Player>();
						for (Player player : gameEngine.getAllPlayers()) {
							if (player.getPoints() == 0) {
								if (playerNames == null) {
									playerNames = player.getPlayerName();
								} else {
									playerNames += ", " + player.getPlayerName();
								}
								playersRemoved.add(player);
								toolBar.removePlayer(player);
								frame.getWinLossRegister().removePlayer(player.getPlayerId());
							}
						}
						
						// removing players who have reached zero points
						if (playerNames != null) {
							for (Player player : playersRemoved) {
								gameEngine.removePlayer(player);
							}
							JOptionPane.showMessageDialog(frame, CommonConstants.ROLL_REMOVE_PLAYER_MESSAGE + playerNames,
								CommonConstants.ROLL_REMOVE_PLAYER_TITLE, JOptionPane.INFORMATION_MESSAGE);
							
							frame.getSummaryPanel().repopulateCardPanel();	// updating summary panel
						}
						
						toolBar.resetRoll();
						toolBar.refreshSelectedPlayer();
						frame.getStatusBar().updateStatus(CommonConstants.NEXT_ROUND);
					} else {
						System.exit(0);
					}					
				}
			}
		}.start();
		
		// the buttons set to false till house rolls and next round starts
		toolBar.toggleBetBtn(false);
		toolBar.toggleRollAndResetBetBtns(false);
		toolBar.setRollBtnToolTip(CommonConstants.ALREADY_ROLLED);
	}

}