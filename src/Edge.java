
public class Edge  {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final double distance;
    private final double cost;

    public Edge(String id, Vertex source, Vertex destination, double distance, double cost) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }
    
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}