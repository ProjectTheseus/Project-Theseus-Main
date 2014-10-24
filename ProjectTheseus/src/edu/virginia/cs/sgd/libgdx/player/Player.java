package edu.virginia.cs.sgd.libgdx.player;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import edu.virginia.cs.sgd.libgdx.entities.Entity;
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;

public class Player extends Entity {
	
	private int xp, nextLevelXp, level;
	private Inventory inventory, quickInventory;
	
	public Player() {
		super();
		xp = 0;
		nextLevelXp = 1000;
		level = 1;
		//inventory = new Inventory();
		//quickInventory = inventory.getQuick();
	}
	
	@Override
	public void attack(Entity attackee) {
		// TODO implement commented out section
		//int dmg = currentWeapon.getDmg();
		int dmg = 50;
		attackee.takeDamage(dmg);
	}
	
	@Override
	public void takeDamage(int dmg) {
		// TODO need to add currentArmour into account
		dmg = dmg - defense;
		if (dmg >= 0) 
			currentHealth -= dmg;
		
		if (currentHealth <= 0)
			die();
	}
	
	@Override
	public void heal(int hp) {
		currentHealth += hp;
	}
	
	@Override
	public void die() {
		
	}
	
	@Override
	public void useItem() {
		
	}
	
	public void defend() {
		
	}
	
	public void Interact() {
		
	}
	
	public void accessInventory() {
		
	}
	public void levelUpOption()
	{
	

	}
	public void levelUp() {
		//When the char got a level up , a window should pop up and let the player choose the level up option
				JFrame frame = new JFrame("Level Up!");
				Object[] possibilities = {"Speed", "Attack", "Defense"};
				String s = (String)JOptionPane.showInputDialog(
				                    frame,
				                    "Choose level up option",
				                    "Please choose a level up Option",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    possibilities,
				                    "Speed");
				if(s == "Speed") 
					{
					speed+=10;
					}
				if(s == "Defense") defense+=10;
				if(s == "Attack")  attack +=10;
		
		level++;
		xp = xp- nextLevelXp;
		
        nextLevelXp = level * level * 1000;
		
		
		maxHealth += 10;
		
		
		
		if (xp >= nextLevelXp) {
			levelUp();
		}
        
	}
	
	public void gainXP(int xpadd) {
		this.xp = this.xp + xpadd;
		
		// determines if a player levels up
		if (xp >= nextLevelXp) levelUp();
		
	}

}
