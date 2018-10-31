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
     * returns vertices.
     * Time complexity is O(1).
     * @return  vertices.
     */
    public int vertices() {
        return vertices;
    }

     /**
     * returns edges.
     * Time complexity is O(1).
     * @return edges.
     */
    public int edges() {
        return edges;
    }

     /**
     * Adds an edge.
     * Time complexity is O(1)
     * @param      v     integer variable.
     * @param      w     integer variable.
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }

     /**
     * iterable function.
     * @param      v integer variable.
     * Time complexity is O(v)
     * @return   array.
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
}