package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.entities.Entity;
<<<<<<< HEAD
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;

public class Player {

=======

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
>>>>>>> origin/Combat

	private int maxHealth;
	private int currentHealth;
	private int defense;
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
		nextLevelXp = 1000;
		level = 1;
	}
	
	public void move() {
		
	}
	
	public void detectPlayer() {
		
	}
	
	public void attack(Entity attackee) {
		// TODO implement commented out section
		//int dmg = currentWeapon.getDmg();
		int dmg = 50;
		attackee.takeDamage(dmg);
	}
	
	public void takeDamage(int dmg) {
		// TODO need to add currentArmour into account
		dmg = dmg - defense;
		if (dmg >= 0) 
			currentHealth -= dmg;
		
		if (currentHealth <= 0)
			die();
	}
	
	public void heal(int hp) {
		currentHealth += hp;
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
		
		maxHealth += 10;
		defense += 10;
		speed += 10;
		
		if (xp >= nextLevelXp) {
			level();
		}
	}
	
	public void gainXP(int xp) {
		this.xp += xp;
		
		// determines if a player levels up
		if (xp >= nextLevelXp) level();
		
	}

}
