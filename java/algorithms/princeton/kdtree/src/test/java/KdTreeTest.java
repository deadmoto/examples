import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class KdTreeTest {

    @Test
    public void emptyTree() {
        KdTree kdTree = new KdTree();
        assertTrue(kdTree.isEmpty());
        assertEquals(0, kdTree.size());
        assertNull(kdTree.nearest(new Point2D(0.5, 0.5)));
    }

    @Test
    public void insertRandom10k() {
        KdTree kdTree = new KdTree();
        for (int i = 0; i < 100000; i++) {
            Point2D point2D = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            kdTree.insert(point2D);
            assertTrue(kdTree.contains(point2D));
            assertEquals(i + 1, kdTree.size());
        }
    }

    @Test
    public void duplicates() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.1, 0.9));
        kdTree.insert(new Point2D(0.2, 0.9));
        kdTree.insert(new Point2D(0.3, 0.9));
        kdTree.insert(new Point2D(0.4, 0.9));
        kdTree.insert(new Point2D(0.5, 0.9));
        kdTree.insert(new Point2D(0.6, 0.9));
        kdTree.insert(new Point2D(0.7, 0.9));
        kdTree.insert(new Point2D(0.8, 0.9));
        kdTree.insert(new Point2D(0.9, 0.9));
        kdTree.insert(new Point2D(0.9, 0.9));
        kdTree.insert(new Point2D(0.9, 0.8));
        kdTree.insert(new Point2D(0.9, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        kdTree.insert(new Point2D(0.9, 0.5));
        kdTree.insert(new Point2D(0.9, 0.4));
        kdTree.insert(new Point2D(0.9, 0.3));
        kdTree.insert(new Point2D(0.9, 0.2));
        kdTree.insert(new Point2D(0.9, 0.1));
        assertEquals(17, kdTree.size());
        assertTrue(kdTree.contains(new Point2D(0.9, 0.2)));
    }
}
