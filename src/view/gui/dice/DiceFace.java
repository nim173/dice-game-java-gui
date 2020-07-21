package view.gui.dice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class DiceFace extends JComponent{
	private String value = "1";	// default value for a dice face
	private DicePanel parent;
	
	public DiceFace(DicePanel dicePanel) {
		parent = dicePanel;
	}

	public void redraw(String value) {
		this.value = value;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int w = getWidth();
        int h = getHeight();

        // painting background
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.BLACK);
        
        // draw dice
        switch (value) {
        case "1":
            drawSpot(g2, w/2, h/2);
            break;
        case "3":
            drawSpot(g2, w/2, h/2);
        case "2":
            drawSpot(g2, w/4, h/4);
            drawSpot(g2, 3*w/4, 3*h/4);
            break;
        case "5":
            drawSpot(g2, w/2, h/2);
        case "4":
            drawSpot(g2, w/4, h/4);
            drawSpot(g2, 3*w/4, 3*h/4);
            drawSpot(g2, 3*w/4, h/4);
            drawSpot(g2, w/4, 3*h/4);
            break;
        case "6":
            drawSpot(g2, w/4, h/4);
            drawSpot(g2, 3*w/4, 3*h/4);
            drawSpot(g2, 3*w/4, h/4);
            drawSpot(g2, w/4, 3*h/4);
            drawSpot(g2, w/4, h/2);
            drawSpot(g2, 3*w/4, h/2);
            break;
    }
	}
	
	@Override
    public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	@Override
    public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
    public Dimension getPreferredSize() {
		Dimension x = parent.getSize();
        int w = (int) x.getWidth();
        int h = (int) x.getHeight();
        int s = (w < h ? w/2: h/2);
        return new Dimension(s, s);
    }
	
	private void drawSpot(Graphics2D g2, int x, int y) {
		Dimension d = parent.getSize();
		int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int SPOT_DIAM = (w < h ? w/20 : h/20);
        g2.fillOval(x-SPOT_DIAM/2, y-SPOT_DIAM/2, SPOT_DIAM, SPOT_DIAM);
    }
}
