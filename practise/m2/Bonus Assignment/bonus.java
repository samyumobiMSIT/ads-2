

public final class bonus implements EulerSolution{

bonus e=new bonus();

	
	public static void main(String[] args) {
		System.out.println(new run());
	}
	
	
	private static final int TOTAL = 200;
	private static int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};
	
	public String run() {
		// Knapsack problem. ways[i][j] is the number of ways to use
		// any of the first i coin values to form an unordered sum of j.
		int[][] ways = new int[COINS.length + 1][TOTAL + 1];
		ways[0][0] = 1;
		for (int i = 0; i < COINS.length; i++) {
			for (int j = 0; j <= TOTAL; j++)
				ways[i + 1][j] = ways[i][j] + (j >= COINS[i] ? ways[i + 1][j - COINS[i]] : 0);  // Dynamic programming
		}
		return Integer.toString(ways[COINS.length][TOTAL]);
	}
	
}