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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
	public static void main(String args[]) throws IOException {

		// reading map network file & creating a graph
		String filepath = "G.json";
		JSONParser parser = new JSONParser();
		
		try {     
            Object obj = parser.parse(new FileReader(filepath));
            
            JSONObject jsonObj = (JSONObject) obj;
            
            for (int i=1; i<=5; i++) {
            	String node = String.format("%d", i);
            	JSONArray One = (JSONArray) jsonObj.get(node);
                System.out.println(One);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
