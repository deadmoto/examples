import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LongPathTest extends PercolationTest {

    @Test
    public void testSnake13() {
        Percolation percolation = load(getClass().getResourceAsStream("snake13.txt"));
        assertEquals(6, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testSnake101() {
        Percolation percolation = load(getClass().getResourceAsStream("snake101.txt"));
        assertEquals(6, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}
