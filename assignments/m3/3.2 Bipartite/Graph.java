import java.util.Scanner;
/**
 * Class for graph.
 */
public class Graph {
    /**
     * {Vertex}.
     */
    private int vertices;
    /**
     * {Edges}.
     */
    private int edges;
    /**
     * {Declaring a bag array of type integer}.
     */
    private Bag<Integer>[] adj;

    /**
     * Create an empty graph with V vertices.
     * time complexity is O(E)
     * E is the edges
     * @param     scan scanner object
     */
    public Graph(final Scanner scan) {
        vertices = scan.nextInt();
        int edge = scan.nextInt();
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        for (int i = 0; i < edge; i++) {
            int vertexOne = scan.nextInt();
            int vertexTwo = scan.nextInt();
            addEdge(vertexOne, vertexTwo);
        }
    }

    /**
     * Return the number of vertices in the graph.
     * @return     vertices  {Integer}
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Return the number of edges in the graph.
     * @return    edges  {Integer}
     */
    public int edges() {
        return edges;
    }

    /**
     * Add the edge v-w to graph.
     * @param      v       {Vertex v}
     * @param       w      {Vertex w}
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Return the list of neighbors of vertex v as in Iterable.
     * @param      v    {Vertex}
     * @return     {Iterable}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
}