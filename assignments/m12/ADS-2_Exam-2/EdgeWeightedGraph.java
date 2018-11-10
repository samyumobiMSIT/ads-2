import java.util.Stack;
/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**.
     * { var_description }
     */
    private static final String NEWLINE =
        System.getProperty("line.separator");
    /**.
     * { var_description }
     */
    private final int vert;
    /**.
     * { var_description }
     */
    private int edge;
    /**.
     * { var_description }
     */
    private Bag<Edge>[] adj;

    /**.
     * Initializes an empty edge-weighted graph with
     * {@code V} vertices and 0
     * edges.
     * Complexity: O(V).
     *
     * @param      v     { parameter_description }
     * @throws     IllegalArgumentException  if {@code V < 0}
     */
    EdgeWeightedGraph(final int v) {
        if (v < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.vert = v;
        this.edge = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**.
     * Initializes a new edge-weighted graph that is a
     * deep copy of {@code G}.
     * Complexity: O(V.E.E).
     *
     * @param      graph  The graph
     */
    EdgeWeightedGraph(final EdgeWeightedGraph graph) {
        this(graph.vert());
        this.edge = graph.edge();
        for (int v = 0; v < graph.vert(); v++) {
            // reverse so that adjacency list is in
            // same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : graph.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     * Complexity: O(1).
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int vert() {
        return vert;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     * Complexity: O(1).
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int edge() {
        return edge;
    }

    // throw an IllegalArgumentException unless
    // {@code 0 <= v < V}
    /**.
     * { function_description }
     * Complexity: O(1).
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vert) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (
                    vert - 1));
        }
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     * Complexity: O(1).
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are
     *          between {@code 0} and {@code V-1}
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        edge++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     * Complexity: O(E).
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     * Complexity: O(1).
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degreedge(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph,
     *  use foreach notation:
     * {@code for (Edge e : G.edges())}.
     * Complexity: O(V.E).
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vert; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     * Complexity: O(V.E).
     *
     * @return the number of vertices <em>V</em>, followed by
     *          the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vert + " vertices " + edge + " edges" + NEWLINE);
        for (int v = 0; v < vert; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

