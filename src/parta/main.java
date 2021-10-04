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
	            
//	            int counter = jsonObj_dist.size();
//	            
//	            Iterator<String> keys = jsonObj_dist.keySet().iterator();
//	            
//	            while(keys.hasNext()) {
//	                String key = keys.next();
//	                System.out.println(key);
//	                
//	                if (counter % 2 == 0) {
//	                	String[] connection = key.split(",");
//	                	double distance = Double.valueOf(jsonObj_dist.get(key).toString());
//	                	double cost = Double.valueOf(jsonObj_cost.get(key).toString());
//	                	
//	                	graph.addEdge(connection[0], connection[1], distance, cost);
//	                }
//	                counter --;
//	                
//	            }
//	            
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
	                	
	                	
//	                	System.out.println(connection + " " + distance);
	                	graph.addEdge(String.valueOf(i), dest, distance, cost);
	  

	                }
	                
	            }
	            
	            //System.out.println(graph.getEdges().get(1));
	            
	            parta.partA("1", "50", graph, jsonObj);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			
			
	
	       
	        
	    }
}
