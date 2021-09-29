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


public class main {
	public static void main(String args[]) throws IOException {

		// reading map network file & creating a graph
		String fileG = "G.json";
		String fileDist = "Dist.json";
		String fileCoord = "Coord.json";
		
		JSONParser parser = new JSONParser();
		
		try {     
			Object obj = parser.parse(new FileReader(fileG));
            Object distObj = parser.parse(new FileReader(fileDist));
            Object coordObj = parser.parse(new FileReader(fileCoord));
            
            JSONObject jsonObj = (JSONObject) obj; //G 
            JSONObject jsonObj_dist = (JSONObject) distObj; //distances
            JSONObject jsonObj_coord = (JSONObject) coordObj; //coord
            
            System.out.println(jsonObj_coord.size());
//            JSONArray allNodes = new JSONArray();
            
            
            for (int i=1; i<=5; i++) {
            	String node = String.format("%d", i);
            	JSONArray connectedNodes = (JSONArray) jsonObj.get(node);
            	
//            	JSONObject combinedObj = new JSONObject();
//                System.out.println(connectedNodes);
                
            	JSONArray destList = new JSONArray();
            	
                
                for (int j=0; j<connectedNodes.size(); j++) {
//                	JSONObject d = new JSONObject();
//                	d.put("cost", 0);
//                	d.put("distance", 0);
//                	d.put("id", connectedNodes.get(j));
                	
                
                	
                	String connection = i + "," + connectedNodes.get(j);
//                	double distance = (double) jsonObj_dist.get("1,2");
                	
                	if (jsonObj_dist.get(connection) instanceof Long) {
                		
                	}
                	
//                	System.out.print(jsonObj_dist.get(connection).getClass().getSimpleName());
                	
                	System.out.println(connection);
                	System.out.println(connectedNodes);
                	System.out.println(jsonObj_dist.get(connection));
                	
                	

//                	destList.add(d);
                }
//                
//                combinedObj.put("destinations", destList);
//                combinedObj.put("source", node);
//                
//                allNodes.add(combinedObj);
                
            }
            
//            System.out.println(allNodes);
            
//            Iterator<String> keys = jsonObj_dist.keySet().iterator();
//
//            while(keys.hasNext()) {
//                String key = keys.next();
//                System.out.println(key);
//                if (jsonObj_dist.get(key) instanceof JSONObject) {
//                      // do something with jsonObject here    
//                	System.out.println(jsonObj_dist.get(key));
//                }
//            }
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
