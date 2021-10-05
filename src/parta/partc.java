package parta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class partc {
	
	public static void partC (String start, String end, Graph graph, JSONObject jsonObj) {
		ArrayList<String> path = new ArrayList<String>();
		Double shortestDist = 0.0;
		Double totalEnergy = 0.0;
		Double energy_budget= 287932.0;
		
		Set<String> unvisited = new HashSet<String>();
		unvisited.add(start);
		
		Set<String> visited = new HashSet<String>();
		
		Hashtable<String, Double> pathCost = new Hashtable<String, Double>();
		pathCost.put(start, 0.0);
		
		Hashtable<String, Double> energyCost = new Hashtable<String, Double>();
		energyCost.put(start, 0.0);
		
		Hashtable<String, String> prevNode = new Hashtable<String, String>();
		prevNode.put(start, start);
		
		Double totalCost = 0.0;
		
		while (unvisited.size() > 0) {
			String curNode = start;
			totalCost = pathCost.get(curNode) + graph.getHeuristic().get(curNode);
			for (String node : unvisited) {
				if (curNode == "" || pathCost.get(node) + graph.getHeuristic().get(node) < totalCost) {
					curNode = node;
				}
			}
			
			if (curNode.equals("")) {
				System.out.println("No path found");
				return;
			}
			
			if (curNode.equals(end)) {
				shortestDist = pathCost.get(curNode);
				totalEnergy = energyCost.get(curNode);
				
				while (prevNode.get(curNode) != curNode) {
					path.add(curNode);
					curNode = prevNode.get(curNode);
				}
				
				path.add(start);
				Collections.reverse(path);
				
				System.out.print("Shortest path: ");
				
	            for (String i : path) {
	            	if (i != end) {
	            		System.out.print(i + "->");
	            	}
	            	else System.out.print(i);
	            }
	            System.out.println();    
	            System.out.println("Shortest distance: " + shortestDist);
	            System.out.println("Total energy cost: " + totalEnergy);
	            return;
			}
			
			JSONArray connectedNodes = (JSONArray) jsonObj.get(curNode);
			
			for (Object node : connectedNodes) {
				String nodeStr = node.toString();
				totalEnergy = energyCost.get(nodeStr); //cummulative energy cost for current node
				if (!unvisited.contains(nodeStr) && !visited.contains(nodeStr) && totalEnergy < 287932) {
					
					Double cumulatedEnergy = energyCost.get(curNode) + graph.getCosts().get(curNode+","+nodeStr);
					Double cumulatedDist = pathCost.get(curNode) + graph.getDistance().get(curNode+","+nodeStr);
					
					energyCost.put(nodeStr, cumulatedEnergy);
					if (cumulatedEnergy>energy_budget) {
						continue;
					}
					unvisited.add(nodeStr);
					prevNode.put(nodeStr, curNode);
					pathCost.put(nodeStr, cumulatedDist);
					
				} 
				else {
					Double cumulatedEnergy = energyCost.get(curNode) + graph.getCosts().get(curNode+","+nodeStr);
					Double cumulatedDist = pathCost.get(curNode) + graph.getDistance().get(curNode+","+nodeStr);
					if (pathCost.get(nodeStr) > cumulatedDist && totalEnergy < 287932) {
						pathCost.put(nodeStr, cumulatedDist);
						energyCost.put(nodeStr, cumulatedEnergy);
						prevNode.put(nodeStr, curNode);
						if (cumulatedEnergy>energy_budget) {
							continue;
						}
						if (visited.contains(nodeStr)) {
							visited.remove(nodeStr);
							unvisited.add(nodeStr);
						}
					}
				}
			}

			// add current node to visited & remove from unvisited
	        unvisited.remove(curNode);
	        visited.add(curNode);	
		}
		
	}
}
