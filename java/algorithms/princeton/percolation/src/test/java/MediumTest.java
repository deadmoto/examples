import org.junit.Test;

import static org.junit.Assert.*;

public class MediumTest extends PercolationTest {

    @Test
    public void testGreeting57() {
        Percolation percolation = load(getClass().getResourceAsStream("greeting57.txt"));
        assertEquals(2522, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testHeart25() {
        Percolation percolation = load(getClass().getResourceAsStream("heart25.txt"));
        assertEquals(352, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testInput6() {
        Percolation percolation = load(getClass().getResourceAsStream("input6.txt"));
        assertEquals(18, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput8() {
        Percolation percolation = load(getClass().getResourceAsStream("input8.txt"));
        assertEquals(34, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput8No() {
        Percolation percolation = load(getClass().getResourceAsStream("input8-no.txt"));
        assertEquals(33, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testInput10No() {
        Percolation percolation = load(getClass().getResourceAsStream("input10-no.txt"));
        assertEquals(55, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }
}
