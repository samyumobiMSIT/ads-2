import java.util.Scanner;
/**
 * class to find the pagerank.
 */
class PageRank {
    /**
     * graph as g.
     */
    private Digraph gr;
    /**
     * reverse of the given graph as revG.
     */
    private Digraph revdg;
    /**
     * variable for vertices.
     */
    private int vertices;
    /**
     * array to store the pageRanks.
     */
    private Double[] pr;
    /**
     * constructor.
     *
     * @param      gph    The graphics
     */
    PageRank(final Digraph gr) {
        this.gr = gr;
        this.revdg = gr.reverse();
        this.vertices = gr.V();
        pr = new Double[vertices];
        int ver = gr.V();
        for (int i = 0; i < vertices; i++) {
            pr[i] = 1.0 / ver;
        }
        prCalculation();
    }
    /**.
     * method to calculate the page Rank
     */
    public void prCalculation() {
        for (int i = 0; i < vertices; i++) {
            if (gr.outdegree(i) == 0) {
                for (int j = 0; j < vertices; j++) {
                    if (i != j) {
                        gr.addEdge(i, j);
                    }
                }
            }
        }
        final int thousand = 1000;
        for (int k = 1; k <= thousand; k++) {
            Double[] temppr = new Double[vertices];
            for (int i = 0; i < vertices; i++) {
                Double newpr = 0.0;
                for (int ele : gr.reverse().adj(i)) {
                    newpr = newpr
                    + pr[ele] / gr.outdegree(ele);
                }
                temppr[i] = newpr;
            }
            pr = temppr;
        }
    }
    /**.
     * method to get the page rank for the given page rank
     *
     * @param      v     { vertices of type int }
     *
     * @return     The page rank.
     */
    public Double getPageRank(final int v) {
        return pr[v];
    }
    /**.
     * method to printer
     */
    public void display() {
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " - " + pr[i]);
        }
    }
}
/**.
 * class to for web search
 */
class WebSearch {
//Websearch ckass
}

/**.
 * solution class
 */
final class Solution {
    /**.
     * constructor
     */
    private Solution() {
        //Constructor
    }
    /**.
     * main method to handle the input testcases
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        /**.
         * Scanner object
         */
        Scanner s = new Scanner(System.in);
        // read the first line of the
        // input to get the number of vertices

        int no_v = Integer.parseInt(s.nextLine());
        Digraph dg = new Digraph(no_v);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < no_v; i++) {
            String[] tokens = s.nextLine().split(" ");
            for (int j = 1; j < tokens.length; j++) {
                int ve = Integer.parseInt(tokens[0]);
                int adj = Integer.parseInt(tokens[j]);
                dg.addEdge(ve, adj);
            }
        }
        System.out.println(dg.toString());
        PageRank obj = new PageRank(dg);
        // Create page rank object to
        //pass the graph object to the constructor
        obj.display();

        // print the page rank object

        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object
        // and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}
