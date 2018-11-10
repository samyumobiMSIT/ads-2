import java.util.Scanner;
/**
 * Class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        int i = edges;
        while (i > 0) {
            String[] arr = scan.nextLine().split(" ");
            Edge ed = new Edge(Integer.parseInt(arr[0]),
                        Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            ewg.addEdge(ed);
            i--;
        }
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...

        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            // System.out.println(vertices + "vertices" + edges + "edges");
            System.out.println(ewg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] directedPaths = scan.nextLine().split(" ");
            int source = Integer.parseInt(directedPaths[0]);
            int dest = Integer.parseInt(directedPaths[1]);
            DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(ewg, source);
            if (dsp.hasPathTo(dest)) {
                System.out.println(dsp.distTo(dest));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where
            // path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] viaPaths = scan.nextLine().split(" ");
            source = Integer.parseInt(viaPaths[0]);
            int via = Integer.parseInt(viaPaths[1]);
            dest = Integer.parseInt(viaPaths[viaPaths.length - 1]);
            DijkstraUndirectedSP dsp2 = new DijkstraUndirectedSP(ewg, source);
            if (dsp2.hasPathTo(dest)) {
                Queue<Integer> q = new Queue<Integer>();
                for (Edge e : dsp2.pathTo(via)) {
                    String[] arr1 = e.toString().split(" ");
                    String[] arr2 = arr1[0].split("-");
                    int m = 0;
                    int n = 0;
                    for (Integer k : q) {
                        if (Integer.parseInt(arr2[1]) == k) {
                            m = 1;
                        }
                        if (Integer.parseInt(arr2[1]) == k) {
                            n = 1;
                        }
                    }
                    if (n == 0) {
                        q.enqueue(Integer.parseInt(arr2[1]));
                    }
                    if (m == 0) {
                        q.enqueue(Integer.parseInt(arr2[0]));
                    }
                }
                DijkstraUndirectedSP dsp1 = new DijkstraUndirectedSP(ewg, via);
                for (Edge e : dsp1.pathTo(dest)) {
                    String[] arr = e.toString().split(" ");
                    String[] arr3 = arr[0].split("-");
                    int p = 0;
                    int r = 0;
                    for (Integer j : q) {
                        if (Integer.parseInt(arr3[0]) == j) {
                            p = 1;
                        }
                        if (Integer.parseInt(arr3[1]) == j) {
                            r = 1;
                        }
                    }
                    if (p == 0) {
                        q.enqueue(Integer.parseInt(arr3[0]));
                    }
                    if (r == 0) {
                        q.enqueue(Integer.parseInt(arr3[1]));
                    }
                }
                System.out.println(dsp2.distTo(via) + dsp1.distTo(dest));
                while (!q.isEmpty()) {
                    System.out.print(q.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            break;
        default:
            break;
        }
    }
}

