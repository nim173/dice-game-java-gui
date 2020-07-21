package view.gui.summary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.interfaces.Player;
import view.gui.MainFrame;
import view.model.PlayerDecorator;

public class SummaryPanel extends JPanel {
	private JPanel cardPanel;
	private MainFrame frame;
	private JScrollPane scrollPane;
	
	public SummaryPanel(MainFrame frame) {
		this.setBackground(Color.lightGray);
		this.setLayout(new BorderLayout());
		this.frame = frame;
		this.add(new JLabel(" Summary Panel", SwingConstants.CENTER), BorderLayout.NORTH);
		cardPanel = new JPanel(new GridLayout(0, 1, 5, 5));
		scrollPane = new JScrollPane(cardPanel);
		this.add(scrollPane);
	}
	
	public void repopulateCardPanel() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SummaryPanel.this.remove(scrollPane);
				cardPanel = new JPanel(new GridLayout(0, 1, 5, 5));
				for (Player player : frame.getGameEngine().getAllPlayers()) {
					cardPanel.add(new PlayerSummaryCard(new PlayerDecorator(player), frame));
				}
				scrollPane = new JScrollPane(cardPanel);
				SummaryPanel.this.add(scrollPane, BorderLayout.CENTER);
				SummaryPanel.this.revalidate();
			}
		});
	}
}
