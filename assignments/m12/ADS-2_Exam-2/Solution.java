
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
 
class Set{
    
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
  
 
 
    static HashMap<Integer, Node> map = new HashMap<Integer, Node>();
 
    static class Node{
 
        int data, rank;
        Node parent;
 
        Node(int data){
            this.data = data;
        }
 
    }
 
    static class Info {
 
 
        public static int compare(Info a, Info b){
            return Integer.compare(a.k, b.k);
        }
    		
        int u,v,k;
 
        Info(int u, int v, int k){
            this.u = u;
            this.v = v;
            this.k = k;
        }
    }
 
    void makeSet(int data){
 
        Node n = new Node(data);
        n.parent = n;
        n.rank = 0;
        map.put(data,n);
    }
    void unionSet(int m, int n){
 
        Node n1 = map.get(m);
        Node n2 = map.get(n);
 
        if(n1 == n2){
            return;
        }
 
        Node p1 = findSet(m);
        Node p2 = findSet(n);
 
        if(p1.rank == p2.rank){
            p2.parent = p1;
            p1.rank++;
        }
        else if(p1.rank > p2.rank){
            p2.parent = p1;
            // p1.rank += p2.rank;
        }
        else{
            p1.parent = p2;
            // p2.rank += p2.rank;
        }
    }
 
    Node findSet(int data){
        //System.out.println("at "+map.get(data));
        return findSet(map.get(data));
    }
 
    Node findSet(Node n){
 
         if(n.parent == n)
             return n;
         //Path compression
         n.parent = findSet(n.parent);
         return n.parent;
        
    }
 
    public static void main(String[] args) throws IOException  {
 
        Set s = new Set();
        Reader sc = new Reader();
        int t = sc.nextInt();
        while(t-->0){
            int n,m;
            n = sc.nextInt();
            m = sc.nextInt();
            
            for(int i = 1 ; i <= n ; i++){
                s.makeSet(i);
            }
            for(int i = 0 ; i < m ; i ++){
                s.unionSet(sc.nextInt(), sc.nextInt());
            }
            m = sc.nextInt();
            Info in[] = new Info[m];
            for(int i = 0 ; i < m ; i++){
                in[i] = new Info(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            Arrays.sort(in, Info::compare);
            int cost = 0;
            for(Info i : in) {
            	if(s.findSet(i.u) != s.findSet(i.v)) {
            		cost += i.k;
            		s.unionSet(s.findSet(i.u).data,s.findSet(i.v).data);
            	}
            }
            System.out.println(cost);
            map.clear();
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