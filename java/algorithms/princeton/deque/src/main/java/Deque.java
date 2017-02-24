import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node(item);
        node.next = first;
        if (first != null) {
            first.prev = node;
        }
        first = node;
        size++;
        if (size == 1) {
            last = node;
        }
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node(item);
        node.prev = last;
        if (last != null) {
            last.next = node;
        }
        last = node;
        size++;
        if (size == 1) {
            first = node;
        }
    }

    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();
        Node node = first;
        first = first.next;
        size--;
        return node.item;
    }

    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();
        Node node = last;
        last = last.prev;
        size--;
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                if (node == null) throw new NoSuchElementException();
                Item item = node.item;
                node = node.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private class Node {
        final Item item;
        Node next;
        Node prev;

        Node(Item item) {
            this.item = item;
        }
    }
}
