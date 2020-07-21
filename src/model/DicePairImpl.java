package model;

import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.Die;

public class DicePairImpl implements DicePair {
	private Die die1;
	private Die die2;
	
	public DicePairImpl() throws IllegalArgumentException{
		Random ran = new Random();
		this.die1 = new DieImpl(1, ran.nextInt(Die.NUM_FACES) + 1 , Die.NUM_FACES);
		this.die2 = new DieImpl(2, ran.nextInt(Die.NUM_FACES) + 1 , Die.NUM_FACES);
	}
	
	// overloaded constructor added (as allowed by Caspar) 
	public DicePairImpl(Die die1, Die die2) {
		this.die1 = die1;
		this.die2 = die2;
	}

	@Override
	public Die getDie1() {
		return die1;
	}

	@Override
	public Die getDie2() {
		return die2;
	}

	@Override
	public int getTotal() {
		return die1.getValue() + die2.getValue();
	}

	@Override
	public boolean equals(DicePair dicePair) {
		return dicePair == null ? false : this.die1.equals(dicePair.getDie1()) && this.die2.equals(die2);
	}
	
	@Override
	public boolean equals(Object dicePair) {
		return dicePair instanceof DicePair ? this.equals((DicePair)dicePair) : false;
	}
	
	 @Override
	 public int hashCode() {
		 return die1.hashCode() + die2.hashCode();
	 }

	 @Override
	 public String toString() {
		 return String.format("Dice 1: %s,  Dice 2: %s .. Total: %d", die1.toString(), die2.toString(), this.getTotal());
	 }

	@Override
	public int compareTo(DicePair dicePair) {
		if (this.getTotal() == dicePair.getTotal())
			return 0;
		else
			return this.getTotal() > dicePair.getTotal() ? 1 : -1;
	}

}
