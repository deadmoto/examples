import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BruteCollinearPointsTest {

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
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(getPoints("input8.txt"));

        assertEquals(2, bruteCollinearPoints.numberOfSegments());
        Object[] strings = transform(bruteCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(10000, 0) -> (0, 10000)",
                "(3000, 4000) -> (20000, 21000)"
        }, strings);
    }

    @Test
    public void equidistant() {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(getPoints("equidistant.txt"));

        assertEquals(4, bruteCollinearPoints.numberOfSegments());
        Object[] strings = transform(bruteCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(10000, 0) -> (0, 10000)",
                "(10000, 0) -> (30000, 0)",
                "(13000, 0) -> (5000, 12000)",
                "(30000, 0) -> (0, 30000)"
        }, strings);
    }

    @Test
    public void input40() {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(getPoints("input40.txt"));

        assertEquals(4, bruteCollinearPoints.numberOfSegments());
        Object[] strings = transform(bruteCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 17000) -> (1000, 31000)",
                "(1000, 17000) -> (29000, 17000)",
                "(2000, 24000) -> (25000, 24000)",
                "(2000, 29000) -> (28000, 29000)"
        }, strings);
    }

    @Test
    public void input48() {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(getPoints("input48.txt"));

        assertEquals(6, bruteCollinearPoints.numberOfSegments());
        Object[] strings = transform(bruteCollinearPoints.segments());
        assertArrayEquals(new String[]{
                "(1000, 2000) -> (1000, 26000)",
                "(1000, 23000) -> (24000, 23000)",
                "(1000, 26000) -> (18000, 26000)",
                "(18000, 13000) -> (18000, 27000)",
                "(6000, 2000) -> (19000, 28000)",
                "(9000, 1000) -> (16000, 22000)"
        }, strings);
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
