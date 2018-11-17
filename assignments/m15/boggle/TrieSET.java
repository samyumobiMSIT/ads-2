/**.
 * { item_description }
 */
import java.util.Iterator;
/**.
 * Class for trie set.
 */
public class TrieSET implements Iterable<String> {
    /**.
     * { var_description }
     */
    private static final int R = 256;
    /**.
     * { var_description }
     */
    private Node root;
    /**.
     * { var_description }
     */
    private int n;
    /**.
     * Class for node.
     */
    private static class Node {
        /**.
         * { var_description }
         */
        private Node[] next = new Node[R];
        /**.
         * { var_description }
         */
        private boolean isString;
    }
    /**.
     * Constructs the object.
     */
    public TrieSET() {
    }
    /**.
     * { function_description }
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to contains() is null");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return false;
        }
        return x.isString;
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node get(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }
    /**.
     * { function_description }
     *
     * @param      key   The key
     */
    public void add(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to add() is null");
        }
        root = add(root, key, 0);
    }
    /**.
     * { function_description }
     *
     * @param      y     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node add(final Node y, final String key, final int d) {
        Node x = y;
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (!x.isString) {
                n++;
            }
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }
    /**.
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<String> iterator() {
        return keysWithPrefix("").iterator();
    }
    /**.
     * { function_description }
     *
     * @param      prefix  The prefix
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }
    /**.
     * { function_description }
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
                                    final Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.isString) {
            results.enqueue(prefix.toString());
        }
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**.
     * { function_description }
     *
     * @param      pattern  The pattern
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> results = new Queue<String>();
        StringBuilder prefix = new StringBuilder();
        collect(root, prefix, pattern, results);
        return results;
    }
    /**.
     * { function_description }
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      pattern  The pattern
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
                 final String pattern, final Queue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.isString) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**.
     * { function_description }
     *
     * @param      query  The query
     *
     * @return     { description_of_the_return_value }
     */
    public String longestPrefixOf(final String query) {
        if (query == null) {
            throw new IllegalArgumentException(
                "argument to longestPrefixOf() is null");
        }
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        }
        return query.substring(0, length);
    }
    /**.
     * { function_description }
     *
     * @param      x       { parameter_description }
     * @param      query   The query
     * @param      d       { parameter_description }
     * @param      len  The length
     *
     * @return     { description_of_the_return_value }
     */
    private int longestPrefixOf(final Node x, final String query,
                                final int d, final int len) {
        int length = len;
        if (x == null) {
            return length;
        }
        if (x.isString) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }
    /**.
     * { function_description }
     *
     * @param      key   The key
     */
    public void delete(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to delete() is null");
        }
        root = delete(root, key, 0);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node delete(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.isString) {
                n--;
            }
            x.isString = false;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.isString) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
    /**.
     * Determines if it has prefix.
     *
     * @param      query  The query
     *
     * @return     True if has prefix, False otherwise.
     */
    public boolean hasPrefix(final String query) {
        Node x = get(root, query, 0);
        return x != null;
    }
}
