package model;

import model.interfaces.Die;

public class DieImpl implements Die {
	private int number;
	private int value;
	private int numFaces;
	private static final String[] NUM_ARRAY = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", ">Nine"};
	
	public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException {
		if (number<1 || number>2 || value<1 || value>numFaces || numFaces<1)
			throw new IllegalArgumentException();
		this.number = number;
		this.value = value;
		this.numFaces = numFaces;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public int getNumFaces() {
		return this.numFaces;
	}

	@Override
	public boolean equals(Die die) {
		return die == null ? false : this.value == die.getValue() && this.numFaces == die.getNumFaces();
	}
	
	@Override
	public boolean equals(Object die) {
		return die instanceof Die ? this.equals((Die)die) : false;
	}
	
	@Override
	public int hashCode() {
		return Integer.toString(this.value + this.numFaces).hashCode();
	}

	@Override
	public String toString() {
		return String.format("%s", this.getValue()<10 ? DieImpl.NUM_ARRAY[this.getValue()] : DieImpl.NUM_ARRAY[10]);
	}
}
