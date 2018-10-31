import java.util.Scanner;
/**
 *the class for bipartite.
 */
class Bipartite {
    /**
     *the marked array to denote the.
     * vertex is visited or not.
     */
    private boolean[] marked;
    /**
     *the array to store color.
     */
    private boolean[] color;
    /**
     *the variable to decide the.
     *graph is bapartite
     */
    private boolean flag = false;
    /**
     *edge to method to consider the.
     *vertices to which they are connected.
     */
    private int[] edgeTo;
    /**
     *stack for storing the path.
     */
    private Stack<Integer> stack;
    /**
     * Determines whether an undirected graph
     * is bipartite and finds either a
     * bipartition or an odd-length cycle.
     * @param  g the graph
     */
    Bipartite(final Graph graph) {
        flag = true;
        marked = new boolean[graph.vertices()];
        color = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }
    /**
     * check that algorithm computes either
     * the topological order or finds a directed cycle.
     * @param      g     g of type Digraph.
     * @param      v     v of type int.
     * Time complexity for this method is O(E) where E
     * is edges.
     */
    private void dfs(final Graph graph, final int vertex) {
        marked[vertex] = true;
        for (int each : graph.adj(vertex)) {
            if (stack != null) {
                return;
            }
            if (!marked[each]) {
                color[each] = !color[vertex];
                edgeTo[each] = vertex;
                dfs(graph, each);
            } else if (color[each] == color[vertex]) {
                flag = false;
                stack = new Stack<Integer>();
                stack.push(each);
                for (int j = vertex; j != each; j = edgeTo[j]) {
                    stack.push(j);
                }
                stack.push(each);
            }
        }
    }
    /**
     * Returns true if the graph is bipartite.
     * @return {@code true} if the graph
     * is bipartite; {@code false} otherwise
     * Time complexity for this method is O(1).
     */
    public boolean isBipartite() {
        return flag;
    }
}
/**
 *the class for solution.
 */
final class Solution {
    /**
     *an empty constructor.
     */
    private Solution() {
    }
    /**
     * main method that drives the program.
     * @param      args  The arguments
     * Time complexity for this method is O(N).
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        Graph obj = new Graph(scan);
        Bipartite bipartiteObj = new Bipartite(obj);
        if (bipartiteObj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}