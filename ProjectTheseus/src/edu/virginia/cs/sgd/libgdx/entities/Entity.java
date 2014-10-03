package edu.virginia.cs.sgd.libgdx.entities;

import edu.virginia.cs.sgd.libgdx.inventory.Inventory;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Entity {
	
	private int maxHealth;
	private int currentHealth;
	private int defense;
	private int viewDistance;
	private int speed;
	private int attStr;
	
	private int xp;
	
	public void move() {
		
	}
	
	public void detectPlayer() {
		
	}
	
	public void attack(Player p) {
		p.takeDamage(attStr);
	}
	
	public void takeDamage(int dmg) {
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
	
	public void defend() {
		
	}

}
