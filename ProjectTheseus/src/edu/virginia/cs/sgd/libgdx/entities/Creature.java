package edu.virginia.cs.sgd.libgdx.entities;

import edu.virginia.cs.sgd.libgdx.player.Player;

public class Creature extends Entity {
	private Player player;

	public Creature() {
		super();
	}

	public boolean detectPlayer() {
		return false;
	}

	public void determineBestAction() {
		// call the best method
	}

}
