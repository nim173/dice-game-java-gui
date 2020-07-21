package view.gui.summary;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.PlayerDecorator;

public class PlayerSummaryCard extends JPanel{
	private Player player;
	private JLabel bet;
	
	public PlayerSummaryCard(PlayerDecorator playerDecorator, MainFrame frame) {
		this.player = playerDecorator.getPlayer();
		
		TitledBorder title1 = BorderFactory.createTitledBorder(player.getPlayerId() + ": " + player.getPlayerName());
		this.setBorder(title1);
		this.setLayout(new GridLayout(0,1,0,0));
		this.add(new JLabel("Current Points"));
		this.add(new JLabel(String.valueOf(player.getPoints())));
		this.add(new JLabel("Current Bet"));
		
		bet = new JLabel(String.valueOf(player.getBet()));
		
		this.add(bet);
		this.add(new JLabel("Latest Win/Loss"));
		this.add(new JLabel(String.valueOf(frame.getWinLossRegister().getWinLoss(player.getPlayerId(), player.getPoints()))));		
	}

}
