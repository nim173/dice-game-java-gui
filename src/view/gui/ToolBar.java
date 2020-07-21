package view.gui;

import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import controller.AddPlayerListener;
import controller.BetActionListener;
import controller.ComboBoxActionListener;
import controller.ResetCurrentBetListener;
import controller.RollActionListener;
import controller.RemoveCurrentPlayerListener;
import model.interfaces.Player;
import view.model.PlayerDecorator;
import view.model.constants.CommonConstants;

public class ToolBar extends JToolBar {
	public static final String BET_PROPERTY = "bet";
	
	private JComboBox<PlayerDecorator> playerComboBox;
	private JButton rollBtn;
	private JButton betBtn;
	private JButton resetBtn;
	private JButton addBtn;
	
	public ToolBar(MainFrame frame) {
		// set to false as it messes with the GridBagLayout in MainFrame
		this.setFloatable(false);	
		
		// initializing and populating player list
		playerComboBox = new JComboBox<PlayerDecorator>();
		playerComboBox.addItemListener(new ComboBoxActionListener(frame));
		// repopulatePlayerList(frame);	
		
		// initializing and adding listeners for the game bar buttons
		rollBtn = new JButton(" Roll ");
		rollBtn.setToolTipText(CommonConstants.ROLL);
		rollBtn.addActionListener(new RollActionListener(frame));
		
		betBtn = new JButton(" Bet ");
		betBtn.setToolTipText(CommonConstants.BET);
		betBtn.addActionListener(new BetActionListener(frame));
		
		resetBtn = new JButton("Reset Bet");
		resetBtn.setToolTipText(CommonConstants.RESET_CURRENT_BET);
		resetBtn.addActionListener(new ResetCurrentBetListener(frame));
		
		JButton addPlayerBtn = new JButton(" Add ");
		addPlayerBtn.setToolTipText(CommonConstants.ADD);
		addPlayerBtn.addActionListener(new AddPlayerListener(frame));
		
		JButton removeBtn = new JButton("Remove");
		removeBtn.setToolTipText(CommonConstants.REMOVE);
		removeBtn.addActionListener(new RemoveCurrentPlayerListener(frame));
		
		// disabling buttons at initialization
		toggleRollAndResetBetBtns(false);
		toggleBetBtn(false);
		
		// adding all the components to the game bar
		this.add(new JLabel(" Game Bar  "));
		this.add(rollBtn);
		this.add(betBtn);
		this.add(resetBtn);
		this.add(new JLabel("  "));
		this.add(playerComboBox);
		this.add(new JLabel("  "));
		this.add(addPlayerBtn);
		this.add(removeBtn);
		this.add(new JLabel(" "));
	}

	public JComboBox<PlayerDecorator> getPlayerComboBox() {
		return playerComboBox;
	}

	
	public void addPlayer(Player player) {
		// when another player with same id is added replaces the previous player
	    // (this is already handled in GEI (A1) by the player hash map automatically
		removePlayer(player);
		PlayerDecorator newPlayer = new PlayerDecorator(player);
		playerComboBox.addItem(newPlayer);
		playerComboBox.setSelectedItem(newPlayer);	// switching combo box selected item to new player -> which in turn sets the respective dice panel (via ComboBoxLsitener
	}
	
	public void removePlayer(Player player) {
		for (int i = 0; i < playerComboBox.getItemCount(); i++) {
			if (playerComboBox.getItemAt(i).getPlayer().getPlayerId().equals(player.getPlayerId())) {
				playerComboBox.removeItemAt(i);
			}
		}
	}
	
	public Player getSelectedPlayer() {
		return getSelectedPlayerDecorator().getPlayer();
	}
	
	public PlayerDecorator getSelectedPlayerDecorator() {
		return (PlayerDecorator) playerComboBox.getSelectedItem();
	}
	
	public void removeSelectedPlayer() {
		playerComboBox.removeItem(playerComboBox.getSelectedItem());
	}
	
	public JButton getRollBtn() {
		return rollBtn;
	}

	public JButton getBetBtn() {
		return betBtn;
	}
	
	public void toggleRollAndResetBetBtns(Boolean b){
		rollBtn.setEnabled(b);
		resetBtn.setEnabled(b);
	}
	
	public void toggleBetBtn(Boolean b) {
		betBtn.setEnabled(b);
	}
	
	public void setRollBtnToolTip(String string) {
		rollBtn.setToolTipText(string);
	}
	
	public boolean checkHouseRoll() {
		int count = playerComboBox.getItemCount();
		if (count == 0)
			return false;
		for (int i = 0; i < count; i++) {
			if(playerComboBox.getItemAt(i).getRolled() == false)
				return false;
		}
		return true;
	}

	public boolean checkOtherPlayerRolling() {
		int count = playerComboBox.getItemCount();
		for (int i = 0; i < count; i++) {
			if(playerComboBox.getItemAt(i).getRolling() == true)
				return true;
		}
		return false;
	}

	/** refreshing the selected player by simulating a virtual event, so that the listener for the combo box will reload the restrictions on the buttons, 
	depending on the player state (when a player is selected when another player is rolling, the 'roll' button for that player would be disabled until
	the the current roll has finished, this method would re-enable the roll button in such an instance */
	public void refreshSelectedPlayer() {
		if (playerComboBox.getItemCount() != 0)
			playerComboBox.getItemListeners()[0].itemStateChanged(new ItemEvent(new JComboBox<String>(), 1, null, ItemEvent.SELECTED));
	}

	public void resetRoll() {
		int count = playerComboBox.getItemCount();
		for (int i = 0; i < count; i++) {
			playerComboBox.getItemAt(i).setRolled(false);
		}
	}

	public JButton getAddBtn() {
		return addBtn;
	}
}
