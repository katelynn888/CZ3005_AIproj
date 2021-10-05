package parta;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
	
	public static void main(String[] args) { 
		
		Graph graph = new Graph();
	    	
			// reading map network file & creating a graph
			String fileG = "G.json";
			String fileDist = "Dist.json";
			String fileCoord = "Coord.json";
			String fileCost = "Cost.json";
			
			JSONParser parser = new JSONParser();
			
			try {     
	            Object obj = parser.parse(new FileReader(fileG));
	            Object distObj = parser.parse(new FileReader(fileDist));
	            Object coordObj = parser.parse(new FileReader(fileCoord));
	            Object costObj = parser.parse(new FileReader(fileCost));
	            
	            JSONObject jsonObj = (JSONObject) obj; //G 
	            JSONObject jsonObj_dist = (JSONObject) distObj; //distances
	            JSONObject jsonObj_coord = (JSONObject) coordObj; //coord
	            JSONObject jsonObj_cost = (JSONObject) costObj; //cost
	            
	            for (int i=1; i<= jsonObj_coord.size(); i++) {
	            	
	            	String node = String.format("%d", i);
	            	JSONArray connectedNodes = (JSONArray) jsonObj.get(node);
	                
	                for (int j=0; j < connectedNodes.size(); j++) {
	                	String connection = i + "," + connectedNodes.get(j);  
	   
	                	String distStr = jsonObj_dist.get(connection).toString();
	                	String costStr = jsonObj_cost.get(connection).toString();
	                	double distance = Double.valueOf(distStr);
	                	double cost = Double.valueOf(costStr);
	                	
	                	String dest = connectedNodes.get(j).toString();   

	                	graph.addEdge(String.valueOf(i), dest, distance, cost);
	                }
	                
	            }
	            System.out.println("Part A:");
	            parta.partA("1", "50", graph, jsonObj);
	            System.out.println("Part B:");
	            partb.partB("1", "50", graph, jsonObj);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
}
