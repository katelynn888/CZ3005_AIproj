package assignment1;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class partb {

	public static void partB(String start, String end, Graph graph, JSONObject jsonObj) {
		// Path to print
		ArrayList<String> path = new ArrayList<String>();

		// Shortest Total Distance & Total Cumulative Energy Cost for the whole path
		Double shortestDist = 0.0;
		Double totalEnergy = 0.0;
		Double energy_budget = 287932.0;

		// Unvisited Set: Stores visited nodes that has not been expanded -> list of
		// nodes to visit
		Set<String> unvisited = new HashSet<String>();
		unvisited.add(start);

		// Visited Set: Stores visited nodes that have been visited and expanded
		Set<String> visited = new HashSet<String>();

		// Hashtable to store cumulative distance to each node from start node
		// Key: Node (String)
		// Value: Distance (Double)
		Hashtable<String, Double> pathCost = new Hashtable<String, Double>();
		pathCost.put(start, 0.0);

		// Hashtable to store cumulative energy cost to each node from start node
		// Key: Node (String)
		// Value: Energy (Double)
		Hashtable<String, Double> energyCost = new Hashtable<String, Double>();
		energyCost.put(start, 0.0);

		// Hashtable to store the path until we get to the target destination
		// Key: Next Node
		// Value: Current Node
		Hashtable<String, String> prevNode = new Hashtable<String, String>();
		prevNode.put(start, start);

		// While there are still nodes to visit
		while (unvisited.size() > 0) {
			String curNode = "";

			for (String node : unvisited) {
				if (curNode.equals("") || pathCost.get(node) < pathCost.get(curNode)) {
					curNode = node;
				}
			}
			// when there is no valid path, print error msg
			if (curNode.equals("")) {
				System.out.println("No path found");
				return;
			}
			// if we have reached the end, we print the path
			if (curNode.equals(end)) {

				// Get shortest distance and total energy from start to end
				shortestDist = pathCost.get(curNode);
				totalEnergy = energyCost.get(curNode);

				// Adding nodes from the prevNode Hashtable into path ArrayList
				while (prevNode.get(curNode) != curNode) {
					path.add(curNode);
					curNode = prevNode.get(curNode);
				}

				path.add(start);

				// Reverse the arraylist as we added from the target node to the start node
				Collections.reverse(path);

				System.out.print("Shortest path: ");

				for (String i : path) {
					if (i.equals(end)) {
						System.out.print(i);
					} else
						System.out.print(i + "->");
				}
				System.out.println();
				System.out.println("Shortest distance: " + shortestDist);
				System.out.println("Total energy cost: " + totalEnergy);
				return;

			}

			// Get array of neighbour nodes of current node
			JSONArray connectedNodes = (JSONArray) jsonObj.get(curNode);

			// For each node in the neighbour node array, we check whether it is unexplored
			for (Object node : connectedNodes) {
				String nodeStr = node.toString();

				if (!unvisited.contains(nodeStr) && !visited.contains(nodeStr)) {

					Double cumulatedEnergy = energyCost.get(curNode) + graph.getCosts().get(curNode + "," + nodeStr);
					Double cumulatedDist = pathCost.get(curNode) + graph.getDistance().get(curNode + "," + nodeStr);

					// cumulated energy exceeds the energy_budget, it skips the current iteration
					// and continues on to the next iteration in the for loop.
					if (cumulatedEnergy > energy_budget) {
						continue;
					}

					// if node is unvisited, we add this node into the unvisited set
					unvisited.add(nodeStr);

					// Assign current node to be the value of the neighbour node
					prevNode.put(nodeStr, curNode);

					// Assigning cumulative energy and distance costs for respective neighbour node
					energyCost.put(nodeStr, cumulatedEnergy);
					pathCost.put(nodeStr, cumulatedDist);

				}
				// Node is visited before and is in the visited/unvisited sets
				else {
					Double cumulatedEnergy = energyCost.get(curNode) + graph.getCosts().get(curNode + "," + nodeStr);
					Double cumulatedDist = pathCost.get(curNode) + graph.getDistance().get(curNode + "," + nodeStr);

					// If cumulated distance is smaller than cumulated distance of the neighbour
					// node to current node
					// we assign new cumulated distance
					if (pathCost.get(nodeStr) > cumulatedDist) {

						// Assigning updated cumulative energy and distance costs for respective
						// neighbour node
						pathCost.put(nodeStr, cumulatedDist);
						energyCost.put(nodeStr, cumulatedEnergy);
						prevNode.put(nodeStr, curNode);

						// cumulated energy exceeds the energy_budget, it skips the current iteration
						// and continues on to the next iteration in the for loop.
						if (cumulatedEnergy > energy_budget) {
							continue;
						}

						// remove neighbour node from visited set, and add to unvisited set
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
		// if unvisited is empty, print no path found
		System.out.println("No path found");
		return;
	}
}
