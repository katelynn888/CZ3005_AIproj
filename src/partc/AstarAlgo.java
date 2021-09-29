package partc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AstarAlgo {

	public static Node aStar(Node start, Node target){
	    PriorityQueue<Node> closedList = new PriorityQueue<>();
	    PriorityQueue<Node> openList = new PriorityQueue<>();

	    start.f = start.g + start.calculateHeuristic(target);
	    openList.add(start);

	    while(!openList.isEmpty()){
	        Node n = openList.peek();
	        if(n == target){
	            return n;
	        }

	        for(Node.Edge edge : n.neighbors){
	            Node m = edge.node;
	            double totalWeight = n.g + edge.weight;

	            if(!openList.contains(m) && !closedList.contains(m)){
	                //  n is the current and m is the next node
	            	m.parent = n;
	                m.g = totalWeight;
	                m.f = m.g + m.calculateHeuristic(target);
	                openList.add(m);
	            } else {
	                if(totalWeight < m.g){
	                    m.parent = n;
	                    m.g = totalWeight;
	                    m.f = m.g + m.calculateHeuristic(target);

	                    if(closedList.contains(m)){
	                        closedList.remove(m);
	                        openList.add(m);
	                    }
	                }
	            }
	        }

	        openList.remove(n);
	        closedList.add(n);
	    }
	    return null;
	}
	
	public static void getTotalCost(Node target) {
		Node n = target;

	    if(n==null)
	        return;

	    double total =0 ;

	    while(n.parent != null){
	    	total += n.f;
	    	n = n.parent;
	    }
	    
		System.out.println("The total cost value: "+ total);
	}
	
	public static void printPath(Node target){
	    Node n = target;

	    if(n==null)
	        return;

	    List<Integer> ids = new ArrayList<>();

	    while(n.parent != null){
	        ids.add(n.id);
	        n = n.parent;
	    }
	    ids.add(n.id);
	    Collections.reverse(ids);

	    for(int id : ids){
	        System.out.print(id + " ");
	    }
	    System.out.println("");
	}

}
