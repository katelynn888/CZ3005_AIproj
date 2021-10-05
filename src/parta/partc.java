package parta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.json.simple.JSONObject;

public class partc {
	public static void partC (String start, String end, Graph graph, JSONObject jsonObj) {
		ArrayList<String> path = new ArrayList<String>();
		Double shortestDist = 0.0;
		Double totalEnergy = 0.0;
		
		Set<String> unvisited = new HashSet<String>();
		unvisited.add(start);
		
		Set<String> visited = new HashSet<String>();
		
		Hashtable<String, Double> pathCost = new Hashtable<String, Double>();
		pathCost.put(start, 0.0);
		
		Hashtable<String, Double> energyCost = new Hashtable<String, Double>();
		energyCost.put(start, 0.0);
		
		Hashtable<String, String> prevNode = new Hashtable<String, String>();
		prevNode.put(start, start);
		
	}
}
