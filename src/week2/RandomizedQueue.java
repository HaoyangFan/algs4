package week2; // DON'T FORGET TO REMOVE IT FOR SUBMISSION!

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Coursera Princeton Algorithms-I Week 2 Programming Assignment: RandomizedQueue.
 * <p>
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random from items in the data structure.
 *
 * @param <Item> generic type of RandomizedQueue
 * @author Haoyang Fan
 * @version 1.0
 * @since 12-26-2018
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;   // base data structure to store items
    private int n;  // index that represents the top of stack
    private int capacity = 1;   // set initial capacity to be 1

    /**
     * Construct an empty RandomizedQueue.
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[capacity]; //
        n = 0;
    }

    /**
     * Check if current RandomizedQueue is empty.
     *
     * @return true if RandomizedQueue is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Get current number of items in the RandomizedQueue.
     *
     * @return number of items
     */
    public int size() {
        return n;
    }

    /**
     * Add an item into the randomize queue.
     *
     * @param item the element to be added
     * @throws IllegalArgumentException if input item is null
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("sorry, the randomized queue does not support null reference");
        }
        if (n == capacity) {
            resize(capacity << 1);
        }
        items[n++] = item;
    }

    /**
     * Remove and return a random item from queue.
     *
     * @return the randomly selected item
     * @throws NoSuchElementException if current RandomizedQueue is empty
     */
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException("fail to remove from empty RandomizedQueue");
        }
        // randomly generate a value in the range [0, n-1]
        int idx = StdRandom.uniform(n);
        // swap it with the top element
        swap(idx, --n);
        Item i = items[n];
        items[n] = null;
        if (n > 0 && n == capacity / 4) {
            resize(capacity >> 1);
        }
        return i;
    }

    /**
     * Return a random item (but do not remove it).
     *
     * @return the randomly selected item
     * @throws NoSuchElementException if current RandomizedQueue is empty
     */
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException("fail to remove from empty RandomizedQueue");
        }
        // randomly generate a value in the range [0, n-1] and return item at this index
        return items[StdRandom.uniform(n)];
    }


    /**
     * Swap two items in the queue.
     *
     * @param i the index of first item
     * @param j the index of second item
     */
    private void swap(int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * Resize the array to a new size and copy all elements from old array into
     * the new array.
     *
     * @param newSize the new size of array
     */
    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < n; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
        capacity = newSize;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        int[] indices;
        int i;

        RandomizedQueueIterator() {
            indices = StdRandom.permutation(n);
            i = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return i < indices.length;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Item next() {
            if (i >= indices.length) {
                throw new NoSuchElementException("iterator has reached the end of RandomizedQueue");
            }
            return items[indices[i++]];
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("RandomizedQueue iterator currently does not support remove");
        }

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("sample:" + queue.sample());
        System.out.println("dequeue");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println(queue.size());
    }
}
