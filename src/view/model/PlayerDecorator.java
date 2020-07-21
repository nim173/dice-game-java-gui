package view.model;

import model.DicePairImpl;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.Player;

public class PlayerDecorator{
	private Player player;
	private boolean rolled;
	private Die die1;
	private Die die2;
	private boolean rolling;
	
	public PlayerDecorator(Player player) {
		this.player = player;
		DicePair dicePair = new DicePairImpl();
		setDie1(dicePair.getDie1());
		setDie2(dicePair.getDie2());
		this.setRolled(false);
		this.rolling = false;
	}

	public Player getPlayer() {
		return player;
	}

	public String toString() {
		return player.getPlayerId() + ": " + player.getPlayerName();
	}
	
	public boolean getRolled() {
		return rolled;
	}
	
	public void setRolled(boolean rolled) {
		this.rolled = rolled;
	}

	public Die getDie1() {
		return die1;
	}

	public void setDie1(Die die1) {
		this.die1 = die1;
	}

	public Die getDie2() {
		return die2;
	}

	public void setDie2(Die die2) {
		this.die2 = die2;
	}

	public void setRolling(boolean b) {
		this.setRolled(!b);
		this.rolling = b;
	}

	public boolean getRolling() {
		return this.rolling;
	}
}
