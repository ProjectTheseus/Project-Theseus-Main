package edu.virginia.cs.sgd.libgdx.inventory;

public class Weapon extends Item {

	private int accuracy, damage;
	
	public Weapon(String name, String type, String textureRegion) {
		super(name, type, textureRegion);
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getDamage() {
		return damage;
	}


	
	
}
