import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DequeTest {

    @Test
    public void testEmptyDeque() {
        Deque<String> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst("");
        assertFalse(deque.isEmpty());
        deque.addLast("");
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testDequeSize() {
        Deque<String> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst("");
        assertEquals(1, deque.size());
        deque.addLast("");
        assertEquals(2, deque.size());
    }

    @Test
    public void testNaturalItemOrder() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        assertEquals("A", deque.removeLast());
        assertEquals("B", deque.removeLast());
        assertEquals("C", deque.removeLast());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testReversedItemOrder() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        assertEquals("C", deque.removeFirst());
        assertEquals("B", deque.removeFirst());
        assertEquals("A", deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLastRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(new Integer(0), deque.removeFirst());
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(new Integer(2), deque.removeFirst());
    }
}
