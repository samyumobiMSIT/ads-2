import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Solution
{
	 public static void main(String args[]) throws NumberFormatException, IOException
	    
	 {       
		 	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 	int t=Integer.parseInt(br.readLine());
		 	while(t>0)
		 	{
		 		String b[]=br.readLine().split(" ");
		 		int a=Integer.parseInt(b[1]);
		 		while(a>0)
		 		{
		 			String op[]=br.readLine().split(" ");
		 			a--;
		 			
		 		}
		 		System.out.println(Integer.parseInt(b[0])-1);
		 		
		 		t--;
		 	}
 	}
 }
