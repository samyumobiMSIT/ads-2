import java.util.*;
class Solution{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		percolate obj = new percolate(size);
		while(sc.hasNextLine()){
			String[] input = sc.nextLine().split(" ");
			obj.open(Integer.parseInt(input[0])-1, Integer.parseInt(input[1])-1);
		}
		System.out.println(obj.ispercolate());
	}
}
class percolate{
	int size;
	boolean[][] grid;
	Graph obj;

	percolate(int size){
		this.size = size;
		grid = new boolean[size][size];
		obj = new Graph(size*size + 2);
	}

	public void open(int i, int j){
		if(grid[i][j]) return;
		grid[i][j] = true;
		if(i == 0){
			obj.addEdge(convert(i, j), size*size);
		}if(i == size-1){
			obj.addEdge(convert(i, j), size*size+1);
		}
		if(i < size - 1 && grid[i+1][j]){
			obj.addEdge(convert(i,j), convert(i+1,j));
		}if(i > 0 && grid[i-1][j]){
			obj.addEdge(convert(i, j), convert(i-1,j));
		}if(j > 0 && grid[i][j-1]){
			obj.addEdge(convert(i, j), convert(i,j-1));
		}if(j < size-1 && grid[i][j+1]){
			obj.addEdge(convert(i, j), convert(i,j+1));
		}
	}
	public boolean ispercolate(){
		CC obj1 = new CC(obj);
		return obj1.connected(size*size, size*size+1);

		//return obj.connected(size*size, size*size+1);
	}

	public int convert(int i, int j){
		return ((i * size) + j);
	}
}