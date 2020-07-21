package view.gui.menu;

import javax.swing.JMenuBar;

import view.gui.MainFrame;

public class MenuBar extends JMenuBar {
	
	public MenuBar(MainFrame frame) {
		this.add(new FileMenu());
		this.add(new PlayerMenu(frame));
	}
}
