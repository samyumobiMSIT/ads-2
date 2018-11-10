/**
 * Class for dijkstra undirected sp.
 */
public class DijkstraUndirectedSP {
    /**
     * double array.
     */
    private double[] distTo;
    /**
     * Edge array.
     */
    private Edge[] edgeTo;
    /**
     * IndexMinPQ.
     */
    private IndexMinPQ<Double> pq;
    /**
     * Computes a shortest-paths tree from the source vertex to every
     * other vertex in the edge-weighted graph.
     * Complexity O(ELogV)
     *
     * @param      g     the edge-weighted digraph
     * @param      s     the source vertex
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph g, final int s) {
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(g.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) {
                relax(e, v);
            }
        }
    }
    /**
     * relax.
     * Complexity O(E)
     *
     * @param      e     Edge object.
     * @param      v     Integer variable.
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
    /**
     * distTo.
     * Complexity O(1)
     *
     * @param      v     Integer variable.
     *
     * @return     distance.
     */
    public double distTo(final int v) {
        return distTo[v];
    }

    /**
     * Returns true if there is a path between the source vertex and
     * vertex.
     * Complexity O(1)
     *
     * @param      v     the
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * Iterable.
     * Complexity O(ELogV)
     *
     * @param      v     Integer variable.
     *
     * @return     path.
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
}