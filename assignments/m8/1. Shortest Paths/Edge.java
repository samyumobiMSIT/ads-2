/**.
 * Edge class
 * Complexty is O(1)
 */
class Edge implements Comparable<Edge> {
    /**.
     * variable for first vertex
     */
    private int firstVertex;
    /**.
     * variable for sedcond vertex
     */
    private int secondVertex;
    /**.
     * variable for weight
     */
    private Double weight;
    /**.
     * constructor
     *
     * @param      v     { of type int }
     * @param      w     { of type int }
     * @param      wei   The wei of type dounle
     */
    Edge(final int v, final int w, final Double wei) {
        this.firstVertex = v;
        this.secondVertex = w;
        this.weight = wei;
    }
    /**.
     * method to return the weight
     * complexity is O(1)
     *
     * @return     { Double }
     */
    public Double weight() {
        return this.weight;
    }
    /**.
     * either method to return one vertex
     * complexity is O(1)
     *
     * @return     { int }
     */
    public int either() {
        return firstVertex;
    }
    /**.
     * other method to find the other vertex
     * complexity is O(1)
     *
     * @param      v     { of type int }
     *
     * @return     { int }
     */
    public int other(final int v) {
        if (firstVertex == v) {
            return secondVertex;
        }
        return firstVertex;
    }
    /**.
     * method to compare the two Edge objects
     * complexity is O(1)
     *
     * @param      that  The that
     *
     * @return     { int }
     */
    public int compareTo(final Edge that) {
        return this.weight().compareTo(that.weight());
    }
}