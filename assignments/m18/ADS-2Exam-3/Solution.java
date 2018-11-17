import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    // Don't modify this method.
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary(
                    "/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }
    // Don't modify this method.
    /**
     * to read file.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(
        final String file) {
        BinarySearchST<String, Integer>  st = new
        BinarySearchST<String, Integer>();
        // your code goes here
        String[] dict = toReadFile(file);
        for (int i = 0; i < dict.length; i++) {
            String str = dict[i].toLowerCase();
            if (st.contains(str)) {
                st.put(str, st.get(str) + 1);
            } else {
                st.put(str, 1);
            }
        }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * tst for loading words with freq.
     */
    private TST<Integer> tst;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    protected T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<>();
        for (String eachWord : st.keys()) {
            tst.put(eachWord, st.get(eachWord));
        }
    }

    //get all the prefixes that match with given prefix.
    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
    /**
     * potential words.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        ArrayList<String> arrlist = new ArrayList<>();
        for (String each : tst.keys()) {
            String[] strarr = each.split("");
            String number = "";
            for (String ch : strarr) {
                if (ch.equals("a") || ch.equals("b") || ch.equals("c")) {
                    number = number + "2";
                }
                if (ch.equals("d") || ch.equals("e") || ch.equals("f")) {
                    number = number + "3";
                }
                if (ch.equals("g") || ch.equals("h") || ch.equals("i")) {
                    number = number + "4";
                }
                if (ch.equals("j") || ch.equals("k") || ch.equals("l")) {
                    number = number + "5";
                }
                if (ch.equals("m") || ch.equals("n") || ch.equals("o")) {
                    number = number + "6";
                }
                if (ch.equals("p") || ch.equals("q") || ch.equals("r")
                        || ch.equals("s")) {
                    number = number + "7";
                }
                if (ch.equals("t") || ch.equals("u") || ch.equals("v")) {
                    number = number + "8";
                }
                if (ch.equals("w") || ch.equals("x") || ch.equals("y")
                        || ch.equals("z")) {
                    number = number + "9";
                }
            }
            if (number.equals(t9Signature)) {
                arrlist.add(each);
            }
        }
        return arrlist;
    }

    // return all possibilities(words), find top k with highest frequency.
    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
                                           final int k) {
        // your code goes here
        ArrayList<String> arr = new ArrayList<>();
        MaxPQ<Integer> max = new MaxPQ<>();
        for (String each : words) {
            max.insert(tst.get(each));
        }
        for (int i = 0; i < k; i++) {
            int freq = max.delMax();
            for (String eachOne : words) {
                if (freq == tst.get(eachOne)) {
                    arr.add(eachOne);
                }
            }
        }
        Collections.sort(arr);
        return arr;
    }

    // final output
    // Don't modify this method.
    /**
     * t9.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

