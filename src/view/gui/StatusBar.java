package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.model.constants.CommonConstants;

public class StatusBar extends JPanel {
	private JLabel playerCount;
	private JLabel status;
	private MainFrame frame;
	
	public StatusBar(MainFrame frame) {
		this.frame = frame;
		this.setLayout(new BorderLayout());
		status = new JLabel(CommonConstants.ADD_PLAYER_STATUS);
		status.setForeground(Color.gray);
		this.add(status, BorderLayout.WEST);
		playerCount = new JLabel();
		playerCount.setForeground(Color.gray);
		this.updatePlayerCount();
		this.add(playerCount, BorderLayout.EAST);
	}

	public void updatePlayerCount() {
		this.playerCount.setText("Player Count: " + frame.getGameEngine().getAllPlayers().size());
	}
	
	public void updateStatus(String string) {
		status.setText(string);
	}
}
