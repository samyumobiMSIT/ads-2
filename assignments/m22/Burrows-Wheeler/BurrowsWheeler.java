import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.BinaryStdIn;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Map;
public class BurrowsWheeler {

    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < csa.length(); i++) {
            int index = csa.index(i);
            if (index == 0) {
                BinaryStdOut.write(s.charAt(s.length() - 1), 8);
            } else {
                BinaryStdOut.write(s.charAt(index - 1), 8);
            }
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int num = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] t = s.toCharArray();
        Map<Character, Queue<Integer>> map = new TreeMap<Character, Queue<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(t[i])) {
                map.put(t[i], new Queue<Integer>());
            }
            map.get(t[i]).enqueue(i);
        }

        
        Arrays.sort(t);
        int[] next = new int[t.length];
        for (int i = 0; i < next.length; i++) {
            next[i] = map.get(t[i]).dequeue();
        }

        for (int i = 0; i < next.length; i++) {
            BinaryStdOut.write(t[num]);
            num = next[num];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Illegal command line argument");
        } else if (args[0].equals("-")) {
            transform();
        } else if (args[0].equals("+")) {
            inverseTransform();
        } else {
            throw new IllegalArgumentException("Illegal command line argument");
        }
    }
}