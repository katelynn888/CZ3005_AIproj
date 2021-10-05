package partc;

import java.util.PriorityQueue;


import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import parta.parta;
import parta.partb;

import java.util.List;
import java.util.Comparator;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TestAstarAlgo{


	public static void main(String[] args) {
		
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
                JSONArray coord  = (JSONArray) jsonObj_coord.get(String.valueOf(i));
                JSONArray connectedNodes = (JSONArray) jsonObj.get(String.valueOf(i));
                
                String x_str = coord.get(0).toString();
                int x = Integer.valueOf(x_str);
                
                String y_str = coord.get(0).toString();
                int y = Integer.valueOf(y_str);
                
                Node node = new Node(x,y);
                
                if (i==1) {
                	node.g = 0;
                }
                
                for (int j=0; j < connectedNodes.size() ; j++) {
                	String destNode = connectedNodes.get(j).toString();
                	String connection = i + "," + connectedNodes.get(j); 
                	
                	String distStr = jsonObj_dist.get(connection).toString();
                	String costStr = jsonObj_cost.get(connection).toString();
                	double distance = Double.valueOf(distStr);
                	double cost = Double.valueOf(costStr);
                	
                	JSONArray destCoord  = (JSONArray) jsonObj_coord.get(String.valueOf(destNode));
                	
                	String destx_str = destCoord.get(0).toString();
                    int destx = Integer.valueOf(destx_str);
                    
                    String desty_str = destCoord.get(0).toString();
                    int desty = Integer.valueOf(desty_str);
                	
                	
                	Node dest = new Node(destx, desty);
                	
                	node.addBranch(dest, distance, cost); //node, weight, cost
                }

            }
            
            Node head = new Node(-73530767, 41085396);
            Node target = new Node(-73643471, 41026897);
            
    	    Node res = AstarAlgo.aStar(head, target);
    	    System.out.println("hello");
    	    AstarAlgo.printPath(res);
    	    System.out.println("hello2");
    	    AstarAlgo.getTotalDistance(res);
    	    
    	    System.out.println("hello1");
//    	    AstarAlgo.getTotalCost(res);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        
}




