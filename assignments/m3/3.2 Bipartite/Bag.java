import java.util.Iterator;
/**
 * Class for bag.
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
         * item of type Item.
         */
        private Item item;
        /**
         * next of type Node.
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
     * Add the item to the bag.
     * @param      item  The item
     * Time complexity for this method is O(1).
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Return an iterator that iterates over the items in the bag.
     * @return     Iterator.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * current of type Node.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         * @return     True if has next, False otherwise.
         * Time complexity for this method is O(1).
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * returns the next item of current item.
         * @return     Item.
         * Time complexity for this method is O(1).
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
