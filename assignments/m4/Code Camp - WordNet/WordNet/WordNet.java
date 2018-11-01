import java.util.HashMap;

public class WordNet {

    private final HashMap<Integer, String> idSynset;
    private final HashMap<String, Bag<Integer>> nounId;
    private final Digraph G;
    public boolean multiple_root = false;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In in = new In(synsets);
        idSynset = new  HashMap<Integer, String>();
        nounId =  new HashMap<String, Bag<Integer>>();

        // Synsets
        while (in.hasNextLine()) {

            String parsingLine = in.readLine();
            String[] fields = parsingLine.split(",");
            // get id
            int id = Integer.parseInt(fields[0]);
            String synsetField = fields[1];
            idSynset.put(id, synsetField);

            // parsing nouns
            String[] nouns = synsetField.split(" ");

            for (String noun : nouns) {
                if (nounId.containsKey(noun)) {
                    Bag<Integer> b = nounId.get(noun);
                    b.add(id);
                    nounId.put(noun, b);
                }
                else {
                    Bag<Integer> b = new Bag<Integer>();
                    b.add(id);
                    nounId.put(noun, b);
                }
            }
        }

        Digraph digraph = new Digraph(idSynset.size());
        G = digraph;

        in = new In(hypernyms);
        // Get the hypernynms and add edges to digraph
        while (in.hasNextLine()) {
            // parse
            String parsingLine = in.readLine();
            String[] fields = parsingLine.split(",");

            int id = Integer.parseInt(fields[0]);

            for (int i = 1; i < fields.length; i++) {
                int hypernymId = Integer.parseInt(fields[i]);
                digraph.addEdge(id, hypernymId);
            }
        }

        // Check for cycles
        DirectedCycle cycle = new DirectedCycle(this.G);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Not a valid DAG");
        }

        // Check if not rooted
        int rootCount = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!this.G.adj(i).iterator().hasNext())
                rootCount++;
        }

        if (rootCount != 1) {
            throw new IllegalArgumentException("Not a rooted DAG");
        }


        }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return nounId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        SAP sap = new SAP(G);
        //get id of vertice

        Bag<Integer> bag1 =  nounId.get(nounA);
        Bag<Integer> bag2 =  nounId.get(nounB);

        return sap.length(bag1, bag2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }

        Bag<Integer> bag1 =  nounId.get(nounA);
        Bag<Integer> bag2 =  nounId.get(nounB);
        SAP sap = new SAP(G);

        return idSynset.get(sap.ancestor(bag1, bag2));
    }

   }
