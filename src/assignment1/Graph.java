package assignment1;

import java.util.*;

// Graph class
public class Graph {

	// Hashtable to store edges of the graph
	// Key: Node 1
	// Value: Node 2
	final private Hashtable<String, String> edges;

	// Hashtable to store distances between 2 connected nodes
	// Key: Pair of connected nodes
	// Value: Distance between the pair
	final private Hashtable<String, Double> distance;

	// Hashtable to store energy costs between 2 connected nodes
	// Key: Pair of connected nodes
	// Value: Energy cost between the pair
	final private Hashtable<String, Double> costs;

	// Hashtable to store heuristic costs of each node
	// Key: Node
	// Value: Heuristic Cost
	final private Hashtable<String, Double> heuristic;

	// Constructor
	public Graph() {
		this.edges = new Hashtable<String, String>();
		this.distance = new Hashtable<String, Double>();
		this.costs = new Hashtable<String, Double>();
		this.heuristic = new Hashtable<String, Double>();
	}

	// Getters
	public Hashtable<String, String> getEdges() {
		return edges;
	}

	public Hashtable<String, Double> getDistance() {
		return distance;
	}

	public Hashtable<String, Double> getCosts() {
		return costs;
	}

	public Hashtable<String, Double> getHeuristic() {
		return heuristic;
	}

	// Method to add edge
	public void addEdge(String source, String dest, double distance, double energy) {
		this.edges.put(source, dest);
		this.edges.put(dest, source);
		this.distance.put(source + "," + dest, distance);
		this.costs.put(source + "," + dest, energy);
	}

	// Method to calculate heuristic costs for each node
	// Heuristic Function: Straight line distance from node to target node
	// Assigns heuristic costs into hashtable, with key: node, value: heuristic cost
	public double calculateHeuristic(String curNode, String target, int[] curCoord, int[] targetCoord) {
		double straightLineDistance = Math
				.sqrt(Math.pow((targetCoord[0] - curCoord[0]), 2) + Math.pow((targetCoord[1] - curCoord[1]), 2));
		this.heuristic.put(curNode, straightLineDistance);

		return straightLineDistance;
	}

}
