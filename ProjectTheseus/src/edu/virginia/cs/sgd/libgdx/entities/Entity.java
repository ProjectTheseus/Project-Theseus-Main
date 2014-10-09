package edu.virginia.cs.sgd.libgdx.entities;

<<<<<<< HEAD
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;
import edu.virginia.cs.sgd.libgdx.player.Player;
=======
import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;

//superclass for creatures
//stats: health, defense, other things, location (point class)
//skeleton methods: move, attack, find path

public class Entity {

	protected int maxHealth, currentHealth, attack, defense,
		speed, perception;
	protected MazeNode location;
	
//	protected Weapon weapon;
//	protected Armor armor;

	public Entity() {
		//default values
	}
	
	public Entity(MazeNode location, int maxH, int atk, int def, int spd) {
		this.location = location;
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

	public static void main(String[] args) {
>>>>>>> origin/Combat

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
