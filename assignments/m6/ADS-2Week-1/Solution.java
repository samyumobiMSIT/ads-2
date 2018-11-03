
class PageRank {
	
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
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
		
		
	int T = Integer.parseInt(args[0]); // number of moves
		int M = StdIn.readInt(); // number of pages - ignore since M = N
		int N = StdIn.readInt(); // number of pages
		
		// Read transition matrix.
		double[][] p = new double[N][N]; // p[i][j] = prob. that surfer moves
											// from page i to page j
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				p[i][j] = StdIn.readDouble();
		
		int[] freq = new int[N]; // freq[i] = # times surfer hits page i
		
		// Start at page 0.
		int page = 0;
		
		for (int t = 0; t < T; t++)
		{
			
			// Make one random move.
			double r = Math.random();
			double sum = 0.0;
			for (int j = 0; j < N; j++)
			{
				// Find interval containing r.
				sum += p[page][j];
				if (r < sum)
				{
					page = j;
					break;
				}
			}
			freq[page]++;
		}
		
		// Print page ranks.
		for (int i = 0; i < N; i++)
		{
			System.out.printf("%8.5f", (double) freq[i] / T);
		}
		System.out.println();
	}
		
	}

