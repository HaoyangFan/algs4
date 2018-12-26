package lecture.week2;

import java.util.Iterator;

/**
 * Linked List implementation of queue.
 *
 * @author HaoyangFan
 * @version 1.0
 * @since 12-24-2018
 */
public class LinkedQueue<Item> implements Iterable<Item> {

    private Node first; // reference to the first node in the linked list
    private Node last;  // reference to the second node in the linked list

    public LinkedQueue() {
        first = null;
        last = null;
    }

    private class Node {
        Item item;
        Node next;

        Node(Item s, Node n) {
            item = s;
            next = n;
        }
    }

    private class ArrayIterator implements Iterator<Item> {
        private Node curr;

        ArrayIterator() {
            curr = first;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            Item i = curr.item;
            curr = curr.next;
            return i;
        }
    }

    /**
     * Enqueue a Item to the end of queue.
     *
     * @param item the Item to be enqueued
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node(item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    /**
     * Dequeue the first Item in the queue.
     *
     * @return the first Item in the queue
     */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * Check to see if current queue is empty.
     *
     * @return true if queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    public static void main(String[] args) {
        LinkedQueue<String> q = new LinkedQueue<>();
        System.out.println(q.isEmpty());
        q.enqueue("hello");
        q.enqueue("hi");
        q.enqueue("greeting");
        for (String str : q) {
            System.out.println(str);
        }
        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
