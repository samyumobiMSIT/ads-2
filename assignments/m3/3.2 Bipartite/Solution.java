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
     *constructor is to initialize.
     *
     * @param      graph  The graph
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
     *the depth first search is performed.
     *time complexity is O(E)
     *E is the number of edges.
     * @param      graph   The graph
     * @param      vertex  The vertex
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
     *the method is to check whether it is bipartite or.
     *not
     * @return     True if bipartite, False otherwise.
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
     *the main to read the input.
     *
     * @param      args  The arguments
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