/**
 * Class for edge.
 */
public class Edge implements Comparable<Edge> {
    /**
     * vertex v source.
     */
    private final int v;
    /**
     * vertex w destination.
     */
    private final int w;
    /**
     * weight of edge.
     */
    private final double weight;
    /**
     *constructor.
     * @param      v1  integer
     * @param      w1  integer
     * @param      weight1  The weight
     */
    public Edge(final int v1, final int w1, final double weight1) {
        this.v = v1;
        this.w = w1;
        this.weight = weight1;
    }
    /**
     *weight method.
     * @return weight.
     */
    public double weight() {
        return weight;
    }
    /**
     *that vertex.
     * @return vertex.
     */
    public int either() {
        return v;
    }
    /**
     *next vertex.
     * @param      vertex  The vertex
     *
     * @return other vertex.
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        }  else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }
    /**
     * compare to method.
     *
     * @param      that  The that
     *
     * @return  integer.
     */
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
