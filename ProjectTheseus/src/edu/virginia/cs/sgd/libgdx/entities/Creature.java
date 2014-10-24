package edu.virginia.cs.sgd.libgdx.entities;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Creature extends Entity {

	private ModelInstance box;
	private Player player;

	private final float moveLen = (float) MazeBuilder.spacing / 100;

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

	public void move() {

	}

	public void determineBestAction() {
		// call the best method
	}
	
	@Override
	public void die() {
		box.transform.translate(100, 100, 100);
	}

	public ModelInstance getBox() {
		return box;
	}

	public void setBox(ModelInstance box) {
		this.box = box;
	}

	public void moveC(int direction) {
		switch (direction) {

		case 0:
			box.transform.translate(0, -1 * moveLen, 0);
			break;

		case 1:
			box.transform.translate(moveLen, 0, 0);
			break;

		case 2:
			box.transform.translate(0, moveLen, 0);
			break;

		case 3:
			box.transform.translate(-1 * moveLen, 0, 0);
			break;

		default:
			break;

		}
	}

	public class cPull implements Runnable {
		Creature c;
		int direction;

		public cPull(Creature c, int direction) {
			this.c = c;
			this.direction = direction;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				c.moveC(direction);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
