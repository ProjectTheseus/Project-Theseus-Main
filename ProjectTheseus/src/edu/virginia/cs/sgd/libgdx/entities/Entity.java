package edu.virginia.cs.sgd.libgdx.entities;

//superclass for creatures
//stats: health, defense, other things, location (point class)
//skeleton methods: move, attack, find path

public class Entity {

	protected int maxHealth, currentHealth, defense,
		perception, speed;
	
//	protected Weapon weapon;
//	protected Armor armor;
	
	/**
	 * @param args
	 */
	
	public void move() {
		
	}
	
	public int attack() { //return number of points on attack
		return 0;
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
	
	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @return the currentHealth
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * @param currentHealth the currentHealth to set
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * @return the perception
	 */
	public int getPerception() {
		return perception;
	}

	/**
	 * @param perception the perception to set
	 */
	public void setPerception(int perception) {
		this.perception = perception;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the weapon
	 */
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
