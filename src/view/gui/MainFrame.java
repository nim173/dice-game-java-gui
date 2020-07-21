package view.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackImpl;
import view.gui.dice.DicePanel;
import view.gui.menu.MenuBar;
import view.gui.summary.SummaryPanel;
import view.model.WinLossRegister;

public class MainFrame extends JFrame {
	
	final private GameEngine gameEngine;
	private ToolBar toolBar;
	private StatusBar statusBar;
	private SummaryPanel summaryPanel;
	private DicePanel dicePanel;
	private WinLossRegister winLossRegister;

	public MainFrame() {
		super("Dice Game");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension((int) dimension.getWidth()/2, (int) dimension.getHeight()/2));
		this.setLocationRelativeTo(null);	// centers the window
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// requirement for new game listener
		
		// instantiate the GameEngine
		gameEngine = new GameEngineImpl();
	    // adding callbacks
	    gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
	    gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));
	    
	    // instantiate the Win-Loss Register
	    setWinLossRegister(new WinLossRegister());
	    
	    // adding menu bar
		this.setJMenuBar(new MenuBar(this));
		
		// using a GridBagLayout to add summary panel, toolbar, and dice panel
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 1; 
		gridConstraints.gridheight = GridBagConstraints.RELATIVE;
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 100;
		//gridConstraints.insets = new Insets(0, 5, 0, 0);	// padding on all components
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;
		
		summaryPanel = new SummaryPanel(this);
		panel.add(getSummaryPanel(), gridConstraints);
		
		gridConstraints.gridx = 2;	// gridy = 1
		gridConstraints.gridheight = 1;
		gridConstraints.weightx = 0.8;
		gridConstraints.weighty = 0;	// setting a fixed height for toolbar

		toolBar = new ToolBar(this);
		panel.add(toolBar, gridConstraints);
		
		gridConstraints.gridy = 2;	// gridx = 2
		gridConstraints.weighty = 100;
		
		dicePanel = new DicePanel();
		panel.add(dicePanel, gridConstraints);
		
		gridConstraints.anchor = GridBagConstraints.PAGE_START;
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.gridx = 1;
		gridConstraints.gridwidth = 2;
		gridConstraints.gridy = 3;
		gridConstraints.weighty = 0;
		gridConstraints.insets = new Insets(0, 0, 0, 0);

		panel.add(new JSeparator(JSeparator.HORIZONTAL), gridConstraints);
		
		gridConstraints.gridy = 4;
		gridConstraints.insets = new Insets(1, 10, 1, 10);
		
		statusBar = new StatusBar(this);
		panel.add(statusBar, gridConstraints);
		
		this.add(panel);
		setVisible(true);
	}
	
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public DicePanel getDicePanel() {
		return dicePanel;
	}

	public SummaryPanel getSummaryPanel() {
		return summaryPanel;
	}

	public WinLossRegister getWinLossRegister() {
		return winLossRegister;
	}

	public void setWinLossRegister(WinLossRegister winLossRegister) {
		this.winLossRegister = winLossRegister;
	}

}
