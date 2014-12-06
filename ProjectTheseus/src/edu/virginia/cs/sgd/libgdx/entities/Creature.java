package edu.virginia.cs.sgd.libgdx.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;
import edu.virginia.cs.sgd.libgdx.game.Game;
import edu.virginia.cs.sgd.libgdx.path.Path;
import edu.virginia.cs.sgd.libgdx.player.Player;

public class Creature extends Entity {

	private Game game;
	private ModelInstance box;
	private Player player;
	private cPull cPull;

	private final float moveLen = (float) MazeBuilder.spacing / 100;

	public Creature() {
		super();
	}

	public Creature(Game game, MazeNode location, int maxH, int atk, int def,
			int spd) {
		super(location, maxH, atk, def, spd);
		this.game = game;
		this.player = game.getPlayer();
	}

	public String toString() {
		return "Creature";
	}

	public boolean detectPlayer() {
		if (player.getCam().getCurrent() != null) {
			int xdiff = this.location.getX()
					- player.getCam().getCurrent().getX();
			int ydiff = this.location.getY()
					- player.getCam().getCurrent().getY();
			int dir;
			if (Math.abs(xdiff) + Math.abs(ydiff) == 1) {
				if (ydiff == 1) {
					dir = 0;
				} else if (ydiff == -1) {
					dir = 2;
				} else if (xdiff == 1) {
					dir = 3;
				} else if (xdiff == -1) {
					dir = 1;
				} else {
					return false;
				}
				if (!location.getWalls()[dir]) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean detectPlayerRange() {
		if (player.getCam().getCurrent() != null) {
			Path p = new Path(game.getMaze(), this.location, player.getCam().getCurrent());
			if (p.shortPathLen() <= this.perception + 1) {
				return true;
			}
		}
		System.out.println("False");
		return false;
	}

	public void determineBestAction() {
		if (this.detectPlayer()) {
			this.attack(player);
		} else if (this.detectPlayerRange()) {
			Path p = new Path(game.getMaze(), this.location, player.getCam().getCurrent());
			System.out.println("Turns: " + p.getNumTurns());
			if (p.getNumTurns() < 3) {
				this.setLocation(this.location.getNeighbors()[p.getDirArray()
						.get(0)]);
				this.move(p.getDirArray().get(0)); // moves the creature one
													// space towards the player
			}

		} else {
			Random random = new Random();
			int i = random.nextInt(4);
			if (!this.location.getWalls()[i]
					&& this.location.getNeighbors()[i] != null) {
				this.setLocation(this.location.getNeighbors()[i]);
				this.move(i);
			} else {
				this.determineBestAction();
			}
		}
	}

	public void move(int direction) {
		cPull = new cPull(this, direction);
		new Thread(cPull).start();
	}

	@Override
	public void die() {
		box.transform.translate(100, 100, 100);
		player.gainXP(1000);
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
