import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * marked[v] = has vertex v been marked?.
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = previous vertex on path to v.
     */
    private int[] edgeTo;
    /**
     * onStack[v] = is vertex on the stack?.
     */
    private boolean[] onStack;
    /**
     * directed cycle (or null if no such cycle).
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether the digraph {@code G}
     * has a directed cycle and, if so,
     * finds such a cycle.
     * @param g the digraph
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }
    /**
     * check that algorithm computes either
     * the topological order or finds a directed cycle.
     * @param      g     g of type Digraph.
     * @param      v     v of type int.
     * Time complexity for this method is O(E) where E
     * is no of edges.
     */
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    /**
     * Does the digraph have a directed cycle?.
     * @return {@code true} if the digraph has
     * a directed cycle, {@code false} otherwise
     * Time complexity for this method is O(1).
     */
    public boolean hasCycle() {
        return cycle != null;
    }
    /**
     * Returns a directed cycle if the digraph has
     * a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable)
     * if the digraph has a directed cycle,
     *    and {@code null} otherwise
     * Time complexity for this method is O(1).
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
}
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * main method that drives the program.
     * @param      args  The arguments
     * Time complexity for this method is O(N).
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        Digraph d = new Digraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] inp = sc.nextLine().split(" ");
            d.addEdge(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]));
        }
        DirectedCycle dc = new DirectedCycle(d);
        if (dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}

