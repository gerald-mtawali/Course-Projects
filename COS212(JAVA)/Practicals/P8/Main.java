/**
 * Name: Gerald Mtawali 
 * Student Number: 15264999
 */

public class Main {

    public static void main(String[] args) {
        // NOTE: This is an incomplete test case used for demonstration.
        // For best results, you should write your own test cases.

        Tests.startTestCase("Graph 1");
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);

        Edge e12 = new Edge(v1, v2);

        Edge[] edges = new Edge[]{e12};

        GraphSelectionSort graphSelSort = new GraphSelectionSort(edges);

        // Init
        Tests.assertEquals(false, graphSelSort.isSorted(), "isSorted() returns false if not finished sorting");
        Tests.assertEquals(true, Tests.arrayEquals(edges, graphSelSort.getEdges()), "getEdges() return original edges");

        // 1
        graphSelSort.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{}, graphSelSort.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1}, graphSelSort.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");

        // Incomplete...
        Tests. endTestCase();

        /** Expected output:
         * =========================
         * CASE: Graph 1
         * ...
         * _________________________
         * SUMMARY: PASS
	     * Passed 4/4 assertions
         */

         // TODO: Write your tests here...
        
        // Can add separate test cases here 
        // Create a Test case for a graph with the following vertices 
        Tests.startTestCase("Graph 2");
        Vertex t1_v1 = new Vertex(1);
        Vertex t1_v2 = new Vertex(2);
        Vertex t1_v3 = new Vertex(3);
        Vertex t1_v4 = new Vertex(4);
        Vertex t1_v5 = new Vertex(5);
        Edge e13 = new Edge(t1_v1, t1_v3);
        Edge e14 = new Edge(t1_v1, t1_v4);
        Edge e23 = new Edge(t1_v2, t1_v3);
        Edge e42 = new Edge(t1_v4, t1_v2);
        Edge e45 = new Edge(t1_v4, t1_v5);
        Edge e52 = new Edge(t1_v5, t1_v2);
        
        Edge [] digraph_one = new Edge[]{e13,e14,e23,e42,e45,e52};
        GraphSelectionSort select_sort1 = new GraphSelectionSort(digraph_one);
        
     // Init
        Tests.assertEquals(false, select_sort1.isSorted(), "isSorted() returns false if not finished sorting");
        Tests.assertEquals(true, Tests.arrayEquals(digraph_one, select_sort1.getEdges()), "getEdges() return original edges");

        // 1
        select_sort1.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{e23,e42,e45,e52}, select_sort1.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1}, select_sort1.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");
        //2
        select_sort1.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{e45}, select_sort1.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1,2}, select_sort1.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");
        //3
        select_sort1.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{e45}, select_sort1.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1,2,3}, select_sort1.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");
        //4
        select_sort1.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{}, select_sort1.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1,2,3,4}, select_sort1.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");
        //5
        select_sort1.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{}, select_sort1.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1,2,3,4,5}, select_sort1.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");
        Tests.assertEquals(true, select_sort1.isSorted(), "isSorted() returns false if not finished sorting");
        // Incomplete...
        Tests. endTestCase();
        
    }
}