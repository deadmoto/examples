import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;

public class KdTree {

    Node root;
    int size;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) throw new NullPointerException();
        if (root == null) {
            root = new VNode();
            root.rect = new RectHV(0, 0, 1, 1);
            root.point = point;
        } else {
            root.insert(point);
        }
        size++;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        // does the set contain point p?
        return false;
    }

    public void draw() {
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            Node node = nodes.remove();
            if (node != null) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                node.point.draw();

                if (node instanceof HNode) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.setPenRadius();
                    StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
                }

                if (node instanceof VNode) {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.setPenRadius();
                    StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                }

                nodes.add(node.left);
                nodes.add(node.right);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        // all points that are inside the rectangle
        return null;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) throw new NullPointerException();
        Node nearest = root;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root.left);
        nodes.add(root.right);
        while (nodes.size() > 0) {
            Node node = nodes.remove();
            if (node == null) continue;if (node.point.distanceSquaredTo(point) < nearest.point.distanceSquaredTo(point)) {
                nearest = node;
            }
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        return nearest.point;
    }

    private static abstract class Node {

        RectHV rect;
        Point2D point;
        Node left;
        Node right;

        public void insert(Point2D point) {
            if (compareTo(point) > 0) {
                if (left == null) {
                    left = child();
                    left.rect = left();
                    left.point = point;
                } else {
                    left.insert(point);
                }
            } else {
                if (right == null) {
                    right = child();
                    right.rect = right();
                    right.point = point;
                } else {
                    right.insert(point);
                }
            }
        }

        abstract int compareTo(Point2D point);

        abstract Node child();

        abstract RectHV left();

        abstract RectHV right();
    }

    private static class HNode extends Node {

        @Override
        int compareTo(Point2D point) {
            return Double.compare(point.y(), this.point.y());
        }

        @Override
        Node child() {
            return new VNode();
        }

        @Override
        RectHV left() {
            return new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
        }

        @Override
        RectHV right() {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
        }
    }

    private static class VNode extends Node {

        @Override
        int compareTo(Point2D that) {
            return Double.compare(this.point.x(), that.x());
        }

        @Override
        Node child() {
            return new HNode();
        }

        @Override
        RectHV left() {
            return new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax());
        }

        @Override
        RectHV right() {
            return new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        }
    }
}
