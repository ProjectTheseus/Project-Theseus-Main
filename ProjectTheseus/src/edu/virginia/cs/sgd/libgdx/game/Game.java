/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.game;

import java.util.ArrayList;

import edu.virginia.cs.sgd.libgdx.camera.MyCameraInputController;
import edu.virginia.cs.sgd.libgdx.entities.Entity;
import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Game {
	private MazeBuilder mb;
	private MyCameraInputController cam;
	private Player player;
	private ArrayList<Entity> creatures;
	private ArrayList<Inventory> treasure;
	private ArrayList<Entity> turnOrder;
	private boolean playerTurn;

	private static int level = 1;

	public Game() {
		player = new Player();
		creatures = new ArrayList<Entity>();
		playerTurn = true;
		initialize(1);
	}

	public Game(MyCameraInputController cam, MazeBuilder mb) {
		this.mb = mb;
		this.cam = cam;
		player = new Player(cam);
		playerTurn = true;
		initialize(1);
	}

	public void initialize(int level) {
		creatures = generateCreatures();
		treasure = generateTreasure();
		turnOrder = generateTurnOrder();
	}

	private ArrayList<Entity> generateTurnOrder() {
		return null;
	}

	private ArrayList<Inventory> generateTreasure() {
		return null;
	}

	private ArrayList<Entity> generateCreatures() {
		return null;
	}

	public void nextLevel() {
		level++;
		initialize(level);
		mb.changeScreen(MazeBuilder.class);
	}

	/**
	 * @return the cam
	 */
	public MyCameraInputController getCam() {
		return cam;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the creatures
	 */
	public ArrayList<Entity> getCreatures() {
		return creatures;
	}

	/**
	 * @return the playerTurn
	 */
	public boolean isPlayerTurn() {
		return playerTurn;
	}

	/**
	 * @param playerTurn
	 *            the playerTurn to set
	 */
	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	/**
	 * @return the level
	 */
	public static int getLevel() {
		return level;
	}

	/**
	 * @param l
	 *            the level to be set
	 */
	public static void setLevel(int l) {
		level = l;
	}

}
