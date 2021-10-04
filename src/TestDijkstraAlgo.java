
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestDijkstraAlgo {

    private static List<Vertex> nodes;
    private static List<Edge> edges;


    public static void main(String[] args) { 
    	
		// reading map network file & creating a graph
		String fileG = "G.json";
		String fileDist = "Dist.json";
		String fileCoord = "Coord.json";
		String fileCost = "Cost.json";
		
		JSONParser parser = new JSONParser();
		
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
       
		
		try {     
            Object obj = parser.parse(new FileReader(fileG));
            Object distObj = parser.parse(new FileReader(fileDist));
            Object coordObj = parser.parse(new FileReader(fileCoord));
            Object costObj = parser.parse(new FileReader(fileCost));
            
            JSONObject jsonObj = (JSONObject) obj; //G 
            JSONObject jsonObj_dist = (JSONObject) distObj; //distances
            JSONObject jsonObj_coord = (JSONObject) coordObj; //coord
            JSONObject jsonObj_cost = (JSONObject) costObj; //cost
            
            
            // adding nodes
            for (int i = 1; i <= jsonObj_coord.size(); i++) {
                Vertex location = new Vertex(String.valueOf(i), String.valueOf(i));
                nodes.add(location);
            }
            
            
//            addLane("Edge_0", 0, 1, 85);
//            addLane("Edge_1", 0, 2, 217);
//            addLane("Edge_2", 0, 4, 173);
//            addLane("Edge_3", 2, 6, 186);
//            addLane("Edge_4", 2, 7, 103);
//            addLane("Edge_5", 3, 7, 183);
//            addLane("Edge_6", 5, 8, 250);
//            addLane("Edge_7", 8, 9, 84);
//            addLane("Edge_8", 7, 9, 167);
//            addLane("Edge_9", 4, 9, 502);
//            addLane("Edge_10", 9, 10, 40);
//            addLane("Edge_11", 1, 10, 600);

            
            int k=1;
            for (int i=1; i<= jsonObj_coord.size(); i++) {
            	
            	String node = String.format("%d", i);
            	JSONArray connectedNodes = (JSONArray) jsonObj.get(node);
            	
                
                for (int j=0; j < connectedNodes.size(); j++) {
                	String connection = i + "," + connectedNodes.get(j);  
   
                	String distStr = jsonObj_dist.get(connection).toString();
                	String costStr = jsonObj_cost.get(connection).toString();
                	double distance = Double.valueOf(distStr);
                	double cost = Double.valueOf(costStr);
                	
                	int dest = Integer.valueOf(connectedNodes.get(j).toString());   
                	
                	
                	System.out.println(connection + " " + distance);
                	addLane("Edge_" + k, i, dest, distance, cost);
                	k++;

                }
                
            }
            
                     
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // from location 1 to 2
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        
        System.out.println("Done2");
        
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(49));
        
        System.out.println("Done3");

        double shortestDist = 0;
//        double energyCost = 0;
        
        System.out.println("Shortest path: ");
        for (Vertex vertex : path) {
            System.out.print(vertex + " -> "); 
            shortestDist = dijkstra.getShortestDistance(vertex);
            
        }
        
        System.out.println("Shortest Distance: " + shortestDist);
        
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
            double distance, double cost) {
    	
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo-1), nodes.get(destLocNo-1), distance, cost );
        edges.add(lane);
        
    }
}