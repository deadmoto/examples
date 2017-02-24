import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    @Test
    public void test() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        assertFalse(randomizedQueue.isEmpty());
        assertEquals(3, randomizedQueue.size());

        randomizedQueue.dequeue();
        assertEquals(2, randomizedQueue.size());
    }

    @Test
    public void testDequeue() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        assertEquals(1, randomizedQueue.size());

        String sample = randomizedQueue.sample();
        assertEquals("A", sample);
        assertEquals(1, randomizedQueue.size());

        String dequeue = randomizedQueue.dequeue();
        assertEquals("A", dequeue);
        assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void testIterator() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        Iterator<String> iterator = randomizedQueue.iterator();
        ArrayList<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        Iterator<String> iterator = randomizedQueue.iterator();
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableIterator() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        Iterator<String> iterator = randomizedQueue.iterator();
        iterator.remove();
    }
}
