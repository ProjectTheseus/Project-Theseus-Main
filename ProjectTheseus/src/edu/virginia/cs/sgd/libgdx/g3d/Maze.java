/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.g3d;

import java.util.Random;
import java.util.Stack;

/**
 * @author Dylan
 * 
 *         Maze class - randomly generates and populates a 3-dimensional grid of
 *         MazeNodes
 */
public class Maze {
	private int x_Dim; // x dimension of maze
	private int y_Dim; // y dimension of maze
	private final int z_Dim = 1; // z dimension of maze

	private MazeNode start; // start node
	private int startSide; // side (denoted by an integer 0-5) of start node
	private MazeNode end; // end node
	private int endSide; // side of end node
	private int moveCount; // holds count of moves made
	private boolean atEnd; // boolean of whether the end has been reached

	private MazeNode[][][] grid; // 3D array of MazeNodes
	private Stack<MazeNode> theStack; // Stack of MazeNodes

	private Random rand; // Random number generator

	/**
	 * Default Constructor
	 */
	public Maze() {
		x_Dim = 5;
		y_Dim = 5;
		moveCount = 0;
		atEnd = false;
		grid = new MazeNode[x_Dim][y_Dim][z_Dim];
		theStack = new Stack<MazeNode>();
		populate();
		generate();
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Maze(int x, int y) {
		x_Dim = x;
		y_Dim = y;
		moveCount = 0;
		grid = new MazeNode[x_Dim][y_Dim][z_Dim];
		theStack = new Stack<MazeNode>();
		populate();
		generate();
	}

	/**
	 * Populates grid with MazeNodes; gives coordinates and fills neighbor array
	 */
	private void populate() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				for (int z = 0; z < grid[x][y].length; z++) {
					grid[x][y][z] = new MazeNode(x, y, z);
				}
			}
		}

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				for (int z = 0; z < grid[x][y].length; z++) {
					MazeNode[] neighbors = new MazeNode[6];
					if (y > 0) {
						neighbors[0] = grid[x][y - 1][z];
					} else {
						neighbors[0] = null;
					}
					if (x < (x_Dim - 1)) {
						neighbors[1] = grid[x + 1][y][z];
					} else {
						neighbors[1] = null;
					}
					if (y < (y_Dim - 1)) {
						neighbors[2] = grid[x][y + 1][z];
					} else {
						neighbors[2] = null;
					}
					if (x > 0) {
						neighbors[3] = grid[x - 1][y][z];
					} else {
						neighbors[3] = null;
					}
					if (z > 0) {
						neighbors[4] = grid[x][y][z - 1];
					} else {
						neighbors[4] = null;
					}
					if (z < (z_Dim - 1)) {
						neighbors[5] = grid[x][y][z + 1];
					} else {
						neighbors[5] = null;
					}
					grid[x][y][z].setNeighbors(neighbors);
				}
			}
		}
	}

	/**
	 * Randomly generates maze through modified depth-first search algorithm
	 * using backtracking
	 */
	private void generate() {
		rand = new Random();

		MazeNode initial = grid[rand.nextInt(x_Dim)][rand.nextInt(y_Dim)][rand
				.nextInt(z_Dim)];
		MazeNode current = initial;
		current.setVisited(true);
		int count = 1;
		int size = x_Dim * y_Dim * z_Dim;

		// checks if all nodes in maze have been visited; continues of not
		while (count < size) {
			// checks if all nodes adjacent to current have been visited;
			// continues if so
			if (!allVisited(current)) {
				MazeNode chosen = null;
				int dir = 0;
				// randomly chooses an unvisited adjacent node and moves to it
				while (chosen == null || chosen.isVisited()) {
					dir = rand.nextInt(6);
					chosen = current.getNeighbors()[dir];
				}
				// current node is pushed onto stack
				theStack.push(current);
				// wall is removed between current node and chosen node
				current.setWalls(false, dir);
				chosen.setWalls(false, oppDir(dir));
				// chosen node becomes current node and visited flag is set
				current = chosen;
				current.setVisited(true);
				// count is incremented
				count++;
				// checks if stack is empty; continues if so
			} else if (!theStack.isEmpty()) {
				// a node is popped from the stack and becomes current node
				current = theStack.pop();
				// if all adjacent nodes to current have been visited and the
				// stack is empty
				// a random unvisited node becomes the current node
			} else {
				MazeNode temp = current;
				while (temp.isVisited()) {
					temp = grid[rand.nextInt(x_Dim)][rand.nextInt(y_Dim)][rand
							.nextInt(z_Dim)];
				}
				current = temp;
				current.setVisited(true);
				count++;
			}
		}
		// start node is chosen
		startNode();
	}

	/**
	 * @param current
	 * @return true if all adjacent nodes have been vsited
	 */
	private boolean allVisited(MazeNode current) {
		boolean allVisited = true;
		for (int i = 0; i < 6; i++) {
			if (current.getNeighbors()[i] != null) {
				if (current.getNeighbors()[i].isVisited() == false) {
					allVisited = false;
				} else {
					continue;
				}
			}
		}
		return allVisited;
	}

	/**
	 * @param dir
	 * @return int value equal to "opposite" (defined in method) of dir
	 */
	public int oppDir(int dir) {
		switch (dir) {
		case 0:
			return 2;

		case 1:
			return 3;

		case 2:
			return 0;

		case 3:
			return 1;

		case 4:
			return 5;

		case 5:
			return 4;

		default:
			return -1;
		}
	}

	/**
	 * Randomly sets the start node
	 */
	private void startNode() {
		int side = rand.nextInt(4);
		int xLen = rand.nextInt(x_Dim);
		int yLen = rand.nextInt(y_Dim);
		int zLen = rand.nextInt(z_Dim);

		if (side == 0) {
			grid[xLen][0][zLen].setWalls(false, 0);
			start = grid[xLen][0][zLen];
		} else if (side == 1) {
			grid[x_Dim - 1][yLen][zLen].setWalls(false, 1);
			start = grid[x_Dim - 1][yLen][zLen];
		} else if (side == 2) {
			grid[xLen][y_Dim - 1][zLen].setWalls(false, 2);
			start = grid[xLen][y_Dim - 1][zLen];
		} else {
			grid[0][yLen][zLen].setWalls(false, 3);
			start = grid[0][yLen][zLen];
		}
		startSide = side;
		// end node is set
		endNode(side);
	}

	/**
	 * End node is set at opposite side of start node
	 * 
	 * @param dir
	 */
	private void endNode(int dir) {
		int side = oppDir(dir);
		int xLen = rand.nextInt(x_Dim);
		int yLen = rand.nextInt(y_Dim);
		int zLen = rand.nextInt(z_Dim);

		if (side == 0) {
			grid[xLen][0][zLen].setWalls(false, 0);
			end = grid[xLen][0][zLen];
		} else if (side == 1) {
			grid[x_Dim - 1][yLen][zLen].setWalls(false, 1);
			end = grid[x_Dim - 1][yLen][zLen];
		} else if (side == 2) {
			grid[xLen][y_Dim - 1][zLen].setWalls(false, 2);
			end = grid[xLen][y_Dim - 1][zLen];
		} else {
			grid[0][yLen][zLen].setWalls(false, 3);
			end = grid[0][yLen][zLen];
		}
		endSide = side;
	}

	/**
	 * Getters and Setters
	 */

	public int getX_Dim() {
		return x_Dim;
	}

	public int getY_Dim() {
		return y_Dim;
	}

	public int getZ_Dim() {
		return z_Dim;
	}

	public void incrementMoveCount() {
		moveCount++;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public MazeNode getStart() {
		return start;
	}

	public MazeNode getEnd() {
		return end;
	}

	public int getStartSide() {
		return startSide;
	}

	public int getEndSide() {
		return endSide;
	}

	public MazeNode[][][] getGrid() {
		return grid;
	}
	
	public MazeNode getRandNode() {
		Random rand = new Random();
		MazeNode temp = grid[rand.nextInt(x_Dim)][rand.nextInt(y_Dim)][0];
		if (temp != this.start) {
			return temp;
		} else {
			return getRandNode();
		}
	}

	public int gridSize() {
		return x_Dim * y_Dim * z_Dim;
	}

	public void setAtEnd() {
		atEnd = true;
	}

	public boolean getAtEnd() {
		return atEnd;
	}
}
