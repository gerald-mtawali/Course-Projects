// Name: Gerald Mtawali 
// Student number: 15264999

/*
 * Dijkstra's Algorithm methodology:
 * 		Create a vertex set Q 
 * 		for every vertex in the graph: 
 * 			dist[v] = INF 
 * 			prev[v] = UNDEFINED 
 * 			add v to Q 
 *		dist[source] = 0 
 *		while Q is not empty: 
 *			u = vertex from Q with min dist[u] // source is added first 
 *			pop u from Q 
 *			
 *			for every neighbor node in the adjacencies list of vertex u: 
 *				alt = dist[u] + weight(u,v)							// add the length of the edge between the two vertices to the distance to the node we are on
 *				if alt < dist[v]: 
 *					dist[v] = alt 
 *					prev[v] = u 
 * */


import java.util.ArrayList;
import java.util.List;
 
public class Graph {
	
	private List<Vertex> verticesList;					// vertex map 

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.MAX_VALUE);
		}
	}
	////// Implement the methods below this line //////
	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {

		// Your code here
		/* source is origin, target is where we wanna reach 
		 * set Distance of the source node to 0 
		 * init a Priority Q 
		 * visit every Vertex in the verticesList 
		 * 	loop around the edges of the current vertex 
		 * 
		 * loop through the current edges
		 * 	get the index of the node in the 
		 * */
		sourceVertex.setDistance(0.0);
		// create a Q with which to go through the Vertices 
		ArrayList<Vertex> vertexQueue = new ArrayList<>();
		// add the source vertex to the Queue 
		vertexQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
		while(!vertexQueue.isEmpty())
		{
			// Poll the current Vertex from the Queue aka retrieve and rome the head of the queue 
			Vertex currVert = vertexQueue.get(0);
			vertexQueue.remove(0);
			//Vertex currVert = vertexQueue.get(idx);
			//System.out.println("The current Vertex is "+currVert.getName());
			for(Edge anEdge: currVert.getAdjacenciesList())
			{
				Vertex nextV = anEdge.getEndVertex();
				if(!nextV.isVisited())
				{
					double newDistance = currVert.getDistance() + anEdge.getWeight();
					if(newDistance < nextV.getDistance())
					{
						// remove v from the priority queue again
						if(vertexQueue.contains(nextV)){
							vertexQueue.remove(vertexQueue.indexOf(nextV));
							
						}
						nextV.setDistance(newDistance);
						nextV.setPredecessor(currVert);
						// change the  vertex with which we add 
						vertexQueue.add(nextV);
					}
				}
			}
			currVert.setVisited(true);
		}
		List<Vertex> path = new ArrayList<>();
		if(targetVertex.getDistance() == Double.MAX_VALUE)
			return path;
		for(Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor())
		{
			path.add(vertex);
		}
		List<Vertex> shortPath =  new ArrayList<>();
		for( int i = path.size()-1; i >=0; i--)
		{
			shortPath.add(path.get(i));
		}
		return shortPath;
	}

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {

		// Your code here
		// returns the euclidian distance between two paths
		List<Vertex> aShortPath = this.getShortestPath(sourceVertex, targetVertex);
		if(targetVertex.getDistance() == Double.MAX_VALUE)
			return targetVertex.getDistance();
		int idx = aShortPath.size()-1;
		double distance = aShortPath.get(idx).getDistance() - aShortPath.get(0).getDistance();
		return distance;
		
	}

}