import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (items.length == size) {
            items = Arrays.copyOf(items, items.length << 1);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[--size];
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[size - 1];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int lo = 0;
            private int hi = size - 1;

            @Override
            public boolean hasNext() {
                return lo <= hi;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                if (StdRandom.bernoulli()) {
                    return items[lo++];
                } else {
                    return items[hi--];
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
