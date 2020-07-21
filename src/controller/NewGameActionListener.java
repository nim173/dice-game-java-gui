package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import client.GUIClient;

public class NewGameActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// close current Window
		Component component = (Component) e.getSource();
		JFrame jFrame = (JFrame)SwingUtilities.getRoot(((JPopupMenu)component.getParent()).getInvoker());
		jFrame.dispose();
		// open new frame
		new GUIClient();
	}

}
