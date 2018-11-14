import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * main method that drives the program.
     * @param      args  The arguments
     * Time complexity for this method is O(words * w.length())
     */
    public static void main(final String[] args) {
        TST<Integer> t = new TST();
        String[] words = loadWords();
        //Your code goes here...
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                t.put(words[i].substring(j), flag);
                flag++;
            }
        }
        for (String res : t.keysWithPrefix(sc.nextLine())) {
            System.out.println(res);
        }
    }
    /**
     * Loads words.
     * @return     words of type String array.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}