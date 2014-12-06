/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.camera;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

import edu.virginia.cs.sgd.libgdx.entities.Creature;
import edu.virginia.cs.sgd.libgdx.g3d.Maze;
import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;
import edu.virginia.cs.sgd.libgdx.game.Game;
import edu.virginia.cs.sgd.libgdx.inventory.InventoryScreen;
import edu.virginia.cs.sgd.libgdx.menu.SplashScreen;

/**
 * @author Dylan
 * 
 *         MyCameraInputController class - deals with user input and
 *         moves/rotates camera
 */
public class MyCameraInputController extends CameraInputController {
	private Game game; // holds game object;
	private MazeBuilder mb; // holds MazeBuilder object
	private Maze m; // holds Maze object
	private MazeNode current; // holds current MazeNode
	// private Path path; // holds Path object
	private int faceTo; // direction the camera is facing
	private int backTo; // direction behind camera
	// int looking; // int value pertaining to whether the camera is facing
	// up, forward, or down
	private boolean firstMove; // boolean flag, true if it is the firstMove
	private boolean invOpen;
	private CamPivot pivot; // holds CamPivot
	// private CamPoint pointer; // holds CamPoint
	private CamPull pull; // holds CamPull
	private long prevTime; // holds previous system time in ms
	private int spacing = MazeBuilder.spacing; // holds spacing between walls
	private final float moveLen = (float) spacing / 100; // holds length moved
															// per iteration of
															// Cam threads

	/**
	 * Constructor
	 * 
	 * @param camera
	 * @param m
	 */
	public MyCameraInputController(Camera camera, Maze m, MazeBuilder mb) {
		super(camera);
		super.autoUpdate = true;
		super.rotateLeftKey = Input.Keys.D;
		super.rotateLeftKey = Input.Keys.A;
		this.m = m;
		this.mb = mb;
		this.faceTo = m.oppDir(m.getStartSide());
		this.backTo = m.getStartSide();
		// this.looking = 1;
		this.current = m.getStart();
		firstMove = true;
		invOpen = false;
		prevTime = System.currentTimeMillis() - 500;
	}

	/**
	 * Disables camera control through mouse dragging
	 */
	@Override
	public boolean touchDragged(int x, int y, int p) {
		return true;
	}

	/**
	 * Disables camera zoom
	 */
	@Override
	public boolean scrolled(int amount) {
		return true;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		boolean attacked = false;
		if (System.currentTimeMillis() - prevTime > 500) {
			if (!m.getAtEnd() && current != null && game.isPlayerTurn()
					&& !game.getPlayer().isDead()) {
				for (Creature c : game.getCreatures()) {
					if (c.getLocation().equals(current.getNeighbors()[faceTo])
							&& !current.getWalls()[faceTo]) {
						game.getPlayer().attack(c);
						attacked = true;
					}
				}
				if (!attacked) {
					game.getPlayer().heal(
							(int) (game.getPlayer().getMaxHealth() * 0.05));
				}
				game.endPlayerTurn();
			}
			prevTime = System.currentTimeMillis();
		}
		return true;
	}

	/**
	 * Deals with keyboard input
	 */
	@Override
	public boolean keyDown(int keycode) {
		// input taken if the maze is unfinished
		if (!m.getAtEnd() && current != null && game.isPlayerTurn()) {
			switch (keycode) {

			// Open inventory
			case Input.Keys.I:
				if (!invOpen) {
					invOpen = true;
					mb.changeScreen(InventoryScreen.class);
				} else {
					invOpen = false;
				}
				break;

			// Restart level
			case Input.Keys.BACKSPACE:
				mb.changeScreen(MazeBuilder.class);
				game.resetPlayer();
				break;

			// Restart game
			case Input.Keys.ESCAPE:
				mb.changeScreen(SplashScreen.class);
				Game.setLevel(1);
				game.resetPlayer();
				break;

			// Left arrow or A keys turn camera left
			// case Input.Keys.LEFT:

			case Input.Keys.A:
				if (System.currentTimeMillis() - prevTime > 500
						&& !(firstMove && System.currentTimeMillis() - prevTime < 1000)
						&& !game.getPlayer().isDead()) {
					pivot = new CamPivot(this, true);
					new Thread(pivot).start();
					changeDir(true);
					prevTime = System.currentTimeMillis();
				}
				break;

			// Right arrow or D keys turn camera right
			// case Input.Keys.RIGHT:

			case Input.Keys.D:
				if (System.currentTimeMillis() - prevTime > 500
						&& !(firstMove && System.currentTimeMillis() - prevTime < 1000)
						&& !game.getPlayer().isDead()) {
					pivot = new CamPivot(this, false);
					new Thread(pivot).start();
					changeDir(false);
					prevTime = System.currentTimeMillis();
				}
				break;

			// W key moves camera forward
			case Input.Keys.W:
				if (System.currentTimeMillis() - prevTime > 500
						&& !isBlocked(true) && !game.getPlayer().isDead()) {

					pull = new CamPull(this, true, firstMove);
					if (firstMove
							&& System.currentTimeMillis() - prevTime > 1000) {
						if (backTo == m.getStartSide()) {
							new Thread(pull).start();
							game.getPlayer()
									.heal((int) (game.getPlayer()
											.getMaxHealth() * 0.05));
							m.incrementMoveCount();
							prevTime = System.currentTimeMillis();
						}
					} else if (!current.getWalls()[faceTo]
							&& current.getNeighbors()[faceTo] != null
							&& !firstMove) {
						current = current.getNeighbors()[faceTo];
						new Thread(pull).start();
						game.getPlayer().heal(
								(int) (game.getPlayer().getMaxHealth() * 0.05));
						m.incrementMoveCount();
						prevTime = System.currentTimeMillis();
						game.endPlayerTurn();
					} else if (current.equals(m.getEnd())
							&& backTo == m.getStartSide()) {
						current = current.getNeighbors()[faceTo];
						new Thread(pull).start();
						game.getPlayer().heal(
								(int) (game.getPlayer().getMaxHealth() * 0.05));
						m.incrementMoveCount();
						prevTime = System.currentTimeMillis();
						game.endPlayerTurn();
					}
				}
				break;

			// S key moves camera forward
			case Input.Keys.S:
				if (System.currentTimeMillis() - prevTime > 500
						&& !isBlocked(false) && !game.getPlayer().isDead()) {
					pull = new CamPull(this, false, firstMove);
					if (firstMove) {
						break;
					} else if (!current.getWalls()[backTo]
							&& current.getNeighbors()[backTo] != null) {
						current = current.getNeighbors()[backTo];
						new Thread(pull).start();
						game.getPlayer().heal(
								(int) (game.getPlayer().getMaxHealth() * 0.05));
						m.incrementMoveCount();
						game.endPlayerTurn();
					}
					prevTime = System.currentTimeMillis();

				}
				break;

			default:
				break;

			}
			super.camera.update();
			super.update();
			if (current == null) {
				mb.getGame().nextLevel();
			}
			super.camera.update();
			super.update();
		}
		return true;
	}

