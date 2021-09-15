import java.util.LinkedList;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
	private int source;
	private int nodeID;
	private int distance;
	private int cost;
	private ArrayList<Integer> path;

	public Node(int src, int nId, int dist, int cost, ArrayList<Integer> path) {
		this.source = src;
		this.nodeID = nId;
		this.distance = dist;
		this.cost = cost;
		this.path = path;
	}
	
	public void setSource(int source) {
		this.source = source;
	}

	public int getSource() {
		return source;
	}

	public int getNodeID() {
		return nodeID;
	}

	public int getDistance() {
		return distance;
	}
	
	public int getCost() {
		return cost;
	}
	
	public ArrayList<Integer> getPath() {
		return path;
	}

	public String toString() {
		return ("Distance from Node #" + this.source +" to Node #" + this.nodeID + ": " + this.distance + 
				"\nShortest path to Node #" + this.nodeID + ": " + this.path +"\n");
	}
	
	public int compareTo(Node other) {
		return this.distance - other.distance;
	}

}
