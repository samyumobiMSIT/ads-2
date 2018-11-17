import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Queue class for list of items.
 *
 * @param      <Item>  The item
 */
class Queue<Item> implements Iterable<Item> {
    /**
     * { variable for beginning of queue }.
     */
    private Node<Item> first;
    /**
     * { variable for end of queue }.
     */
    private Node<Item> last;
    /**
     * { variable for number of elements on queue }.
     */
    private int n;
    /**
     * Class for node.
     * helper linked list class.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * { variable for item }.
         */
        private Item item;
        /**
         * { variable for node next }.
         */
        private Node<Item> next;
    }
    /**
     * Constructs the object.
     * Initializes an empty queue.
     */
    Queue() {
        first = null;
        last  = null;
        n = 0;
    }
    /**
     * Returns true if this queue is empty.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     * @return     { int value }
     */
    public int size() {
        return n;
    }

    /**
     * { Returns the item least recently added to this queue }.
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     * @return     { the item least recently added to this queue }
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    /**
     * { Adds the item to this queue }.
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     * @param      item  The item
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

    /**
     * { Removes and returns the item on this queue
     *   that was least recently added }.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return     { the item on this queue that was least recently added }
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

    /**
     * Returns a string representation of this queue.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * { Returns an iterator that iterates over the
     *   items in this queue in FIFO order }.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return     { an iterator that iterates over the items
     *               in this queue in FIFO order}
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    /**
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**
         * { variable for current node }.
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         *
         * @param      first1  The first
         */
        ListIterator(final Node<Item> first1) {
            current = first1;
        }
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * { function for remove }.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { function for next }.
         *
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