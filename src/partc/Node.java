package partc;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Node implements Comparable<Node> {
    // Id for readability of result purposes
    private static int idCounter = 0;
    public int id;
    public Node parent = null;
    public List<Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE; //cumulative total cost = cumulative distance + strline dist
    public double g = Double.MAX_VALUE; //cumulative distance 
    public double h; //str line distance
    public double energyCost; // cumulative energy cost
    
    // Coordinates
    public double x; 
    public double y;

    Node(double x, double y){
          this.x = x;
          this.y = y;
          this.id = idCounter++;
          this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
          return Double.compare(this.f, n.f);
    }

    public static class Edge {
    	public Node node;
    	public double distance;
        public double energyCost;
        
        Edge(Node node, double distance, double energyCost){                
        	  this.node = node;
        	  this.distance = distance;
              this.energyCost = energyCost;
        }

          
    }

    public void addBranch(Node node, double distance, double energy){
          Edge newEdge = new Edge(node, distance, energy);
          neighbors.add(newEdge);
    }
  
    // calculate straight line distance from current node to target node
    public double calculateHeuristic(Node target){
    	double strDistance = Math.sqrt(Math.pow((target.x - this.x), 2) + Math.pow((target.y - this.y), 2));
        this.h = strDistance;
    	return h;
    }
}