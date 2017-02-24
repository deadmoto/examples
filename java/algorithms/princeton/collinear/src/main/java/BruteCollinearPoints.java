import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                for (int k = 0; k < points.length; k++) {
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) continue;
                    for (int l = 0; l < points.length; l++) {
                        if (points[i].slopeTo(points[k]) != points[i].slopeTo(points[l])) continue;
                        if (aligned(points[i], points[j], points[k], points[l])) {
                            lineSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }
    }

    private boolean aligned(Point p1, Point p2, Point p3, Point p4) {
        return p1.compareTo(p2) < 0 && p2.compareTo(p3) < 0 && p3.compareTo(p4) < 0;
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[]{});
    }

    public static void main(String[] args) {
        In in = new In("input8.txt");
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}