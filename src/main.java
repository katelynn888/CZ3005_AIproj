import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
	public static void main(String args[]) throws IOException {

		// reading map network file & creating a graph
		String graph = "G.json";
		String dist = "Dist.json";
		JSONParser parser = new JSONParser();
		
		try {     
            Object obj = parser.parse(new FileReader(dist));
            
            JSONValue jsonObj = (JSONValue) obj;
            
            for (int i=1; i<=5; i++) {
            	//int node = String.format("%d", i);
            	;
                System.out.println(jsonObj);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
