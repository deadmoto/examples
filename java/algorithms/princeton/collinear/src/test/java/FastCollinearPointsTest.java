import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FastCollinearPointsTest {

    private Point[] getPoints(String name) {
        In in = new In(getClass().getResource(name));
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = point(x, y);
        }
        return points;
    }

    @Test
    public void input8() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input8.txt"));

        assertEquals(2, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(10000, 0) -> (0, 10000)",
                "(3000, 4000) -> (20000, 21000)"
        }, strings);
    }

    @Test
    public void input9() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input9.txt"));

        assertEquals(1, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 1000) -> (9000, 9000)"
        }, strings);
    }

    @Test
    public void equidistant() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("equidistant.txt"));

        assertEquals(4, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(10000, 0) -> (0, 10000)",
                "(10000, 0) -> (30000, 0)",
                "(13000, 0) -> (5000, 12000)",
                "(30000, 0) -> (0, 30000)"
        }, strings);
    }

    @Test
    public void input40() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input40.txt"));

        assertEquals(4, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 17000) -> (1000, 31000)",
                "(1000, 17000) -> (29000, 17000)",
                "(2000, 24000) -> (25000, 24000)",
                "(2000, 29000) -> (28000, 29000)"
        }, strings);
    }

    @Test
    public void input48() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input48.txt"));

        assertEquals(6, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 2000) -> (1000, 26000)",
                "(1000, 23000) -> (24000, 23000)",
                "(1000, 26000) -> (18000, 26000)",
                "(18000, 13000) -> (18000, 27000)",
                "(6000, 2000) -> (19000, 28000)",
                "(9000, 1000) -> (16000, 22000)"
        }, strings);
    }

    @Test
    public void input50() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input50.txt"));

        assertEquals(7, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 2000) -> (1000, 26000)",
                "(1000, 2000) -> (1000, 26000)",
                "(1000, 23000) -> (24000, 23000)",
                "(1000, 26000) -> (18000, 26000)",
                "(18000, 13000) -> (18000, 27000)",
                "(6000, 2000) -> (19000, 28000)",
                "(9000, 1000) -> (16000, 22000)"
        }, strings);
    }

    @Test
    public void input80() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input80.txt"));

        assertEquals(31, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 2000) -> (1000, 26000)",
                "(1000, 23000) -> (24000, 23000)",
                "(1000, 26000) -> (18000, 26000)",
                "(18000, 13000) -> (18000, 27000)",
                "(6000, 2000) -> (19000, 28000)",
                "(9000, 1000) -> (16000, 22000)"
        }, strings);
    }

    @Test
    public void input299() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("input299.txt"));

        assertEquals(6, fastCollinearPoints.numberOfSegments());
        Object[] strings = transform(fastCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1650, 2050) -> (28350, 15400)",
                "(23000, 8500) -> (30950, 8500)",
                "(2950, 200) -> (2950, 25400)",
                "(31000, 500) -> (21900, 9600)",
                "(3250, 17450) -> (17250, 17450)",
                "(7300, 10050) -> (7300, 31650)"
        }, strings);
    }

    @Test
    public void kw1260() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(getPoints("kw1260.txt"));
        assertEquals(288, fastCollinearPoints.numberOfSegments());
    }

    private Object[] transform(LineSegment[] segments) {
        List<String> list = Arrays.stream(segments)
                .map(LineSegment::toString)
                .collect(Collectors.toList());
        Object[] array = list.toArray();
        Arrays.sort(array);
        return array;
    }

    private static Point point(int x, int y) {
        return new Point(x, y);
    }
}
