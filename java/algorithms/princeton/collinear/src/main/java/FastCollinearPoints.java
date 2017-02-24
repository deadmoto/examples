import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;
import java.util.stream.Collectors;

public class FastCollinearPoints {

    private final Set<SortedSet<Point>> segments = new HashSet<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        Arrays.sort(points, 0, points.length);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length - 3; i++) {
            Arrays.sort(points, i, points.length);
            Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) break;
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[k]) != points[i].slopeTo(points[l])) break;
                        if (aligned(points[i], points[j], points[k], points[l])) {
                            SortedSet<Point> segment = null;
                            for (SortedSet<Point> s : segments) {
                                if (points[i].compareTo(s.first()) == 0 || points[i].slopeTo(s.first()) == points[i].slopeTo(s.last())) {
                                    if (points[l].compareTo(s.last()) == 0) {
                                        segment = s;
                                    }
                                    if (points[l].slopeTo(s.first()) == points[l].slopeTo(s.last())) {
                                        segment = s;
                                    }
                                }
                            }
                            if (segment == null) {
                                segment = new TreeSet<>();
                                segments.add(segment);
                            }
                            segment.add(points[i]);
                            segment.add(points[l]);
                        }
                    }
                }
            }
        }
    }

    private boolean aligned(Point p1, Point p2, Point p3, Point p4) {
        return p1.compareTo(p2) == p2.compareTo(p3) && p2.compareTo(p3) == p3.compareTo(p4);
    }

    public int numberOfSegments() {
        return segments().length;
    }

    public LineSegment[] segments() {
        return segments.stream()
                .map(it -> new LineSegment(it.first(), it.last()))
                .collect(Collectors.toList())
                .toArray(new LineSegment[]{});
    }

    public static void main(String[] args) {
        In in = new In("input9.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}