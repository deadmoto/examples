import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {

    private LinkedList<Board> solution = new LinkedList<>();

    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();
        MinPQ<Board> minPQ = new MinPQ<>(Comparator.comparingInt(Board::manhattan));
        Board current = initial;
        while (true) {
            solution.add(current);
            //System.out.println("Step " + solution.size());
            //System.out.println(current);
            if (current.isGoal()) break;
            for (Board board : current.neighbors()) {
                if (!solution.contains(board)) {
                    minPQ.insert(board);
                }
            }
            if (minPQ.isEmpty()) {
                solution.clear();
                break;
            } else {
                current = minPQ.delMin();
            }
        }
    }

    public boolean isSolvable() {
        return solution.size() > 0;
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<Board> solution() {
        return solution;
    }
}
