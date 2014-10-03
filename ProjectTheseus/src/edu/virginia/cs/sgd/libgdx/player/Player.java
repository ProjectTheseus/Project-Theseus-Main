package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.inventory.Inventory;

public class Player {


	private int maxHealth;
	private int currentHealth;
	private int defense;
	private int viewDistance;
	private int speed;
	
	private int xp;
	private int nextLevelXp;
	private int level;
	
	private Object currentWeapon;
	private Object currentArmour;
	
	private Inventory inventory;
	private Inventory quickInventory;
	
	public void player() {
		xp = 0;
		level = 1;
		nextLevelXp = 1000;
	}
	
	public void move() {
		
	}
	
	public void detectPlayer() {
		
	}
	
	public void attack(Object attackee) {
	}
	
	public void takeDamage() {
		if (currentHealth <= 0) die();
	}
	
	public void heal() {
		
	}
	
	public void die() {
		
	}
	
	public void useItem() {
		
	}
	
	public void defend() {
		
	}
	
	public void Interact() {
		
	}
	
	public void accessInventory() {
		
	}
	
	public void level() {
		nextLevelXp += 1000;
		level++;
		if (xp >= nextLevelXp) {
			level();
		}
	}
	
	public void gainXP(int xp) {
		this.xp += xp;
		
		//determines if a player levels up
		if (xp >= nextLevelXp) {
			level();
		}
		
	}

}
