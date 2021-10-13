import java.util.ArrayList;
import java.util.Collections;

/**
 * Name: Gerald Mtawali 
 * Student Number: 15264999
 */

public class GraphSelectionSort {
    private int v_count;							// current number of vertices in the graph 
	private ArrayList<Integer> g_verts;
    private ArrayList<Edge> g_edges;				// the current edges within the graph 
	private ArrayList<Integer> sorted;
    /**
     * Initialize a new object using the array of Edges
     */
    public GraphSelectionSort(Edge[] edges) {
        // TODO: your code here..
    	sorted = new ArrayList<Integer>();								// initialize the sorted array that the selection sort returns    	
    	g_edges = new ArrayList<Edge>();	 
    	g_verts = new ArrayList<Integer>();
    	// need to create an array of vertices 
    	for(Edge c_edge: edges)
    	{
    		g_edges.add(c_edge);				
    		if(!g_verts.contains(c_edge.source.getValue())){
    			//System.out.println(c_edge.source.getValue());
    			g_verts.add(c_edge.source.getValue());
    		}
    		if(!g_verts.contains(c_edge.target.getValue())){
    			//System.out.println(c_edge.target.getValue());
    			g_verts.add(c_edge.target.getValue());
    		}
    	}
    	Collections.sort(g_verts);
    	//System.out.println("The integer array is "+g_verts);
    	v_count = g_verts.size();
    }

    /**
     * Return the array of sorted values.
     */
    public Integer[] getSorted() {
        // TODO: your code here...
    	// output of this contributes 18 marks 
    	Integer [] ret_sorted = new Integer[sorted.size()];
    	int idx = 0; 
    	for(Integer i: sorted)
    	{
    		ret_sorted[idx] = i;
    		idx++;
    	}
    	//System.out.println("The sorted array currently is ");
    	//for(Integer i: sorted)
    	//	System.out.print(i+" ");
    	//System.out.print("\n");
    	return ret_sorted;
    }

    /**
     * Return the edges that are still in the graph
     */
    public Edge[] getEdges() {
        // TODO: your code here...
    	// Output of this contributes 22 marks 
    	Edge[] ret_edge = new Edge[g_edges.size()];
    	int idx = 0;
    	for(Edge c_edge: g_edges)
    	{
    		ret_edge[idx] = c_edge;
    		idx++;
    	}
    	return ret_edge;
    }

    /**
     * Remove the vertex with the lowest value from the graph
     * and append it to the sorted array.
     */
    public void doSortIteration() {
        // TODO: your code here...
    	/*
    	 * sorting algorithm implementation: 
    	 * 
    	 * Init a min value, min = Integer.MAX_VALUE 
    	 * Init a dummyVertex Value 
    	 * Iterate through all the edges in the graph: 
    	 * 	- at g_edge[i] we need to compare the values of the source and target vertices of the edge
    	 * 	- whichever vertex has the smaller value is set as the dummyVertex, the value is then taken and set as min value 
    	 * Once a vertex has been identified, remove all the edges in g_edges that are associated with the dummyVertex
    	 * End the sort for right then 
    	 * */
    	if(g_verts.size() == 0 )
    		return;
    	Integer min_value = g_verts.get(0);					// take the minimum value that we need 
    	g_verts.remove(0);									// remove the vertex from the graph_verts 
    	sorted.add(min_value);
    	ArrayList<Edge> upd_edg = new ArrayList<Edge>();
    	for(Edge c_edge: g_edges)
    	{
    		// iterate through all the edges that are currently present in the graph
    		// if the source or target of a specific edge is equal to the minimum value 
    		// then we remove it from the graph 
    		if(c_edge.source.getValue() == min_value || c_edge.target.getValue() == min_value)
    		{
    			//System.out.println("Time to delete an edge..... ");
    			continue;
    		}
    		else
    			upd_edg.add(c_edge);
    	}
    	// set the current graph to the updated one 
    	g_edges = upd_edg;
    }

    /**
     * Return true if all elements are sorted and the graph contains no more vertices.
     */
    public Boolean isSorted() {
        // TODO: your code here...
    	if(sorted.size() != v_count || sorted.size() == 0 || g_verts.size() != 0)
    		return false;
    	else
    		return true;
    }
}
