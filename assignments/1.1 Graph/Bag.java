/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>Bag</tt> class represents a bag (or multiset) of generic items. It
 * supports insertion and iterating over the items in arbitrary order. <p> The
 * <em>add</em>, <em>isEmpty</em>, and <em>size</em>  operation take constant
 * time. Iteration takes time proportional to the numberofitems. <p>
 *
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * Integer N.
     */
    private static int n;         // number of elements in bag
    /**
     * Node of Element.
     */
    private Node first;    // beginning of bag

    /**
     * Class for node.
     */
    private class Node {
        /**
         * Item.
         */
        private Item item;
        /**
         * Next.
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
     * Is the BAG empty?
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the bag.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }

   /**
     * Add the item to the bag.
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
     * Contains Method to check whether it is in Bag or not.
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final Item item) {
        for (Node x = first; x != null; x = x.next) {
            if (x.item.equals(item)) {
                return true;
            }
        }
        return false;
    }


   /**
     * Return an iterator that iterates over the items in the bag.
     *
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
         * Node creation.
         */
        private Node current = first;

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**
         * remove Method.
         */
        public void remove()      {
            throw new UnsupportedOperationException();
        }

        /**
         * Next Method.
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