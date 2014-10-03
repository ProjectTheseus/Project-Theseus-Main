package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.entities.Entity;

public class Player extends Entity {

	private int xp;
	private int level;
	// private QuickInventory quickInventory;
	// private Inventory inventory;

	public Player() {
		super();
		this.xp = 0;
		this.level = 1;
	}

	public void waitTurn() {
		this.defense++;
	}

	public void interact() {

	}

	public void accessInventory() {

	}

	public void level() {
		this.level++;
	}

	public void gainXp(int xp) {
		this.xp += xp;
	}


	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static void main(String[] args) {

	}

}
