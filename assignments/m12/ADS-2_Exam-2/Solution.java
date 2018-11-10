import java.util.Scanner;
/**
 * Solution class
 */
public final class Solution {
    /**
     * Solution class
     */
    private Solution() {
        //empty constructor.
    }
   
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph graph
        = new EdgeWeightedGraph(vertices, edges);
        int i = edges;
        while (i > 0) {
            String[] arr = scan.nextLine().split(" ");
            Edge ed = new Edge(Integer.parseInt(arr[0]),
                               Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            graph.addEdge(ed);
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
            System.out.println(graph);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] directedPaths = scan.nextLine().split(" ");
            int source = Integer.parseInt(directedPaths[0]);
            int dest = Integer.parseInt(directedPaths[1]);
            DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(graph, source);
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
            DijkstraUndirectedSP dsp2 = new DijkstraUndirectedSP(graph, source);
            if (dsp2.hasPathTo(dest)) {
                for (Edge e : dsp2.pathTo(via)) {
                    System.out.print(e + "   ");
                }
                DijkstraUndirectedSP dsp1 = new DijkstraUndirectedSP(graph, via);
                for (Edge e : dsp1.pathTo(dest)) {
                    System.out.print(e + "   ");
                }
                System.out.println(dsp1.distTo(dest));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }
    }
}