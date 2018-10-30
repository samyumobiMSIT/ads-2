import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 
public class Solution{
 
    static int[][] grid;
    static void run() throws IOException{
        String filename = "D:\\ads-2\\practise\\m2\\Bonus Assignment\\testcases\\input000.txt";
        String lineString = "";
        ArrayList<String> listData = new ArrayList<String>();
        BufferedReader data = new BufferedReader(new FileReader(filename));
        while((lineString = data.readLine())!= null){
            listData.add(lineString);
        }
       
        assignArray(listData.size());
       
        for(int index = 0,row_counter=0;index <=listData.size() - 1;++index,row_counter++){
            populateArray(listData.get(index),row_counter);
        }
        int result = minPath(grid,0,0,80-1,80-1);
        System.out.println(result);
 
    }
    // matrix[a][b] to matrix[c][d] 
    public static int minPath(int[][] matrix,int a,int b,int c,int d){
        int[][] D = new int[matrix.length][matrix[0].length];
        for(int i=0;i<D.length;i++)
            for(int j=0;j<D[0].length;j++)
                D[i][j] = Integer.MAX_VALUE;
        D[a][b] = matrix[a][b];
        int x=a,y=b;
        while(true){
          
            if(x < D.length -1)
                if(D[x+1][y] > 0)
                    D[x+1][y] = Math.min(matrix[x+1][y] + D[x][y], D[x+1][y]);
           
            if( y<D[0].length -1)
                if(D[x][y+1] >0)
                    D[x][y+1] = Math.min(matrix[x][y+1] + D[x][y], D[x][y+1]);
          
            if(x>0)
                if(D[x-1][y] >0)
                    D[x-1][y] = Math.min(matrix[x-1][y] + D[x][y], D[x-1][y]);
         
            if(y>0)
                if(D[x][y-1]>0)
                    D[x][y-1] = Math.min(matrix[x][y-1] + D[x][y], D[x][y-1]);
            if(x==c && y==d)
                return D[x][y];
 
       
            D[x][y] =-D[x][y];
          
            int min = Integer.MAX_VALUE;
            for(int i=0;i< D.length;i++){
                for(int j=0;j<D[0].length;j++){
                    if(D[i][j]>0 && D[i][j] < min){
                        min = D[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
        }
    }
    public static int Path_min(int[][] A){
        int size = A.length;
        int B[][] = new int[size][size];
        B[0][0] = A[0][0];
        B[0][1] = A[0][0] + A[0][1];
        B[1][0] = A[0][0] + A[1][0];
        for(int i = 1;i<size; i++){
            for(int j = 1;j<size ;j++){
                B[i][j] = A[i][j] + get4min(B[i-1][j],B[i+1][j],
                        B[i][j-1],B[i][j+1]);
            }
        }
        return B[size-1][size-1];
    }
    public static int get4min(int a,int b,int c,int d){
        int min1 = Math.min(a, b);
        int min2 = Math.min(c, d);
        return Math.min(min1, min2);
    }
    
    public static void populateArray(String str,int row){
        int counter = 0;
        String[] data = str.split(",");
        for(int index = 0;index<=data.length -1;++index){
            grid[row][counter++] = Integer.parseInt(data[index]);
        }
    }
    public static void assignArray(int no_of_row){
        grid = new int[no_of_row][no_of_row];
    }
 
    public static void main(String[] args) throws IOException{
        long t0 = System.currentTimeMillis();
        run();
        long t1 = System.currentTimeMillis();
        long t = t1 - t0;
        System.out.println("running time="+t/1000+"s"+t%1000+"ms");

    }
}