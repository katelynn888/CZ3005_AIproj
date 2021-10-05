package parta;
import java.util.*;

import partc.Node;

public class Graph {
	final private Hashtable<String, String> edges;
	final private Hashtable<String, Double> distance;
	final private Hashtable<String, Double> costs;
	final private Hashtable<String, int[]> coord;
	final private Hashtable<String, Double> heuristic; //stores heuristic fn for each node
	
	
	public Graph() {
		this.edges = new Hashtable<String, String>();
		this.distance = new Hashtable<String, Double>();
		this.costs = new Hashtable<String, Double>();
		this.coord = new Hashtable<String, int[]>();
		this.heuristic = new Hashtable<String, Double>();
	}
	
	public Hashtable<String, String> getEdges() {
		return edges;
	}

	public Hashtable<String, Double> getDistance() {
		return distance;
	}

	public Hashtable<String, Double> getCosts() {
		return costs;
	}

	public void addEdge(String source, String dest, double distance, double energy) {
		this.edges.put(source, dest);
		this.edges.put(dest, source);
		this.distance.put(source+","+dest, distance);
		this.costs.put(source+","+dest, energy);
	}
	
	public double calculateHeuristic(String curNode, String target, int[] curCoord, int[] targetCoord){
		this.coord.put(curNode, curCoord);
		this.coord.put(target, targetCoord);
		 
	    double straightLineDistance = Math.sqrt(Math.pow((targetCoord[0] - curCoord[0]), 2) + Math.pow((targetCoord[1] - curCoord[1]), 2));
	    this.heuristic.put(curNode, straightLineDistance);
	    
	    return straightLineDistance;
	}
	
	
	
}
