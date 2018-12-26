package lecture.week2;

import java.util.Iterator;

/**
 * Array list implementation of stack.
 * Invariant. Array is between 25% and 100% full.
 * <p>
 * Advantage: memory efficient, utilize spatial locality
 * Disadvantage: pop and push in worst case will take O(n) time due to resize
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 12-24-2018
 */
public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] s; // use array as the base data structure
    private int N;  // size of stack, also denote the next position in the array to insert

    /**
     * Constructor of LinkedStack
     */
    public ArrayStack() {
        s = (Item[]) new Object[1];  // set initial capacity to be 1
        N = 0;
    }

    private class StackIterator implements Iterator<Item> {
        private int i;

        public StackIterator() {
            i = N;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return s[--i];
        }
    }

    /**
     * Insert a new Item onto stack.
     *
     * @param item Item to be inserted
     */
    public void push(Item item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    /**
     * Pop topmost Item off the stack and return reference to it.
     *
     * @return topmost Item on the stack
     */
    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot pop from empty stack");
        }
        Item item = s[--N];
        s[N] = null;    // null the reference in order to GC
        // in case the array is one-quarter full, halve the size of array
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    /**
     * Is the stack empty?
     *
     * @return true if stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Resize the array to a larger capacity when current array is full.
     * Consequence: inserting first N items takes time proportional to N (not N^2)
     *
     * @param capacity the new capacity for the array
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        ArrayStack<String> s = new ArrayStack<>();
        System.out.println(s.isEmpty());
        s.push("hello");
        s.push("hi");
        s.push("greeting");
        for (String str : s) {
            System.out.println(str);
        }
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
