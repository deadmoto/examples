import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void testOne() {
        assertEquals(1, Solution.wordpattern("a", "one"));
    }

    @Test
    public void testTwo() {
        assertEquals(1, Solution.wordpattern("aa", "oneone"));
    }

    @Test
    public void testNoMatch() {
        assertEquals(0, Solution.wordpattern("abba", "redredredred"));
    }

    @Test
    public void testMatch() {
        assertEquals(1, Solution.wordpattern("abab", "redblueredblue"));
    }

    @Test
    public void testNoMatch2() {
        assertEquals(0, Solution.wordpattern("aaaa", "redbluebluered"));
    }

}
