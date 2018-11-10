/**.
 * medge weighted graph
 * Complexity is O(N)
 */
class EdgeWeightedGraph {
    /**.
     * newline variable
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**.
     * variable for vertices
     */
    private int vertices;
    /**.
     * variable for edges
     */
    private int edges;
    /**.
     * variable fo bag
     */
    private Bag<Edge>[] adj;
    /**.
     * constructor
     * Complexity is O(N)
     *
     * @param      ver   The version
     */
    EdgeWeightedGraph(final int ver) {
        this.vertices = ver;
        this.edges = 0;
        adj = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**.
     * method to return the vertices count
     * Complexity is O(1)
     *
     * @return     { int }
     */
    public int vertices() {
        return this.vertices;
    }
    /**.
     * method to return the edges count
     * Complexity is O(1)
     *
     * @return     { int }
     */
    public int noOfEdges() {
        return this.edges;
    }
    /**.
     * method to add the edge
     * Complexity is O(1)
     *
     * @param      e     { Edge }
     */
    public void addEdge(final Edge e) {
        int first = e.either();
        int sec = e.other(first);
        adj[first].add(e);
        adj[sec].add(e);
        edges++;
    }
    /**.
     * Iterable adj
     * Complexity is O(1)
     *
     * @param      v     { int }
     *
     * @return     { Iterator }
     */
    public Iterable<Edge> adj(final int v) {
        return adj[v];
    }
    /**.
     * degree method
     * Complexity is O(1)
     *
     * @param      ver   The version
     *
     * @return     { int }
     */
    public int degree(final int ver) {
        return this.adj[ver].size();
    }
    /**.
     * Iterable method for edges
     * Complexity is O(N^2)
     *
     * @return     { Iterator }
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vertices; v++) {
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
    /**.
     * method to print the objects
     * Complexity is O(N^2)
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
