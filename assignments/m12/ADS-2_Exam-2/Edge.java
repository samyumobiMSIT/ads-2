/**.
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**.
     * { var_description }
     */
    private final int v;
    /**.
     * { var_description }
     */
    private final int w;
    /**.
     * { var_description }
     */
    private final double weight;
    /**.
     * Initializes an edge between vertices {@code v} and {@code w} of the
     * given {@code weight}.
     * Complexity: O(1).
     *
     * @param      vert       The vertical
     * @param      otherVert  The other vertical
     * @param      wt         the other vertex
     * @throws     IllegalArgumentException  if negative integer
     * @throws     IllegalArgumentException  if {@code weight} is {@code NaN}
     */
    Edge(final int vert, final int otherVert, final double wt) {
        if (vert < 0) {
            throw new IllegalArgumentException(
                "vertex index must be a nonnegative integer");
        }
        if (otherVert < 0) {
            throw new IllegalArgumentException(
                "vertex index must be a nonnegative integer");
        }
        if (Double.isNaN(wt)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = vert;
        this.w = otherVert;
        this.weight = wt;
    }

    /**.
     * Returns the weight of this edge.
     * Complexity: O(1).
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**.
     * Returns either endpoint of this edge.
     * Complexity: O(1).
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**.
     * Returns the endpoint of this edge that is
     * different from the given vertex.
     * Complexity: O(1).
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(final int vertex) {
        if      (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    /**.
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation
     *                      inherited from {@code Object}.
     * Complexity: O(1).
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer
     *         depending on whether
     *         the weight of this is less than, equal to,
     *         or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**.
     * Returns a string representation of this edge.
     * Complexity: O(1).
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public String v1() {
        return v + " " + w;
    }
}
