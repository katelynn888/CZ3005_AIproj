package partc;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Node implements Comparable<Node> {
    // Id for readability of result purposes
    private static int idCounter = 0;
    public int id;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;

    public double h; 
    public double x; 
    public double y;
    public double cost;

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
          Edge(int weight, Node node, double cost){
                this.weight = weight;
                this.node = node;
                this.cost = cost;
          }

          public int weight;
          public Node node;
          public double cost;
    }

    public void addBranch(int weight, Node node, double energy){
          Edge newEdge = new Edge(weight, node, energy);
          neighbors.add(newEdge);
    }
  
    // calculate straight line distance from current node to target node
    public double calculateHeuristic(Node target){
    	double strDistance = Math.sqrt(Math.pow((target.x - this.x), 2) + Math.pow((target.y - this.y), 2));
        this.h = strDistance;
    	return h;
    }
}