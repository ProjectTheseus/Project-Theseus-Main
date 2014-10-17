package edu.virginia.cs.sgd.libgdx.entities;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;

//superclass for creatures
//stats: health, defense, other things, location (point class)
//skeleton methods: move, attack, find path

public class Entity {

	protected int maxHealth, currentHealth, attack, defense,
		speed, perception;
	protected MazeNode location;
	protected ModelInstance model;
	
//	protected Weapon weapon;
//	protected Armor armor;

	public Entity() {
		//default values
	}
	
	public Entity(MazeNode location, ModelInstance model, int maxH, int atk, int def, int spd) {
		this.location = location;
		this.model = model;
		this.maxHealth = maxH;
		this.currentHealth = maxH;
		this.attack = atk;
		this.defense = def;
		this.speed = spd;
		this.perception = 0;
	}
	
	public void move() {
		
	}
	
	public void attack(Entity e) {
		
		//if something is in front of you, can attack
		//take percent hit
		//deal damage
		//static method to find best move
		//return accuracy of move
		//return damage of best move
		
		double accuracy = .5; //a percent
		int damage = 10;
		
		double hit = Math.random();
		
		if (hit <= accuracy) {
			e.takeDamage(damage);
		}
		
		

		

		
	}
	
	public void takeDamage(int damage) {
		this.currentHealth -= damage;
		if(this.currentHealth <= 0){
			this.die();
		}
	}
	
	public void heal(int healAmount) {
		this.currentHealth += healAmount;
		if(this.currentHealth > this.maxHealth) {
			this.currentHealth = maxHealth;
		}
	}
	
	public void die() {
		
	}
	
	public void useItem() {
		
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getPerception() {
		return perception;
	}

	public void setPerception(int perception) {
		this.perception = perception;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

//	/**
//	 * @return the weapon
//	 */
//	public Weapon getWeapon() {
//		return weapon;
//	}
//
//	/**
//	 * @param weapon the weapon to set
//	 */
//	public void setWeapon(Weapon weapon) {
//		this.weapon = weapon;
//	}
//
//	/**
//	 * @return the armor
//	 */
//	public Armor getArmor() {
//		return armor;
//	}
//
//	/**
//	 * @param armor the armor to set
//	 */
//	public void setArmor(Armor armor) {
//		this.armor = armor;
//	}
	
}
