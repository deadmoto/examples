import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x) return Double.POSITIVE_INFINITY;
        else if (that.y == this.y) return 0;
        else return (double) (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (slopeTo(p1) > slopeTo(p2)) return 1;
                else if (slopeTo(p1) < slopeTo(p2)) return -1;
                return 0;
            }
        };
    }

    @Override
    public int compareTo(Point that) {
        if (this.y > that.y) return 1;
        else if (this.y < that.y) return -1;
        else if (this.x > that.x) return 1;
        else if (this.x < that.x) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
