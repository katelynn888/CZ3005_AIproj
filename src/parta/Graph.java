package parta;
import java.util.*;

public class Graph {
	final private Hashtable<String, String> edges;
	final private Hashtable<String, Double> distance;
	final private Hashtable<String, Double> costs;
	
	public Graph() {
		this.edges = new Hashtable<String, String>();
		this.distance = new Hashtable<String, Double>();
		this.costs = new Hashtable<String, Double>();
		
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
		//this.distance.put(dest+","+source, distance);
		this.costs.put(source+","+dest, energy);
		//this.costs.put(dest+","+source, energy);
	}
}
