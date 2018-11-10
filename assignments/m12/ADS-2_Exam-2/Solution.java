import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // unused constructor
    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);

        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);

        for (int i = 0; i < edges; i++) {
            String[] inputs = scan.nextLine().split(" ");
            int v = Integer.parseInt(inputs[0]);
            int w = Integer.parseInt(inputs[1]);
            double wght = Double.parseDouble(inputs[2]);
            Edge e = new Edge(v, w, wght);
            eg.addEdge(e);
        }

        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(eg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] inputs = scan.nextLine().split(" ");
            int source = Integer.parseInt(inputs[0]);
            int dest = Integer.parseInt(inputs[1]);
            DijkstraUndirectedSP dusp = new DijkstraUndirectedSP(eg, source);
            if (dusp.hasPathTo(dest)) {
                System.out.println(dusp.distTo(dest));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via
            // is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] input = scan.nextLine().split(" ");
            int src = Integer.parseInt(input[0]);
            int via = Integer.parseInt(input[1]);
            int dst = Integer.parseInt(input[2]);
            DijkstraUndirectedSP dusp1 = new DijkstraUndirectedSP(eg, src);
            double totalSum = 0.0;
            ArrayList<Integer> path = new ArrayList<>();
            if (dusp1.hasPathTo(dst)) {
                if (dusp1.hasPathTo(via)) {
                    path.add(src);
                    totalSum += dusp1.distTo(via);
                    for (Edge e : dusp1.pathTo(via)) {
                        int temp = e.either();
                        if (!path.contains(temp)) {
                            path.add(temp);
                        }
                        if (!path.contains(e.other(temp))) {
                            path.add(e.other(temp));
                        }
                    }
                    dusp1 = new DijkstraUndirectedSP(eg, via);
                    if (dusp1.hasPathTo(dst)) {
                        totalSum += dusp1.distTo(dst);
                        for (Edge e : dusp1.pathTo(dst)) {
                            int temp = e.either();
                            if (!path.contains(temp)) {
                                path.add(temp);
                            }
                            if (!path.contains(e.other(temp))) {
                                path.add(e.other(temp));
                            }
                        }
                    }
                }
                System.out.println(totalSum);
                String out = path.toString().replaceAll(",", "");
                out = out.substring(1, out.length() - 1);
                System.out.println(out);
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}