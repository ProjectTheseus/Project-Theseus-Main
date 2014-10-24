package edu.virginia.cs.sgd.libgdx.entities;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;

public class Creature extends Entity {
	
	private ModelInstance box;

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
	
	public ModelInstance getBox() {
		return box;
	}
	
	public void setBox(ModelInstance box) {
		this.box = box;
	}

}
