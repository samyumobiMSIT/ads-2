import java.util.Scanner;

/**
 * Class for graph l.
 */
class GraphL {
    /**
     * No.of Vertices.
     */
    private int vertices;
    /**
     * No.of Edges.
     */
    private int edges;
    /**
     * Scanner Object.
     */
    private Scanner sca;
    /**
     * String Array.
     */
    private String[] input;
    /**
     * Bag Array.
     */
    private Bag<Integer>[] bag;
    /**
     * COunt of the Edges.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      vert  The vertical
     */
    GraphL(final int vert) {
        vertices = vert;
        bag = new Bag[vertices];
    }

    /**
     * Constructs the object.
     *
     * @param      sc    The scanner
     */
    GraphL(final Scanner sc) {
        sca = sc;
        vertices = Integer.parseInt(sca.nextLine());
        edges = Integer.parseInt(sca.nextLine());
        input = sca.nextLine().split(",");
        bag = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            bag[i] = new Bag();
        }
        count = 0;
    }

    /**
     * Vertices Method.
     *
     * @return     { returns no.of Vertices }
     */
    public int vert() {
        return vertices;
    }

    /**
     * Edges Method.
     *
     * @return     { returns no.of Edges }
     */
    public int edge() {
        return edges;
    }

    /**
     * Adds an edge, connects two vertices.
     * In worst case the complexity is O(N).
     *
     * @param      vert1  The vertical 1
     * @param      vert2  The vertical 2
     */
    public void addEdge(final int vert1, final int vert2) {
        if (!hasEdge(vert1, vert2)) {
            count++;
        }
        bag[vert1].add(vert2);
        bag[vert2].add(vert1);
    }

    /**
     * Iterable to iterate no.of keys.
     * Complexity is O(N).
     * It iterates through the Bag.
     *
     * @param      v     { Integer Value }
     *
     * @return     { Returns Iterable Integer }
     */
    public Iterable<Integer> adj(final int v) {
        return bag[v];
    }

    /**
     * Determines if it has edge.
     * Complexity of hasEdge is O(N).
     * It iterates through bag in worst case.
     *
     * @param      v     { Vertex Index 1 }
     * @param      w     { Vertex Index 2 }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        Bag bagobj = bag[v];
        return bagobj.contains(w);
    }

    /**
     * To String Method to Print List.
     * Complexity is O(N ^ 2).
     *
     * @return     { Returns String }
     */
    public String tostring() {
        String str = vert() + " vertices, " + count + " edges" + "\n";
        if (count == 0) {
            str += "No edges";
            return str;
        }
        int i = 0;
        for (i = 0; i < vert() - 1; i++) {
            str += input[i] + ": ";
            for (int word : adj(i)) {
                str += input[word] + " ";
            }
            str += "\n";
        }
        str += input[i] + ": ";
        for (int word : adj(i)) {
            str += input[word] + " ";
        }
        return str;
    }
}

/**
 * Class for graph matrix.
 */
class GraphMat {
    /**
     * No.of Vertices.
     */
    private int vertices;
    /**
     * No.of Edges.
     */
    private int edges;
    /**
     * Scanner Object.
     */
    private Scanner sca;
    /**
     * String input array.
     */
    private String[] input;
    /**
     * Matrix 2D Array.
     */
    private int[][] matrix;
    /**
     * Count to check no.of edges.
     */
    private int count;
    /**
     * Constructs the object.
     */
    GraphMat() {
        //Not using this Constructor.
    }

    /**
     * Constructs the object.
     *
     * @param      sc    The screen
     */
    GraphMat(final Scanner sc) {
        sca = sc;
        vertices = Integer.parseInt(sca.nextLine());
        edges = Integer.parseInt(sca.nextLine());
        input = sca.nextLine().split(",");
        matrix = new int[vertices][vertices];
        count = 0;
    }

    /**
     * No.of Vertices.
     *
     * @return     { Returns int }
     */
    public int vert() {
        return vertices;
    }

    /**
     * No.of Edges.
     *
     * @return     { Returns int }
     */
    public int edge() {
        return edges;
    }

    /**
     * Adds an edge.
     * Complexity of addEdge is 1.
     * It uses hasEdge to check whether it is connected or not.
     *
     * @param      vert1  The vertical 1
     * @param      vert2  The vertical 2
     */
    public void addEdge(final int vert1, final int vert2) {
        if (!hasEdge(vert1, vert2)) {
            matrix[vert1][vert2] = 1;
            matrix[vert2][vert1] = 1;
            count++;
        }
    }

    /**
     * Determines if it has edge.
     * Complexity is O(1).
     *
     * @param      v     { Vertex 1 }
     * @param      w     { Vertex 2 }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return matrix[v][w] == 1;
    }

    /**
     * Iterable iterates the Array.
     * Complexity of Iterable is O(N).
     *
     * @param      v     { Integer value }
     *
     * @return     { Returns the Iterable of integer type }
     */
    public Iterable<Integer> adj(final int v) {
        return null;
    }

    /**
     * To String method to print the Matrix representation of graph.
     * Complexity of toString method is N ^ 2.
     * It iterates through out the 2D Array.
     *
     * @return     { Returns the String }
     */
    public String tostring() {
        String str = vert() + " vertices, " + count + " edges" + "\n";
        if (count == 0) {
            str += "No edges";
            return str;
        }
        int i;
        for (i = 0; i < vert(); i++) {
            for (int j = 0; j < vert(); j++) {
                str += matrix[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}


/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Method for Graph.
     * Complexity is O(N ^ 2).
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sca = new Scanner(System.in);
        String type = sca.nextLine();
        switch (type) {
            case "List":
                GraphL graphlist = new GraphL(sca);
                while (sca.hasNext()) {
                    String[] connection = sca.nextLine().split(" ");
                    int valuel1 = Integer.parseInt(connection[0]);
                    int valuel2 = Integer.parseInt(connection[1]);
                    if (valuel1 != valuel2) {
                        graphlist.addEdge(valuel1, valuel2);
                    }
                }
                System.out.println(graphlist.tostring());
            break;
            case "Matrix":
                GraphMat graphMatrix = new GraphMat(sca);
                while (sca.hasNext()) {
                    String[] connection = sca.nextLine().split(" ");
                    int value1 = Integer.parseInt(connection[0]);
                    int value2 = Integer.parseInt(connection[1]);
                    if (value1 != value2) {
                        graphMatrix.addEdge(value1, value2);
                    }
                }
                System.out.println(graphMatrix.tostring());
            break;
            default:
            break;
        }
    }
}