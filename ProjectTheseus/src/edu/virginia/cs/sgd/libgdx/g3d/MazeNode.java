/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.g3d;

/**
 * @author Dylan
 * 
 *         MazeNode class - holds values and arrays relevant to nodes in maze
 */
public class MazeNode {
	private boolean[] walls; // boolean array giving presence of wall in certain direction
	private MazeNode[] neighbors; // array of MazeNodes holding nodes adjacent to this node

	private int x;	// x coordinate of node
	private int y;	// y coordinate of node
	private int z;	// z coordinate of node

	private boolean visited; // boolean flag telling whether current node has been visited during maze generation

	/**
	 * Default Constructor
	 */
	public MazeNode() {
		walls = new boolean[6];
		for (int i = 0; i < walls.length; i++) {
			walls[i] = true;
		}
		neighbors = new MazeNode[6];
		x = 0;
		y = 0;
		visited = false;
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public MazeNode(int x, int y, int z) {
		walls = new boolean[6];
		for (int i = 0; i < walls.length; i++) {
			walls[i] = true;
		}
		neighbors = new MazeNode[6];
		this.x = x;
		this.y = y;
		this.z = z;
		visited = false;
	}

	/**
	 * @return the walls
	 */
	public boolean[] getWalls() {
		return walls;
	}

	/**
	 * @param walls
	 *            the walls to set
	 */
	public void setWalls(boolean[] walls) {
		this.walls = walls;
	}

	/**
	 * @param wall
	 *            the wall to set
	 * @param dir
	 *            the direction of the wall
	 */
	public void setWalls(boolean wall, int dir) {
		this.walls[dir] = wall;
	}

	/**
	 * @return the neighbors
	 */
	public MazeNode[] getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors
	 *            the neighbors to set
	 */
	public void setNeighbors(MazeNode[] neighbors) {
		this.neighbors = neighbors;
	}

	/**
	 * @param neighbor
	 *            the neighbor to set
	 * @param dir
	 *            the direction of the neighbor
	 */
	public void setNeighbors(MazeNode neighbor, int dir) {
		this.neighbors[dir] = neighbor;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited
	 *            the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

}
