import org.junit.Test;

import static org.junit.Assert.*;

public class SmallTest extends PercolationTest {

    @Test
    public void testInput1() {
        Percolation percolation = load(getClass().getResourceAsStream("input1.txt"));
        assertEquals(1, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput1No() {
        Percolation percolation = load(getClass().getResourceAsStream("input1-no.txt"));
        assertEquals(0, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testInput2() {
        Percolation percolation = load(getClass().getResourceAsStream("input2.txt"));
        assertEquals(3, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput2No() {
        Percolation percolation = load(getClass().getResourceAsStream("input2-no.txt"));
        assertEquals(2, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }
}
