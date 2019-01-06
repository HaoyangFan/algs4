package week2; // DON'T FORGET TO REMOVE IT FOR SUBMISSION!

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Coursera Princeton Algorithms-I Week 2 Programming Assignment: Deque
 * <p>
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack
 * and a queue that supports adding and removing items from either the front or the
 * back of the data structure.
 *
 * @param <Item> generic type of Deque
 * @author Haoyang Fan
 * @version 1.0
 * @since 12-24-2018
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first; // reference to the front node of deque
    private Node last;  // reference to the last node of deque
    private int num;    // number of nodes contained in current deque

    /**
     * Construct an empty deque.
     */
    public Deque() {
        first = null;
        last = null;
        num = 0;
    }

    /**
     * Check if deque is currently empty.
     *
     * @return true if deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return num == 0;
    }

    /**
     * Return the number of items on the deque.
     *
     * @return the number of items on the deque
     */
    public int size() {
        return num;
    }

    /**
     * Add the item to the front of deque.
     *
     * @param item item to be added to front
     * @throws IllegalArgumentException if trying to add null element
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("adding null element is not permitted");
        }
        // in case current deque is empty
        if (isEmpty()) {
            first = new Node(item, null, null);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(item, oldFirst, null);
            oldFirst.prev = first;
        }
        num++;
    }

    /**
     * Append the item to the end of deque.
     *
     * @param item item to be added to end
     * @throws IllegalArgumentException if trying to add null element
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("adding null element is not permitted");
        }
        // in case current deque is empty
        if (isEmpty()) {
            last = new Node(item, null, null);
            first = last;
        } else {
            Node oldLast = last;
            last = new Node(item, null, oldLast);
            oldLast.next = last;
        }
        num++;
    }

    /**
     * Remove and return the item from the front of deque.
     *
     * @return item item to be removed from the front
     * @throws NoSuchElementException if trying to remove from empty deque
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("fail to remove from empty Deque");
        }
        Item i = first.item;
        // in case deque becomes empty after removal
        if (--num == 0) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return i;
    }

    /**
     * Remove and return the item from the end of deque.
     *
     * @return item item to be removed from the end
     * @throws NoSuchElementException if trying to remove from empty deque
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("fail to remove from empty Deque");
        }
        Item i = last.item;
        num--;
        // in case deque becomes empty after removal
        if (num == 0) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return i;
    }

    /**
     * Return an iterator over items in order from front to end
     *
     * @return the iterator for iterating over deque
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Iterator for the Deque.
     */
    private class DequeIterator implements Iterator<Item> {
        Node curr;

        DequeIterator() {
            curr = first;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("iterator has reached the end of Deque");
            }
            Item i = curr.item;
            curr = curr.next;
            return i;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("sorry, Deque's iterator does not support removal");
        }
    }


    /**
     * Node of a doubly linked list.
     */
    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        System.out.println(deque.isEmpty()); // true
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(1);
        System.out.println(deque.removeFirst());
        deque.addFirst(2);
        System.out.println(deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);
    }
}
