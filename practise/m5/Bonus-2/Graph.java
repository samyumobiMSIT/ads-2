import java.util.NoSuchElementException;
import java.util.Scanner;
public class Graph {
    /**
     * No.of Vertices.
     */
    private int vertices;
    /**
     * No.of Edges.
     */
    private int edges;
    /**
     * Scanner Object.
     */
    private Scanner sca;
    /**
     * String Array.
     */
    private String[] input;
    /**
     * Bag Array.
     */
    private Bag<Integer>[] bag;
    /**
     * COunt of the Edges.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      vert  The vertical
     */
    Graph(final int vert) {
        vertices = vert;
        bag = new Bag[vertices];
    }

    /**
     * Constructs the object.
     *
     * @param      sc    The scanner
     */
    Graph(final Scanner sc) {
        sca = sc;
        vertices = Integer.parseInt(sca.nextLine());
        edges = Integer.parseInt(sca.nextLine());
        input = sca.nextLine().split(",");
        bag = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            bag[i] = new Bag();
        }
        count = 0;
    }
    
    
   
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return vertices;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return edges;
    }

    
    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
     public void addEdge(final int vert1, final int vert2) {
        if (!hasEdge(vert1, vert2)) {
            count++;
        }
        bag[vert1].add(vert2);
        bag[vert2].add(vert1);
    }
     /**
     * Determines if it has edge.
     * Complexity of hasEdge is O(N).
     * It iterates through bag in worst case.
     *
     * @param      v     { Vertex Index 1 }
     * @param      w     { Vertex Index 2 }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        Bag bagobj = bag[v];
        return bagobj.contains(w);
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        
        return bag[v];
    }
/**
     * To String Method to Print List.
     * Complexity is O(N ^ 2).
     *
     * @return     { Returns String }
     */
    public String tostring() {
        String str = V() + " vertices, " + count + " edges" + "\n";
        if (count == 0) {
            str += "No edges";
            return str;
        }
        int i = 0;
        for (i = 0; i < V() - 1; i++) {
            str += input[i] + ": ";
            for (int word : adj(i)) {
                str += input[word] + " ";
            }
            str += "\n";
        }
        str += input[i] + ": ";
        for (int word : adj(i)) {
            str += input[word] + " ";
        }
        return str;
    }
}