import java.util.Comparator;
import java.util.ArrayList;
/**
 * Class for maximum pq.
 *
 * @param      <Key>  The key
 */
public class MaxPQ<Key> {
    /**
     * stores items from 1 to n.
     */
    private Key[] pq;
    /**
     * Number of items on priority queue.
     */
    private int n;
    /**
     * Comparator variable.
     */
    private Comparator<Key> comparator;

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(final int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     */
    public Key max() {
        return pq[1];
    }

    /**
     * Helper function to double the size of the heap array.
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the new key to add to this priority queue
     */
    public void insert(final Key x) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++n] = x;
        swim(n);
    }

    /**
     * Removes and returns a largest key
     * on this priority queue.
     *
     * @return a largest key on this priority queue
     */
    public Key delMax() {
        final int four = 4;
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        // to avoid loiterig and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / four)) {
            resize(pq.length / 2);
        }
        return max;
    }

    /**
    * Swim function.
    *
    * @param      k1     { parameter_description }
    */
    private void swim(final int k1) {
        int k = k1;
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }
    /**
     * Sink function.
     *
     * @param      k1     { parameter_description }
     */
    private void sink(final int k1) {
        int k = k1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Less than function.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean less(final int i, final int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }
    /**
     * Swaps the values in the array.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     */
    private void exch(final int i, final int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /**
     * Prints the best 5.
     *
     * @param      best  The best
     */
    public void print5(final ArrayList<Key> best) {
        final int five = 5;
        for (int i = 1; i <= five; i++) {
            Key temp = delMax();
            System.out.println(temp);
            best.add(temp);
        }
        System.out.print("\n");
    }
}