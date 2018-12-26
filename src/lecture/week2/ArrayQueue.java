package lecture.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 8; // default capacity

    private Item[] items;
    private int front;
    private int rear;
    private int capacity;

    public ArrayQueue() {
        front = 0;
        rear = 0;
        capacity = 0;
        items = (Item[]) new Object[capacity];  // lazy initialization
    }

    public int size() {
        if (rear >= front) {
            return rear - front;
        } else {
            return rear + capacity - front;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        int newCapacity;
        if (capacity == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = capacity + (capacity >> 1);
        }
        Item[] newItems = (Item[]) new Object[newCapacity];
        if (rear >= front) {
            System.arraycopy(items, front, newItems, 0, size());
        } else {
            System.arraycopy(items, front, newItems, 0, capacity - front);
            System.arraycopy(items, 0, newItems, capacity - front, rear);
        }
        front = 0;
        rear = size();
        items = newItems;
        capacity = newCapacity;
    }

    public void enqueue(Item i) {
        if (size() == capacity) {
            grow();
        }
        items[rear] = i;
        rear = (rear + 1) % capacity;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item i = items[front];
        items[front] = null;
        front = (front + 1) % capacity;
//        if (size() > DEFAULT_CAPACITY && size() == capacity / 4) {

        return i;

    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
