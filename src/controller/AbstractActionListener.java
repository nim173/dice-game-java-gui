package controller;

import java.awt.event.ActionListener;

import view.gui.MainFrame;

public abstract class AbstractActionListener implements ActionListener {

	protected MainFrame frame;
	
	public AbstractActionListener(MainFrame frame) {
		this.frame = frame;
	}

}
