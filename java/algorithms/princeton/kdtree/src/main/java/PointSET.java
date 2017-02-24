import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> points = new TreeSet<>();

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D point) {
        if (point == null) throw new NullPointerException();
        points.add(point);
    }

    public boolean contains(Point2D point) {
        if (point == null) throw new NullPointerException();
        return points.contains(point);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        TreeSet<Point2D> pointsInRect = new TreeSet<>();
        for (Point2D point : points) {
            if (rect.contains(point)) pointsInRect.add(point);
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) throw new NullPointerException();
        Comparator<Point2D> order = point.distanceToOrder();
        PriorityQueue<Point2D> nearestPoints = new PriorityQueue<>(order);
        nearestPoints.addAll(points);
        return nearestPoints.poll();
    }
}
