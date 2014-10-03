package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.entities.Entity;

public class Player extends Entity {

	private int xp;
	private int level;

	// private QuickInventory quickInventory;
	// private Inventory inventory;

	public Player() {
		this.xp = 0;
		this.level = 1;
	}

	public void waitTurn() {
		defense++;
	}

	public void interact() {

	}

	public void accessInventory() {

	}

	public void level() {
		level++;
	}

	public void gainXp(int xp) {
		this.xp += xp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

}
