package edu.virginia.cs.sgd.libgdx.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Creature extends Entity {

	private ModelInstance box;
	private Player player;

	public Creature() {
		super();
	}

	public Creature(MazeNode location, int maxH, int atk, int def, int spd) {
		super(location, maxH, atk, def, spd);
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
		if(this.detectPlayer()){
			this.attack(player);
		}
		else{
			Random random = new Random();
			int i = random.nextInt(3);
			if(!this.location.getWalls()[i]){
				this.setLocation(this.location.getNeighbors()[i]);
				this.move();
			}
			else{
				this.determineBestAction();
			}
		}
	}
	
	public void move() {
		
	}

	public ModelInstance getBox() {
		return box;
	}

	public void setBox(ModelInstance box) {
		this.box = box;
	}

}
