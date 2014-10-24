package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.camera.MyCameraInputController;
import edu.virginia.cs.sgd.libgdx.entities.Entity;
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;

public class Player extends Entity {

	private int xp, nextLevelXp, level;
	private Inventory inventory, quickInventory;
	private MyCameraInputController cam;

	public Player() {
		super();
		xp = 0;
		nextLevelXp = 1000;
		level = 1;
		// inventory = new Inventory();
		// quickInventory = inventory.getQuick();
	}

	public Player(MyCameraInputController cam) {
		// Sample base stats
		super(cam.getCurrent(), 100, 1, 0, 1);
		this.cam = cam;
		xp = 0;
		nextLevelXp = 1000;
		level = 1;
		// inventory = new Inventory();
		// quickInventory = inventory.getQuick();
	}

	@Override
	public void attack(Entity attackee) {
		// TODO implement commented out section
		// int dmg = currentWeapon.getDmg();
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
		if (xp >= nextLevelXp)
			level();

	}
	
	public MyCameraInputController getCam() {
		return cam;
	}

}
