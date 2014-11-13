package edu.virginia.cs.sgd.libgdx.inventory;

public class Armor extends Item{


	private int defense;	
	
	public Armor(String name, String type, String textureRegion) {
		super(name, type, textureRegion);
		
	
	}

	public int getDefense() {
		return defense;
	}


	
	
}
