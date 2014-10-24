/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.game;

import java.util.ArrayList;

import edu.virginia.cs.sgd.libgdx.camera.MyCameraInputController;
import edu.virginia.cs.sgd.libgdx.entities.Creature;
import edu.virginia.cs.sgd.libgdx.entities.Entity;
import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Game {
	private MazeBuilder mb;
	private MyCameraInputController cam;
	private Player player;
	private ArrayList<Creature> creatures;
	private ArrayList<Inventory> treasure;
	private ArrayList<Entity> turnOrder;
	private boolean playerTurn;

	private static int level = 1;

	public Game() {
		player = new Player();
		creatures = new ArrayList<Creature>();
		playerTurn = true;
		initialize(1);
	}

	public Game(MyCameraInputController cam, MazeBuilder mb) {
		this.mb = mb;
		this.cam = cam;
		this.cam.setGame(this);
		player = new Player(cam);
		playerTurn = true;
		initialize(1);
	}

	public void initialize(int level) {
		generateCreatures();
		generateTreasure();
		generateTurnOrder();
	}

	private void generateTurnOrder() {
		turnOrder.add(player);
		turnOrder.add(creatures.get(0));
	}

	private void generateTreasure() {

	}

	private void generateCreatures() {
		Creature minotaur = new Creature();
		creatures.add(minotaur);
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
	public ArrayList<Creature> getCreatures() {
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
	public void endPlayerTurn() {
		playerTurn = false;
		if (!playerTurn) {
			creatures.get(0).determineBestAction();
			playerTurn = true;
		}
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
