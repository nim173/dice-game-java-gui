package client;

import javax.swing.SwingUtilities;

import view.gui.MainFrame;

public class GUIClient {

	public static void main(String[] args) {
		new GUIClient();
	}

	public GUIClient() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
