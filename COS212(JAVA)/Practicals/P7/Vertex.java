import java.util.ArrayList;
import java.util.List;
 
public class Vertex implements Comparable<Vertex> {
 
	private String name;
	private List<Edge> adjacenciesList;							// list of all the edges connected to this particular node
	private double distance = Double.MAX_VALUE;					// distance from whatever source is connected to it 

	private boolean visited = false;
	private Vertex predecessor;
 
	public Vertex(String name) {
		this.name = name;
		this.adjacenciesList = new ArrayList<>();
	}
 
	public void addNeighbour(Edge edge) {
		this.adjacenciesList.add(edge);
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public List<Edge> getAdjacenciesList() {
		return adjacenciesList;
	}
 
	public void setAdjacenciesList(List<Edge> adjacenciesList) {
		this.adjacenciesList = adjacenciesList;
	}

	public double getDistance() {
		return distance;
	}
 
	public void setDistance(double distance) {
		this.distance = distance;
	}
 
	public boolean isVisited() {
		return visited;
	}
 
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
 
	public Vertex getPredecessor() {
		return predecessor;
	}
 
	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}
 
	@Override
	public String toString() {
		return this.name;
	}
 
	@Override
	public int compareTo(Vertex otherVertex) {
		return Double.compare(this.distance, otherVertex.getDistance());
	}
}