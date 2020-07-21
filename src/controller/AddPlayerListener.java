package controller;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.SimplePlayer;
import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.constants.CommonConstants;

public class AddPlayerListener extends AbstractActionListener {

	public AddPlayerListener(MainFrame frame) {
		super(frame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField playerId = new JTextField();
		JTextField playerName = new JTextField();
		// minimum of zero with no maximum value specified with increments of one to simulate integers
		JSpinner initialPoints = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		
		final JComponent[] inputs = new JComponent[] {
		        new JLabel("Player ID*"),
		        playerId,
		        new JLabel("Player Name*"),
		        playerName,
		        new JLabel("Initial Points (> 0)"),
		        initialPoints
		};
		Object[] options = {"ADD", "CANCEL"};
		
		int ret = JOptionPane.showOptionDialog(frame, inputs, CommonConstants.ADD, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (ret == JOptionPane.OK_OPTION) {
			int points = (int) initialPoints.getValue();
			// since players are automatically removed from the game when they reach zero (or less) points, only initial points above zero are accepted
			if (points <= 0) {
				JOptionPane.showMessageDialog(frame, CommonConstants.ADD_PLAYER_POINT_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String id = playerId.getText().trim();
			String name = playerName.getText().trim();
			if ("".equals(id) || "".equals(name)) {
				JOptionPane.showMessageDialog(frame, CommonConstants.ADD_PLAYER_DETAIL_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Player simplePlayer = new SimplePlayer(id, name, (int)points);
		    frame.getGameEngine().addPlayer(simplePlayer);
		    frame.getToolBar().addPlayer(simplePlayer);
		    frame.getWinLossRegister().addToMap(id, (int)points);
		    
		    // updating summary panel and status bar
		    frame.getSummaryPanel().repopulateCardPanel();
		    frame.getStatusBar().updateStatus("Player " + id + " added");
		    frame.getStatusBar().updatePlayerCount();
		    
		    JOptionPane.showMessageDialog(frame, "Player " + name + " added", "Sucess", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
