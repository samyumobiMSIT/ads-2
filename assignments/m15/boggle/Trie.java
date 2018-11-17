/**.
Trie the r way trie class for dictionary
@param <Value> the generic type
*/
class Trie<Value> {
/**.
R the alphabet size for the r way trie
*/
    private static final int R = 26;
/**.
root the root  ode of the trie data structure
*/
    private Node root;
/**.
triesize the size of the trie
*/
    private int triesize;
/**.
Node the node class for the trie ds
*/
    private static class Node {
/**.
val the value of the key in the node
*/
        private Object val;
/**.
next the array pointing
to the incoming character in trie
*/
        private Node[] next = new Node[R];
    }

   /**
     * Initializes an empty string symbol table.
     */
    Trie() {
    }


    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated
     * with the given key if the key is in the symbol table
     *     and <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(final String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    /**
     * Complexity is O(1) for boolean return
     * Does this symbol table contain the given key?
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(final String key) {
        return get(key) != null;
    }
/**.
@param y the node
@return the return for value
@param key the string to be out into the node
@param d the positon of the string
Complexity O(m) m is the ;ength of the string
*/
    private Node get(final Node y, final String key, final int d) {
        Node x = y;
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = (char) (key.charAt(d) - 'A');
        return get(x.next[c], key, d + 1);
    }

    /**
     * Inserts the key-value pair into
     * the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>,
     * this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(final String key, final Value val) {
       root = put(root, key, val, 0);
    }
/**.
@param y the node
@param key , string to be put in the node
@param val the value of the string
@param d the position of the string
@return the return for node after putting
Complexity is O(m) : m is length of string
*/
    private Node put(final Node y,
        final String key,
        final Value val,
        final int d) {
        Node x = y;
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                triesize++;
            }
            x.val = val;
            return x;
        }
        char c = (char) (key.charAt(d) - 'A');
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * Complexity O(1) : returns the size
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return triesize;
    }

    /**
     * Complexity : O(1) checks the size of the node
     * Is this symbol table empty?
     * @return <tt>true</tt>
     if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
/**.
hasKey() the fuction to validate the presence of the key
@param prefix the prefix of the key
@return returns the boolean
Complexity : O(1) checks the condition and
returns the truw bool value
*/
    public boolean hasKey(final String prefix) {
        Node x = get(root, prefix, 0);
        return !(x == null);
    }
}