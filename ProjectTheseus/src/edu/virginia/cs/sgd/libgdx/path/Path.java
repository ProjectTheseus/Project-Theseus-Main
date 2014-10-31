/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.virginia.cs.sgd.libgdx.g3d.Maze;
import edu.virginia.cs.sgd.libgdx.g3d.MazeNode;

/**
 * @author Dylan
 * 
 *         Path class - finds shortest path between a given start node and a
 *         given end node
 */
public class Path {
	private Maze m; // holds Maze object
	private MazeNode start; // holds start node
	//private MazeNode end; // holds end node
	//private MazeNode current; // holds current node
	private ArrayList<MazeNode> path; // holds array of MazeNodes designating
										// path
	private ArrayList<Integer> dirArray; // holds array of ints (directions)
											// designating path

	// variables used for A* search algorithm
	private Set<MazeNode> closedSet;
	private Set<MazeNode> openSet;
	private HashMap<MazeNode, MazeNode> cameFrom;
	private Map<MazeNode, Integer> gScore;
	private Map<MazeNode, Integer> hScore;
	private Map<MazeNode, Integer> fScore;

	/**
	 * Default Constructor
	 */
	public Path() {
		this.m = null;
		this.start = null;
		//this.end = null;
	}

	/**
	 * Constructor
	 * 
	 * @param m
	 * @param start
	 * @param end
	 */
	public Path(Maze m, MazeNode start, MazeNode end) {
		this.m = m;
		this.start = start;
		//this.end = end;
		path = shortPath(start, end);
		dirArray = dirs();
	}

	/**
	 * @return int size of path
	 */
	public int shortPathLen() {
		return path.size();
	}

	/**
	 * Modified A* search to find shortest path through Maze
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<MazeNode> shortPath(MazeNode start, MazeNode end) {
		closedSet = new HashSet<MazeNode>(); // nodes already evaluated
		openSet = new HashSet<MazeNode>(); // nodes yet to be evaluated
		openSet.add(start);
		cameFrom = new HashMap<MazeNode, MazeNode>();

		gScore = new HashMap<MazeNode, Integer>();
		gScore.put(start, 0);
		hScore = new HashMap<MazeNode, Integer>();
		hScore.put(start, hCostEst(start, end));
		fScore = new HashMap<MazeNode, Integer>();
		fScore.put(start, hScore.get(start));

		// checks if openSet is empty; continues if so
		while (!openSet.isEmpty()) {
			MazeNode current = null;
			// iterates through openSet and sets current node to node with
			// lowest fScore (estimated distance from start to end through that
			// node)
			for (MazeNode m : openSet) {
				if (current == null) {
					current = m;
				} else if (fScore.get(m) < fScore.get(current)) {
					current = m;
				}
			}
			// checks if at end; if so reconstruct path, else continue
			if (current.equals(end)) {
				return reconstructPath(cameFrom, end);
			}
			// remove current node from openSet
			openSet.remove(current);
			// adds current to closedSet
			closedSet.add(current);
			// for each adjacent node to current, not already evaluated,
			// recalculate scores
			for (int i = 0; i < 6; i++) {
				MazeNode neighbor = current.getNeighbors()[i];
				if (neighbor != null && !current.getWalls()[i]) {
					if (closedSet.contains(neighbor)) {
						continue;
					}
					int tentativeGScore = gScore.get(current) + 1;

					if ((!openSet.contains(neighbor))
							|| tentativeGScore < gScore.get(neighbor)) {
						cameFrom.put(neighbor, current);
						gScore.put(neighbor, tentativeGScore);
						fScore.put(neighbor,
								gScore.get(neighbor) + hCostEst(neighbor, end));
						if (!openSet.contains(neighbor)) {
							openSet.add(neighbor);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Calculates hCost (basically distance) from current node to end node
	 * 
	 * @param start
	 * @param goal
	 * @return Integer value
	 */
	private Integer hCostEst(MazeNode start, MazeNode goal) {
		double minusX = (start.getX() - goal.getX());
		int minusXi = (int) Math.pow(minusX, 2.0);

		double minusY = (start.getY() - goal.getY());
		int minusYj = (int) Math.pow(minusY, 2.0);

		double minusZ = (start.getZ() - goal.getZ());
		int minusZk = (int) Math.pow(minusZ, 2.0);

		return minusXi + minusYj + minusZk;
	}

	/**
	 * Constructs shortest path array recursively
	 * 
	 * @param cameFrom
	 * @param current
	 * @return ArrayList of MazeNodes making up path
	 * @throws StackOverflowError
	 */
	private ArrayList<MazeNode> reconstructPath(
			HashMap<MazeNode, MazeNode> cameFrom, MazeNode current)
			throws StackOverflowError {
		if (cameFrom.containsKey(current)) {
			ArrayList<MazeNode> r = new ArrayList<MazeNode>(reconstructPath(
					cameFrom, cameFrom.get(current)));
			r.add(current);
			return r;
		} else {
			ArrayList<MazeNode> r = new ArrayList<MazeNode>();
			r.add(current);
			return r;
		}
	}

	/**
	 * Creates array of ints (directions) from array of MazeNodes
	 * 
	 * @return array of ints
	 */
	private ArrayList<Integer> dirs() {
		ArrayList<Integer> r = new ArrayList<Integer>();
		if (start.equals(m.getStart())) {
			r.add(m.oppDir(m.getStartSide()));
		}
		for (int i = 0; i < path.size() - 1; i++) {
			for (int j = 0; j < 6; j++) {
				if (path.get(i + 1).equals(path.get(i).getNeighbors()[j])) {
					r.add(j);
					break;
				}
			}
		}
		return r;
	}

	/**
	 * @return dirArray
	 */
	public ArrayList<Integer> getDirArray() {
		return dirArray;
	}
	
	public int getNumTurns() {
		int turns = 0;
		int dir = -1; 
		for (int i : dirArray) {
			if (i != dir) {
				turns ++;
				dir = i;
			}
		}
		return turns;
	}
}
