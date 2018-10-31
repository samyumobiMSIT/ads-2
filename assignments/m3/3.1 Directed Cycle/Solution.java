import java.util.Scanner;
class DirectedCycle {
    /**
     * marked boolean array.
     */
    private boolean[] marked;
    /**
     * edgeTo array.
     */
    private int[] edgeTo;
    /**
     * boolean array.
     */
    private boolean[] onStack;
    /**
     * cycle  - stack.
     */
    private Stack<Integer> cycle;
     /**
      * Constructs the object.
      *
      * @param      G     { Digraph }
      */

    public DirectedCycle(final Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(G, v);
            }
        }
    }

    /**
     * creates dfs and fills marked array and edgeto array.
     *
     * @param      G     { digraph }
     * @param      v     { source vertex }
     */
    private void dfs(final Digraph G, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj[v]) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                // assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
/**
 * class Solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * reads input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = Integer.parseInt(input.nextLine());
        int edges = Integer.parseInt(input.nextLine());
        Digraph dg = new Digraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = input.nextLine().split(" ");
            dg.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }

        DirectedCycle dc = new DirectedCycle(dg);
        if(dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }

    }
}

