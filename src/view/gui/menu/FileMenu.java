package view.gui.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import controller.ExitActionListener;
import controller.NewGameActionListener;

public class FileMenu extends JMenu{
	public FileMenu() {
		super("File");
		this.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem newGame = new JMenuItem("New Game", KeyEvent.VK_N);
		newGame.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		newGame.setToolTipText("Restart the game");
		newGame.addActionListener(new NewGameActionListener());
				
		JMenuItem exitGame = new JMenuItem("Exit", KeyEvent.VK_X);
		exitGame.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
		exitGame.setToolTipText("Exit the game");
		exitGame.addActionListener(new ExitActionListener());
		
		this.add(newGame);
	    this.add(new JSeparator()); // Adding a seperator in-between
		this.add(exitGame);
	}
}
