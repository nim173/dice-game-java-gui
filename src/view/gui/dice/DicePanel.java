package view.gui.dice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DicePanel extends JPanel {
	private DiceFace diceFace1;
	private DiceFace diceFace2;

	public DicePanel() {
		diceFace1 = new DiceFace(this);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());	// GridBagLayout used to center inside
		panel.add(diceFace1);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		diceFace2 = new DiceFace(this);
		panel2.add(diceFace2);

//		this.add(Box.createHorizontalGlue());
//		jPanel1.add(diceFace1);
//		this.add(jPanel1);
//		//jPanel.add(Box.createHorizontalGlue());
//		jPanel2.add(diceFace2);
//		this.add(jPanel2);
//		this.add(Box.createHorizontalGlue());
		
		this.setLayout(new GridBagLayout());
		
		// creating filler panels
		JPanel jPanel1 = new JPanel();
		JPanel jPanel2 = new JPanel();
		JPanel jPanel3 = new JPanel();	
		JPanel jPanel4 = new JPanel();	
		JPanel jPanel5 = new JPanel();	

		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridwidth = 1; 
		gridConstraints.gridheight = 1;
		gridConstraints.weightx = 1;
		gridConstraints.weighty = 1;
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;
		
		this.add(jPanel1, gridConstraints);
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		
		this.add(panel, gridConstraints);
		
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 1;
		gridConstraints.weighty = 1;
		
		this.add(jPanel2, gridConstraints);
		
		gridConstraints.gridx = 3;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		//gridConstraints.fill = GridBagConstraints.NONE;
		
		this.add(panel2, gridConstraints);
		
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 1;
		gridConstraints.weighty = 1;
		
		this.add(jPanel3, gridConstraints);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;
		
		this.add(jPanel4, gridConstraints);
		
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 2;
		
		this.add(jPanel5, gridConstraints);
	}
	
	public void setDice1(String value) {
		diceFace1.redraw(value);
	}

	public void setDice2(String value) {
		diceFace2.redraw(value);
	}
	
	public void setToDefault() {
		setDice1("1");
		setDice2("1");
	}
}
