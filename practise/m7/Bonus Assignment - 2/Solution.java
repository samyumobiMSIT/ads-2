import java.util.Arrays;


public class Solution {
    
    private double weight;
    private EdgeWeightedGraph mst;
    private Edge[] src;
    
    public Solution(EdgeWeightedGraph G) {
        mst = new EdgeWeightedGraph(G.V());
        src = new Edge[G.E()];
        int index = 0;
        for (Edge e: G.edges()) {
            src[index++] = e;
            mst.addEdge(e);
        }
        
        Arrays.sort(src);
        
        //for (int i=G.E()-1; i>-1; i--) 
           // checkComponent(src[i]);
        
        for (Edge e: mst.edges())
            weight += e.weight();
    }
    
   /* private void checkComponent(Edge e) {
        mst.delEdge(e);
        MSF msf = new MSF(mst);
        if(msf.count()>1)
            mst.addEdge(e);
    }*/
    
    public Iterable<Edge> edges() {
        return mst.edges();
    }

    public double weight() {
        return weight;
    }
    
    public static void main(String[] args) {
        Solution mst = new Solution(
                new EdgeWeightedGraph(new In("resource/4.3/tinyEWG.txt")));
        for (Edge e: mst.edges())
            System.out.println(e);
        
        System.out.println(mst.weight());
    }

}