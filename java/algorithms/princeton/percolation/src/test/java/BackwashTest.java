import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BackwashTest extends PercolationTest {

    @Test
    public void testInput10() {
        Percolation percolation = load(getClass().getResourceAsStream("input10.txt"));
        assertEquals(56, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput20() {
        Percolation percolation = load(getClass().getResourceAsStream("input20.txt"));
        assertEquals(250, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput50() {
        Percolation percolation = load(getClass().getResourceAsStream("input50.txt"));
        assertEquals(1412, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testJerry47() {
        Percolation percolation = load(getClass().getResourceAsStream("jerry47.txt"));
        assertEquals(1476, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}
