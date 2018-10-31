/**.
 * List of graphs.
 */
class Graph {
    /**.
     * { var_description }.
     */
    private int ver;
    /**.
     * { var_description }
     */
    private int edg;
    /**.
     * { var_description }
     */
    private Bag<Integer>[] adj;
    /**.
     * { var_description }
     */
    private String[] vertexes;
    /**.
     * { var_description }
     */
    private int size = 0;
    /**
     * Initializes an empty graph with V vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  vt number of vertices
     */
    Graph(final int vt) {
        this.ver = vt;
        this.edg = 0;
        vertexes = new String[ver];
        size = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     * time complexity is 1
     * @return the number of vertices in this graph
     */
    public int vert() {
        return ver;
    }

    /**
     * Returns the number of edges in this graph.
     * time complexity is 1
     * @return the number of edges in this graph
     */
    public int edge() {
        return edg;
    }
    /**.
     * Adds a vertex.
     * time complexity is 1
     * @param      v     { parameter_description }
     */
    public void addVertex(final String v) {
        vertexes[size] = v;
        size++;
    }
    /**
     * Adds the undirected edge v-w to this graph.
     * time complexity is 1
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            System.out.println(ver + " vertices, " + edg + " edges");
            System.out.println("No edges");
            return;
        }
            edg++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * time complexity is 1
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * time complexity is 1
     */
    public int degree(final int v) {
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by
     * the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     * time complexity is N^2
     */
    public String toString() {
        String s = "";
        s += ver + " vertices, " + edg + " edges" + '\n';
        for (int v = 0; v < ver; v++) {
            s += vertexes[v] + ": ";
            for (int w : adj[v]) {
                s += vertexes[w] + " ";
            }
            s += '\n';
        }
        return s;
    }

}