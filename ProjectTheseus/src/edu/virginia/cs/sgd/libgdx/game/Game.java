/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.game;

import java.util.ArrayList;
import java.util.Random;

import edu.virginia.cs.sgd.libgdx.camera.MyCameraInputController;
import edu.virginia.cs.sgd.libgdx.entities.Creature;
import edu.virginia.cs.sgd.libgdx.entities.Entity;
import edu.virginia.cs.sgd.libgdx.g3d.Maze;
import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.inventory.Inventory;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Game {
	private MazeBuilder mb;
	private MyCameraInputController cam;
	private static Player player;
	private ArrayList<Creature> creatures;
	private ArrayList<Inventory> treasure;
	private ArrayList<Entity> turnOrder;
	private boolean playerTurn;

	private static int level = 1;

	public Game() {
		if (player == null) {
			player = new Player();
		}
		creatures = new ArrayList<Creature>();
		turnOrder = new ArrayList<Entity>();
		playerTurn = true;
		initialize(1);
	}

	public Game(MyCameraInputController cam, MazeBuilder mb) {
		this.mb = mb;
		this.cam = cam;
		this.cam.setGame(this);
		if (player == null) {
			player = new Player(cam);
		} else {
			player.setLocation(mb.getMaze().getStart());
			player.setCam(cam);
		}
		creatures = new ArrayList<Creature>();
		turnOrder = new ArrayList<Entity>();
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
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt((int)Math.pow(level, 2)) + 1; i++) {
			Creature minotaur = new Creature(this, mb.getMaze().getRandNode(),
				100 * (int)Math.sqrt(level), 10 + level*(level - 1), level, level);
			creatures.add(minotaur);
		}
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
		ArrayList<Creature> toBeRemoved = new ArrayList<Creature>();
		for (Creature c : creatures) {
			if (c.getCurrentHealth() <= 0) {
				toBeRemoved.add(c);
			}
		}
		for (Creature c : toBeRemoved) {
			creatures.remove(c);
		}
		return creatures;
	}
	
	public Maze getMaze() {
		return this.mb.getMaze();
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
			if (this.getCreatures().size() > 0) {
				for (Creature creature : creatures) {
					creature.determineBestAction();
				}
			}
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
