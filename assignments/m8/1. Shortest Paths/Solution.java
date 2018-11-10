import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Empty Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokens = scan.nextLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        String[] strarr = scan.nextLine().split(" ");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (int i = 0; i < strarr.length; i++) {
            hash.put(strarr[i], i);
        }
        while (m > 0) {
            String[] arr = scan.nextLine().split(" ");
            ewg.addEdge(new Edge(
                            hash.get(arr[0]), hash.get(arr[1]),
                            Double.parseDouble(arr[2])));
            m--;
        }
        int q = Integer.parseInt(scan.nextLine());
        while (q > 0) {
            String[] strarr1 = scan.nextLine().split(" ");
            int a = hash.get(strarr1[0]);
            DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(ewg, a);
            if (dsp.hasPathTo(hash.get(strarr1[1]))) {
                System.out.println((int) dsp.distTo(hash.get(strarr1[1])));
            }
            q--;
        }
    }
}