	/**
	 * Changes faceTo and backTo variables based on boolean parameter
	 * 
	 * @param left
	 */
	public void changeDir(boolean left) {
		if (left) {
			faceTo++;
			backTo++;
		} else {
			faceTo--;
			backTo--;
		}
		if (faceTo > 3) {
			faceTo -= 4;
		} else if (faceTo < 0) {
			faceTo += 4;
		}
		if (backTo > 3) {
			backTo -= 4;
		} else if (backTo < 0) {
			backTo += 4;
		}
	}

	/**
	 * Moves camera forward or backward based on boolean parameter
	 * 
	 * @param forward
	 */
	public void moveCam(boolean forward) {
		switch (faceTo) {

		case 0:
			if (forward) {
				super.camera.translate(0, -1 * moveLen, 0);
			} else {
				super.camera.translate(0, moveLen, 0);
			}
			break;

		case 1:
			if (forward) {
				super.camera.translate(moveLen, 0, 0);
			} else {
				super.camera.translate(-1 * moveLen, 0, 0);
			}
			break;

		case 2:
			if (forward) {
				super.camera.translate(0, moveLen, 0);
			} else {
				super.camera.translate(0, -1 * moveLen, 0);
			}
			break;

		case 3:
			if (forward) {
				super.camera.translate(-1 * moveLen, 0, 0);
			} else {
				super.camera.translate(moveLen, 0, 0);
			}
			break;

		default:
			break;

		}
		super.camera.update();
		super.update();
	}

	/**
	 * Rotates camera right or left based on boolean parameter
	 * 
	 * @param left
	 */
	public void rotateCam(boolean left) {
		if (left) {
			super.camera.rotate(1, 0, 0, 1);
		} else {
			super.camera.rotate(-1, 0, 0, 1);
		}
		super.camera.update();
		super.update();
	}

	/**
	 * Runnable classes allows for Threading/smoother camera movement
	 */

	public class CamPivot implements Runnable {
		MyCameraInputController camController;
		boolean left;

		public CamPivot(MyCameraInputController camController, boolean left) {
			this.camController = camController;
			this.left = left;
		}

		@Override
		public void run() {
			for (int i = 0; i < 90; i++) {
				camController.rotateCam(left);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class CamPoint implements Runnable {
		MyCameraInputController camController;
		int dir;

		public CamPoint(MyCameraInputController camController, int dir) {
			this.camController = camController;
			this.dir = dir;
		}

		@Override
		public void run() {
			int pointer = dir - camController.faceTo;
			if (pointer == 3) {
				pointer = -1;
			} else if (pointer == -3) {
				pointer = 1;
			}
			if (pointer > 0) {
				for (int i = 0; i < 90 * pointer; i++) {
					camController.rotateCam(true);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				for (int i = 0; i > 90 * pointer; i--) {
					camController.rotateCam(false);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			camController.setFaceTo(dir);
			camController.setBackTo(m.oppDir(dir));
		}
	}

	public class CamPull implements Runnable {
		MyCameraInputController camController;
		boolean forward;
		boolean firstMove;

		public CamPull(MyCameraInputController camController, boolean forward,
				boolean firstMove) {
			this.camController = camController;
			this.forward = forward;
			this.firstMove = firstMove;
		}

		@Override
		public void run() {
			if (firstMove) {
				for (int i = 0; i < 200; i++) {
					camController.moveCam(forward);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				camController.setFirstMove(false);
			} else {
				for (int i = 0; i < 100; i++) {
					camController.moveCam(forward);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public boolean isBlocked(boolean front) {
		if (front) {
			for (Creature c : game.getCreatures()) {
				if (c.getLocation() == current.getNeighbors()[faceTo]) {
					return true;
				}
			}
			return false;
		} else {
			for (Creature c : game.getCreatures()) {
				if (c.getLocation() == current.getNeighbors()[backTo]) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Getters and Setters
	 */

	public MazeNode getCurrent() {
		return current;
	}

	public void setFirstMove(boolean b) {
		firstMove = b;
	}

	public void setFaceTo(int dir) {
		faceTo = dir;
	}

	public void setBackTo(int dir) {
		backTo = dir;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
