/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * variable vertex.
     */
    private final int ver;
    /**
     * variable edge.
     */
    private int edg;
    /**
     * bag of adjacent vertices list.
     */
    private Bag<Edge>[] adj;
    /**
     * Constructs the object.
     *
     * @param      ver1 integer vertex.
     */
    public EdgeWeightedGraph(final int ver1) {
        if (ver1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.ver = ver1;
        this.edg = 0;
        adj = (Bag<Edge>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    /**
     * return vertices.
     *
     * @return vertex count
     */
    public int vertex() {
        return ver;
    }
    /**
     * return edges.
     *
     * @return edge count
     */
    public int edge() {
        return edg;
    }
    /**
     * Adds an edge.
     *
     * @param      e Edge object
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edg++;
    }
    /**
     * returns adjacent vertex.
     *
     * @param      v integer.
     *
     * @return  adjacent vertex.
     */
    public Iterable<Edge> adj(final int v) {

        return adj[v];
    }
    /**
     * degree that gives size of adjacent list.
     *
     * @param      v vertex.
     *
     * @return degree.
     */
    public int degree(final int v) {

        return adj[v].size();
    }
    /**
     * edges that iterates and add.
     *
     * @return iterable list.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < ver; v++) {
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
}
