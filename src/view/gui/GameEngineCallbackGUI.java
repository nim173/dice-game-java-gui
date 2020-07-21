package view.gui;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;
import view.model.PlayerDecorator;
import view.model.constants.CommonConstants;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainFrame frame;
	
	public GameEngineCallbackGUI(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				PlayerDecorator playerDecorator = null;
				JComboBox<PlayerDecorator> playerComboBox = frame.getToolBar().getPlayerComboBox();		
				for (int i = 0; i < playerComboBox.getItemCount(); i++) {
					if (playerComboBox.getItemAt(i).getPlayer().getPlayerId() == player.getPlayerId())
						playerDecorator = playerComboBox.getItemAt(i);
				}
				
				// updating the dice panel GUI
				if(die.getNumber() == 1) {
					if(playerComboBox.getSelectedItem() == playerDecorator)	// facilitating concurrency when switching between players
						frame.getDicePanel().setDice1(String.valueOf(die.getValue()));
					playerDecorator.setDie1(die);
				} else {
					if(playerComboBox.getSelectedItem() == playerDecorator)
						frame.getDicePanel().setDice2(String.valueOf(die.getValue()));
					playerDecorator.setDie2(die);
				}
			}
		});
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// updating the dice panel GUI
				if(die.getNumber() == 1) {
					frame.getDicePanel().setDice1(String.valueOf(die.getValue()));
				} else {
					frame.getDicePanel().setDice2(String.valueOf(die.getValue()));
				}
			}
		});
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine) {
		JOptionPane.showMessageDialog(frame, result.toString(), player.getPlayerName() + " Roll Results", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// updating status bar after house roll
		frame.getStatusBar().updateStatus(CommonConstants.HOUSE_ROLL_FINISHED);
		String message = "HOUSE RESULT: " + result.toString() + "\n\nPLAYER RESULTS\n";
		JOptionPane.showMessageDialog(frame, message + finalPlayerResults(gameEngine, result), "Round Results", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private String finalPlayerResults(GameEngine gameEngine, DicePair houseResult) {
		String resultString = new String();
		for (Player player: gameEngine.getAllPlayers()) {
			resultString += String.format("ID: %s, Name: %s, Total: %d Result = ", player.getPlayerId(), player.getPlayerName(), player.getResult().getTotal());
			int temp = houseResult.compareTo(player.getResult());
			if (temp == -1) {
				resultString += "WIN (+" + player.getBet() + " Points)\n";
			} else if (temp == 1) {
				resultString += "LOSS (-" + player.getBet() + " Points)\n";
			} else {
				resultString += "DRAW\n";
			}
		}
		return resultString;
	}
}
