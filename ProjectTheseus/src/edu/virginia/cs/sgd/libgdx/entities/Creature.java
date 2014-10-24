package edu.virginia.cs.sgd.libgdx.entities;

import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;

public class Creature extends Entity {

	public Creature() {
		super();
	}
	
	public Creature(MazeNode location, int maxH, int atk, int def, int spd) {
		super(location, maxH, atk, def, spd);
	}

	public void detectPlayer() {

	}

	public void determineBestAction() {
		// call the best method
	}

}
