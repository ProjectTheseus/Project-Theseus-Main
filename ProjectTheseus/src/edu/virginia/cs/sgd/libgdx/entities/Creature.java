package edu.virginia.cs.sgd.libgdx.entities;

import edu.virginia.cs.sgd.libgdx.player.Player;

public class Creature extends Entity {
	private Player player;

	public Creature() {
		super();
	}

	public boolean detectPlayer() {
		int xdiff = Math.abs(this.location.getX() - player.location.getX());
		int ydiff = Math.abs(this.location.getY() - player.location.getY());
		if (xdiff + ydiff == 1) {
			return true;
		}
		return false;
	}

	public void determineBestAction() {
		// call the best method
	}

}
