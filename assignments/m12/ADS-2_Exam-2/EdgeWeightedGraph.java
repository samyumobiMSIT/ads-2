/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     *the variable to store vertices.
     */
    private int vertices;
    /**
     *the variable to store edges.
     */
    private int edges;
    /**
     *the array of bags to store.
     *complete adjacency list.
     */
    private Bag<Edge>[] adj;
    /**
     *the constructor to initialize.
     *
     * @param      v vertices count
     */
    EdgeWeightedGraph(final int v, final int e) {
        this.vertices = v;
        this.edges = e;
        adj = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     *this method returns the vertices count.
     *
     * @return  number of vertices.
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     *this method is to build the graph.
     *
     * @param      edge  The edge
     */
    public void addEdge(final Edge edge) {
        int vertexOne = edge.either();
        int vertexTwo = edge.other(vertexOne);
        adj[vertexOne].add(edge);
        adj[vertexTwo].add(edge);
    }
    /**
     *returns the iterator to return the bag at that.
     *vertex.
     * @param      vertex  The vertex
     *time complexity is O(V)
     * @return iterator at that vertex.
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     *returns all the edges in graph.
     *time complexity is O(E)
     * @return bag of all the edges.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
 }