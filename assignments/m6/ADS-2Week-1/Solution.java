import java.util.Scanner;
import java.util.Arrays;
/*PageRank class
*/
class PageRank {
	Digraph digraph;
	int vertices;
	double[] PR_value;
	double[] outlinks;
    // Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
/*
    PageRank constructor
    @param   digraph g
    @param  vertices v
    Time complexity: Constant 1
*/
	PageRank(Digraph digraph, int vertices) {
		this.digraph = digraph;
		this.vertices = vertices;
		PR_value = new double[vertices];
	    outlinks = new double[vertices];
        // adj = (Bag<Integer>[]) new Bag[vertices];


	}
 /*
 initializePR updates vert value 
  Corner case method 
  Complexity :
  Worst: O(V) , Best: O(1)
  */
	public void initializePR(String[] incoming) {
        for(int i = 0; i<vertices; i++) {
        	outlinks[i] = digraph.outdegree(i);
        	PR_value[i] = 1.0/4;
        }
   
	}
  /* 
   getPR method 
   @param incoming String(vert)
   Complexity :
   Worst: O(V) , Best: O(1)

	*/
   public void getPR(String[] incoming) {
        for(int i = 0; i<999; i++) {
        	for(int j = 0; j<vertices; j++) {
        		// System.out.println(incoming[j]);
                String[] tokens = incoming[j].split(" ");
                double a = 0;
                for(int k = 1; k<tokens.length; k++) {
                	a += PR_value[Integer.parseInt(tokens[k])] / outlinks[Integer.parseInt(tokens[k])];
                }
                PR_value[j] = a;
        	}
        }
	}
/*
  toString method:
  @param incoming String(vert)
   Complexity :
   Worst: O(V) , Best: O(1)

*/
   public String toString()  {
		String s = "";

		for(int i = 0; i<vertices; i++) {
			s = s + i+" - "+PR_value[i]+"\n";
		}
		return s;
	}



}
/*
 WebSearch Method 
 */
class WebSearch {

}
/*
Solution class


*/
public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

        Scanner input  = new Scanner(System.in);
         // number of iterations
        int vertices = Integer.parseInt(input.nextLine());
        Digraph digraph = new Digraph(vertices);
         // number of pages
        String[] incoming = new String[vertices];

        for(int j = 0; j<vertices; j++) {
        	// System.out.println(vertices);
        	String a = input.nextLine();
        	incoming[j] = a;
        	String [] tokens = a.split(" ");
        	for(int i = 1; i<tokens.length; i++) {
                digraph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
        	}
        }

        System.out.println(digraph);
        // System.out.println();
        PageRank page = new PageRank(digraph, vertices);
        page.initializePR(incoming);
        page.getPR(incoming);

        System.out.println(page);


		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
        // Use the power method to compute page ranks. 
       /* double[] rank = new double[incoming]; 
        rank[0] = 1.0; 
        for (int t = 0; t < vertices; t++) {

            // Compute effect of next move on page ranks. 
            double[] newRank = new double[incoming]; 
            for (int j = 0; j < incoming; j++) {
                //  New rank of page j is dot product of old ranks and column j of p[][]. 
                for (int k = 0; k < incoming; k++) 
                   newRank[j] += rank[k]*p[k][j]; 
            } 

            // Update page ranks.
            rank = newRank;
        } 


        // print page ranks
        for (int i = 0; i < incoming; i++) {
            System.out.printf("%8.5f", rank[i]);  
        }
        System.out.println(); */
    } 
} 
        
	