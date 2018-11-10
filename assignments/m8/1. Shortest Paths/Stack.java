import java.util.Iterator;
import java.util.NoSuchElementException;
/**.
 * List of .
 * Complexity O(1)
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**.
     * Integer variable.
     */
    private int n;
    /**.
     * Node variable.
     */
    private Node first;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * Item.
         */
        private Item item;
        /**.
         * Node variable.
         * Complexity O(1)
         */
        private Node next;
    }
    /**.
     * Constructs the object.
     * Complexity O(1)
     */
    public Stack() {
        first = null;
        n = 0;
    }
    /**.
     * Determines if empty.
     * Complexity O(1)
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**.
     * size.
     * Complexity O(1)
     *
     * @return     size.
     */
    public int size() {
        return n;
    }
    /**.
     * Push.
     * Complexity O(1)
     *
     * @param      item  The item
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**.
     * Pop.
     * Complexity O(1)
     *
     * @return     item.
     */
    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    /**.
     * peek.
     * Complexity O(1)
     *
     * @return     item.
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return first.item;
    }
    /**.
     * Returns a string representation of the object.
     * Complexity O(1)
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }
    /**.
     * Iterator.
     * Complexity O(1)
     *
     * @return   list iterator.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    /**.
     * Class for list iterator.
     * Complexity O(1)
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * Node variable.
         * Complexity O(1)
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         * Complexity O(1)
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * remove.
         * Complexity O(1)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * next.
         * Complexity O(1)
         *
         * @return     item.
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}



