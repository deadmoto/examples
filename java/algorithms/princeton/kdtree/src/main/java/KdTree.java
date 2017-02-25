import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.TreeSet;

public class KdTree {

    private Node root;
    private int size;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new VNode();
            root.rect = new RectHV(0, 0, 1, 1);
            root.point = point;
            size++;
        } else {
            if (root.insert(point)) {
                size++;
            }
        }
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            Node node = nodes.remove();
            if (node == null) {
                continue;
            }
            if (node.rect.contains(point)) {
                if (node.point.compareTo(point) == 0) {
                    return true;
                } else {
                    nodes.add(node.left);
                    nodes.add(node.right);
                }
            }
        }
        return false;
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        TreeSet<Point2D> range = new TreeSet<>();
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            Node node = nodes.remove();
            if (node != null) {
                if (rect.contains(node.point)) {
                    range.add(node.point);
                }
                if (node.rect.intersects(rect)) {
                    nodes.add(node.left);
                    nodes.add(node.right);
                }
            }
        }
        return range;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            return null;
        }
        Node nearest = root;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root.left);
        nodes.add(root.right);
        while (nodes.size() > 0) {
            Node node = nodes.remove();
            if (node == null) {
                continue;
            }
            if (node.point.distanceSquaredTo(point) < nearest.point.distanceSquaredTo(point)) {
                nearest = node;
            }
            if (node.rect.distanceSquaredTo(point) < nearest.point.distanceSquaredTo(point)) {
                nodes.add(node.left);
                nodes.add(node.right);
            }
        }
        return nearest.point;
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

                if (node.getClass().equals(HNode.class)) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.setPenRadius();
                    StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
                }

                if (node.getClass().equals(VNode.class)) {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.setPenRadius();
                    StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                }

                nodes.add(node.left);
                nodes.add(node.right);
            }
        }
    }

    private abstract static class Node {

        RectHV rect;
        Point2D point;
        Node left;
        Node right;

        public boolean insert(Point2D point) {
            int cmp = compareTo(point);
            if (cmp > 0) {
                if (left == null) {
                    left = child();
                    left.rect = left();
                    left.point = point;
                    return true;
                } else {
                    return left.insert(point);
                }
            } else if (cmp < 0) {
                if (right == null) {
                    right = child();
                    right.rect = right();
                    right.point = point;
                    return true;
                } else {
                    return right.insert(point);
                }
            }
            return false;
        }

        abstract int compareTo(Point2D point);

        abstract Node child();

        abstract RectHV left();

        abstract RectHV right();
    }

    private static class HNode extends Node {

        @Override
        int compareTo(Point2D that) {
            int compareY = Double.compare(that.y(), point.y());
            if (compareY == 0) {
                int compareX = Double.compare(that.x(), point.x());
                if (compareX != 0) {
                    return -1;
                }
            }
            return compareY;
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
            int compareX = Double.compare(point.x(), that.x());
            if (compareX == 0) {
                int compareY = Double.compare(point.y(), that.y());
                if (compareY != 0) {
                    return -1;
                }
            }
            return compareX;
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
