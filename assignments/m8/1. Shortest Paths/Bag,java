import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * number of elements in bag.
     */
    private int n;
    /**
     * beginning of bag.
     */
    private Node first;
    /**
     * Class for node.
     */
    private class Node {
        /**
         * Item.
         */
        private Item item;
        /**
         * Node next.
         */
        private Node next;
    }
    /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        n = 0;
    }
    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }
    /**
     * adds an item.
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Iterator.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * { var_description }.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * { function_description }.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { function_description }.
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

