package lecture.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Linked list implementation of stack
 * <p>
 * Advantage: easy-to-implement, time efficient for every operation (worst case O(1))
 * Disadvantage: memory-inefficient, extra overhead for holding reference (links to other nodes)
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 12-24-2018
 */
public class LinkedStack<Item> implements Iterable<Item> {
    private Node first; // pointer to the first node of linked list

    private class Node {
        Item item;   // Item stored at this node
        Node next;  // reference to the next node

        /**
         * Constructor for the inner class Node
         *
         * @param s Item contained in this node
         * @param n reference to the next node in the list
         */
        Node(Item s, Node n) {
            item = s;
            next = n;
        }
    }

    private class StackIterator implements Iterator<Item> {
        private Node curr;

        StackIterator() {
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
     * Constructor of LinkedStack
     */
    public LinkedStack() {
        first = null;
    }

    /**
     * Insert a new Item onto stack.
     *
     * @param item Item to be inserted
     */
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node(item, oldFirst);
    }

    /**
     * Pop topmost Item off the stack and return reference to it.
     *
     * @return topmost Item on the stack
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    /**
     * Is the stack empty?
     *
     * @return true if stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.pop());
            } else {
                stack.push(s);
            }
        }
    }
}
