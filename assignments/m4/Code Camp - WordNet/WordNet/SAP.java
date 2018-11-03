import java.util.ArrayList;
public class SAP {
    Digraph graph;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = G;
    }

    // // length of shortest ancestral path between v and w; -1 if no such path
    // public int length(int v, int w)

    // // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // public int ancestor(int v, int w)

    // // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int[] length(ArrayList<Integer> listOne, ArrayList<Integer> listTwo) {
        int min = graph.V();
        int tempOne = 0;
        for(int i = 0; i < listOne.size(); i++) {
            for(int k = 0; k < listTwo.size(); k++) {
            BreadthFirstDirectedPaths bfsOne = new BreadthFirstDirectedPaths(graph, listOne.get(i));
            BreadthFirstDirectedPaths bfsTwo = new BreadthFirstDirectedPaths(graph, listTwo.get(k));
            for(int j = 0; j < graph.V(); j++) {
                if(bfsOne.hasPathTo(j) && bfsTwo.hasPathTo(j)) {
                    int sum = bfsOne.distTo[j] + bfsTwo.distTo[j];
                    if(sum < min) {
                        min = sum;
                        tempOne = j;
                    }
                }
            }
        }
    }
    int[] result = {min, tempOne};
    return result;
        }

    // // a common ancestor that participates in shortest ancestral path; -1 if no such path
    // public int ancestor(Iterable<Integer> v, Iterable<Integer> w)

    // // do unit testing of this class
    // public static void main(String[] args)
}