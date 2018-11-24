import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

// import java.util.ArrayList;
public class MoveToFront {
    private static final int R = 256;
    public static void encode() {
        String s = BinaryStdIn.readString();
        char[] sequence = new char[R];
        for (int i = 0; i < R; i++) {
            sequence[i] = (char) i;
        }
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length; i++) {
            char in = input[i];
            int out;
            for (out = 0; out < sequence.length; out++) {
                if (sequence[out] == in) {
                    break;
                }
            }
            BinaryStdOut.write((char) out);
            System.arraycopy(sequence, 0, sequence, 1, out);
            sequence[0] = in;
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        char[] sequence = new char[R];
        for (int i = 0; i < R; i++) {
            sequence[i] = (char) i;
        }
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length; i++) {
            int in = input[i];
            char ch = sequence[in];
            BinaryStdOut.write(ch);
            System.arraycopy(sequence, 0, sequence, 1, in);
            sequence[0] = ch;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else if (args[0].equals("+")) {
            decode();
        } else {
            throw new IllegalArgumentException("Illegal command line argument");
        }
    }
}