
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
 
 
public class Prims implements Runnable {
	    
		class point 
		{
			int k;
			int we;
			
			public point(int a,int c)
			{
				k=a;
				we=c;
			}
		}
	    
		class myc implements Comparator<point>
		{
			public int compare(point p1,point p2)
			{
				if(p1.we<p2.we)
					return -1;
				
				else if(p1.we>p2.we)
					return 1;
				
				else if(p1.we==p2.we)
					return 0;
				
				return 0;
			}
		}
		
		int[] vis;
		int[] key;
		ArrayList<point>[] al;
		
		void primmst(int st)
		{
			PriorityQueue<point> pq=new PriorityQueue<>(new myc());
			
			key[st]=0;
			pq.add(new point(st,0));
			
			while(!pq.isEmpty())
			{
				point p1=pq.poll();
				int x=p1.k;
				
				if(vis[x]==1)
					continue;
				
				vis[x]=1;
				
				for(int i=0;i<al[x].size();i++)
				{
					int y=al[x].get(i).k;
					int wt=al[x].get(i).we;
					
					if(vis[y]==0 && wt<key[y])
					{
						key[y]=wt;
						pq.add(new point(y,key[y]));
					}
				}
			}
		}
		public void run(){	
	
		InputReader in =  new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
			vis=new int[n+1];
			key=new int[n+1];
			al=new ArrayList[n+1];
			
			int v=0;
			Arrays.fill(key, Integer.MAX_VALUE);
			
			for(int i=1;i<=n;i++)
				al[i]=new ArrayList<point>();
				
			int m=in.nextInt();
		    
			for(int i=0;i<m;i++)
			{
				int a=in.nextInt();
				//starting node
				v=a;
				int b=in.nextInt();
				al[a].add(new point(b,0));
				al[b].add(new point(a,0));
			}
			
			int k1=in.nextInt();
			for(int i=0;i<k1;i++)
			{
				int a=in.nextInt();
				int b=in.nextInt();
				int c=in.nextInt();
				
				al[a].add(new point(b,c));
				al[b].add(new point(a,c));
			}
			
			primmst(v);
			
			long sum=0;
			for(int i=1;i<=n;i++)
				sum+=key[i];
			
			w.println(sum);
		}
		w.close();	
	}
	
	static class InputReader {
		
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
		
		public int read()
		{
			if (numChars==-1) 
				throw new InputMismatchException();
			
			if (curChar >= numChars)
			{
				curChar = 0;
				try 
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				
				if(numChars <= 0)				
					return -1;
			}
			return buf[curChar++];
		}
	 
		public String nextLine()
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
		}
		public int nextInt()
		{
			int c = read();
			
			while(isSpaceChar(c)) 
				c = read();
			
			int sgn = 1;
			
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			
			int res = 0;
			do 
			{
				if(c<'0'||c>'9') 
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c)); 
			
			return res * sgn;
		}
		
		public long nextLong() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			long res = 0;
			
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));
				return res * sgn;
		}
		
		public double nextDouble() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') 
			{
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') 
			{
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) 
				{
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
		
		public String readString() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = read();
			} 
			while (!isSpaceChar(c));
			
			return res.toString();
		}
	 
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	 
		public String next() 
		{
			return readString();
		}
		
		public interface SpaceCharFilter 
		{
			public boolean isSpaceChar(int ch);
		}
	}
    
	public static void main(String args[]) throws Exception
	{
		String caseToGo = null;
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			break;
		case "DirectedPaths":
		//Print the Graph Object.
			break;
		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
		new Thread(null, new Prims(),"Prims",1<<26).start();
			break;

		default:
			break;
		}

	

		
	}
 }   
/*public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...

		String caseToGo = null;
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}*/