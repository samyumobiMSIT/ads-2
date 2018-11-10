// import java.util.Arrays;
/**
 * Class for dijkstra undirected sp.
 */
public class DijkstraUndirectedSP {
    /**
     * distTo[v] = distance  of shortest s->v path.
     */
    private double[] distTo;
    /**
     * edgeTo[v] = last edge on shortest s->v path.
     */
    private Edge[] edgeTo;
    /**
     * priority queue of vertices.
     */
    private IndexMinPQ<Double> pq;

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every
     * other vertex in the edge-weighted graph {@code G}.
     *
     * Complexity : E + V
     * @param  graph the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph graph, final int s) {
        for (Edge e : graph.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                    "edge " + e + " has negative weight");
            }
        }

        distTo = new double[graph.vertices()];
        edgeTo = new Edge[graph.vertices()];

        for (int v = 0; v < graph.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(graph.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : graph.adj(v)) {
                relax(e, v);
            }
        }

        // check optimality conditions
        assert check(graph, s);
    }

    /**
     * relax edge e and update pq if changed.
     *
     * @param      e     { parameter_description }
     * @param      v     { parameter_description }
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
     * Returns the length of a shortest path between
     * the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return the length of a shortest path
     * between the source vertex {@code s} and
     *         the vertex {@code v}; {@code Double.POSITIVE_INFINITY}
     *         if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(final int v) {
        return distTo[v];
    }

    /**
     * Returns true if there is a path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path between the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path between the source vertex {@code s}
     * and vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path between the source vertex {@code s}
     *  and vertex {@code v};
     *         {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
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


    // check optimality conditions:
    // (i) for all edges e = v-w:            distTo[w] <= distTo[v] + e.weight()
    // (ii) for all edge e = v-w on the SPT: distTo[w] == distTo[v] + e.weight()

    /**
     * check.
     *
     * @param      graph  The graph
     * @param      s      { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph graph, final int s) {

        // check that edge weights are nonnegative
        for (Edge e : graph.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < graph.vertices(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v-w satisfy distTo[w] <=
        // distTo[v] + e.weight()
        for (int v = 0; v < graph.vertices(); v++) {
            for (Edge e : graph.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e =
        // v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < graph.vertices(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) {
                return false;
            }
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println(
                    "edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }


}