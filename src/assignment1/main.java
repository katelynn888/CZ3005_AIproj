package assignment1;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {

	public static void main(String[] args) {

		Graph graph = new Graph();
		String start = "1";
		String target = "50";

		// Reading map network file & creating a graph
		String fileG = "G.json";
		String fileDist = "Dist.json";
		String fileCoord = "Coord.json";
		String fileCost = "Cost.json";

		JSONParser parser = new JSONParser();

		try {
			// Parsing JSON
			Object obj = parser.parse(new FileReader(fileG));
			Object distObj = parser.parse(new FileReader(fileDist));
			Object coordObj = parser.parse(new FileReader(fileCoord));
			Object costObj = parser.parse(new FileReader(fileCost));

			JSONObject jsonObj = (JSONObject) obj; // G
			JSONObject jsonObj_dist = (JSONObject) distObj; // distances
			JSONObject jsonObj_coord = (JSONObject) coordObj; // coord
			JSONObject jsonObj_cost = (JSONObject) costObj; // cost

			// Getting target coordinates
			JSONArray targetCoordJson = (JSONArray) jsonObj_coord.get(target);

			String x_str = targetCoordJson.get(0).toString();
			int targetX = Integer.valueOf(x_str);

			String y_str = targetCoordJson.get(1).toString();
			int targetY = Integer.valueOf(y_str);

			int[] targetCoord = { targetX, targetY };

			for (int i = 1; i <= jsonObj_coord.size(); i++) {

				String node = String.format("%d", i);

				// Getting array of neighbour nodes of current node
				JSONArray connectedNodes = (JSONArray) jsonObj.get(node);

				// Getting node coordinate of current node
				JSONArray nodeCoord = (JSONArray) jsonObj_coord.get(node);

				String xStr = nodeCoord.get(0).toString();
				int x = Integer.valueOf(xStr);

				String yStr = nodeCoord.get(1).toString();
				int y = Integer.valueOf(yStr);

				int[] curCoord = { x, y };

				// Calculate heuristic function of each node
				// Heuristic function = straight line distance to target node
				graph.calculateHeuristic(node, target, curCoord, targetCoord);

				for (int j = 0; j < connectedNodes.size(); j++) {
					String connection = i + "," + connectedNodes.get(j);

					String distStr = jsonObj_dist.get(connection).toString();
					String costStr = jsonObj_cost.get(connection).toString();
					double distance = Double.valueOf(distStr);
					double cost = Double.valueOf(costStr);

					String dest = connectedNodes.get(j).toString();

					// Creating edges in the graph
					graph.addEdge(String.valueOf(i), dest, distance, cost);
				}

			}

			// Running the programs

			// UCS
			System.out.println("Task 1:");
			parta.partA(start, target, graph, jsonObj);
			System.out.println("\n");

			// UCS with energy constraint
			System.out.println("Task 2:");
			partb.partB(start, target, graph, jsonObj);
			System.out.println("\n");

			// A-Star Search
			System.out.println("Task 3:");
			partc.partC(start, target, graph, jsonObj);
			System.out.println("\n");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
