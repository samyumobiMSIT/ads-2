/*import java.util.Arrays;
import java.util.*;

public class WordNet {
        private int vertices;
        private String hypernyms;
        private Digraph digraph;
        private LinearProbingHashST<String, List<Integer>> st;
   
    WordNet(String synsets, String hypernyms) {
        vertices = readsyn(synsets);
        
        digraph = new Digraph(vertices);
        readhyn(hypernyms);
        st = new LinearProbingHashST<String, List<Integer>>();
    }
   
    public int readsyn(String file) {
        In in = new In("./Files/" + file);
        String[] s1 = null; 
        String[] s = null; 
        while (!in.isEmpty()) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
            vertices++;
        s = in.readString().split(",");
        int id = Integer.parseInt(s[0]);
        ids.add(id);
        if (s[1].length() > 1) {
        for (int i = 0; i < s[1].length(); i++) {
            s1 = s[1].split(" ");
            if (st.contains(s1[i])) {
                ids.addAll(st.get(s[i]));
                st.put(s[1], ids);
            } else {
            st.put(s1[i], ids);
            }
        } 
        }
    }
    return vertices;
}
    
    public void readhyn(String file)  {
        In in = new In("./Files/" + file);
        while(!in.isEmpty()) {
            String[] s = in.readString().split(",");
            for (int i = 1; i < s.length; i++) {
                digraph.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[i]));
            }
        }
        DirectedCycle dir = new DirectedCycle(digraph);
        int count = 0;
        for (int i = 0; i < vertices; i++) {
            if (digraph.outdegree(i) == 0) {
                count++;
            }
        }
        if (count > 1) {
            throw new IllegalArgumentException("Multiple roots");
        }
        if(dir.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
        System.out.println(digraph);
        }

    }
   
    public Iterable<String> nouns() {
        return st.keys();
    }
    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(String word) {
        return st.contains(word);
    }
    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {

    // }
    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB) {

    // }

    // do unit testing of this class
    // public static void main(String[] args) {

    // }
}*/