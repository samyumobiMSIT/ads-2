/**.
 * { item_description }
 */
import java.util.Iterator;
/**.
 * { item_description }
 */
import java.util.NoSuchElementException;
/**.
 * List of .
 *
 * @param      <Item>  The item
 */
public class Queue<Item> implements Iterable<Item> {
    /**.
     * { var_description }
     */
    private Node<Item> first;
    /**.
     * { var_description }
     */
    private Node<Item> last;
    /**.
     * { var_description }
     */
    private int n;
    /**.
     * Class for node.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**.
         * { var_description }
         */
        private Item item;
        /**.
         * { var_description }
         */
        private Node<Item> next;
    }
    /**.
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }
    /**.
     * Determines if empty.
     * time complexity is 1 in avg case
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     * time complexity is 1 in avg case
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    /**
     * Adds the item to this queue.
     * time complexity is 1 in avg case
     * @param  item the item to add
     */
    public void enqueue(final Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    /**.
     * Removes and returns the item on this queue that was least recently added.
     * time complexity is 1 in avg case
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    /**.
     * Returns a string representation of the object.
     * time complexity is O(N)
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    /**.
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**.
         * { var_description }
         */
        private Node<Item> current;
        /**.
         * Constructs the object.
         *
         * @param      fst  The first
         */
        ListIterator(final Node<Item> fst) {
            current = fst;
        }
        /**.
         * Determines if it has next.
         * time complexity is 1 in avg case
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * { function_description }
         * time complexity is 1 in avg case
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * { function_description }
         * time complexity is 1 in avg case
         * @return     { description_of_the_return_value }
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

