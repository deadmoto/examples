import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllSitesTest extends PercolationTest {

    @Test
    public void testInput5() {
        Percolation percolation = load(getClass().getResourceAsStream("input5.txt"));
        assertEquals(25, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}
