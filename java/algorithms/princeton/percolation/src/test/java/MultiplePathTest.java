import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MultiplePathTest extends PercolationTest {

    @Test
    public void testInput3() {
        Percolation percolation = load(getClass().getResourceAsStream("input3.txt"));
        assertEquals(6, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput4() {
        Percolation percolation = load(getClass().getResourceAsStream("input4.txt"));
        assertEquals(8, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput7() {
        Percolation percolation = load(getClass().getResourceAsStream("input7.txt"));
        assertEquals(16, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}
