import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * Returns a string representation of the object.
     *
     * @param      a     { parameter_description }
     *
     * @return     String representation of the object.
     */
    public static String toString(final String[] a) {
        String s = "[";
        String ans;
        for (int i = 0; i < a.length; i++) {
            s = s + a[i] + ", ";
        }
        int val = s.length() - 2;
        ans = s.substring(0, val);
        ans += "]";
        return ans;
    }
    /**
     * Rearranges the array of W-character strings in ascending order.
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        final int z = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character
            // compute frequency counts
            int[] count = new int[z + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < z; r++) {
                count[r + 1] += count[r];
            }
            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLine();
        }
        int w = a[0].length();
        sort(a, w);
        System.out.println(toString(a));
    }
}
