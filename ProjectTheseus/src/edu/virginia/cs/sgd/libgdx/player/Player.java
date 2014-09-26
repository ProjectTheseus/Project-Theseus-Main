package edu.virginia.cs.sgd.libgdx.player;

import edu.virginia.cs.sgd.libgdx.inventory.Inventory;

public class Player {


	public int health;
	private int defense;
	public int power;
	public int exp;
	public int level;
	
	public Inventory inv;
	
	public Object weapon;
	public Object armor;
	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	public Player() {
		health = 100;
		defense = 100;
		power = 100;
		exp = 0;
		level = 0;
	}
	
	public int getAttPow() {
		return power;
		//return power + weapon.getPow();
	}
	
	public void attacked(int attPow) {
		health = health - attPow/defense;
	}
	
	public void move() {
		
	}
	

}
