import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void doubleTest() {
        System.out.println(Double.NaN == Double.NaN);
        System.out.println(Double.valueOf(Double.NaN).equals(Double.valueOf(Double.NaN)));
    }

    private Board loadBoard(String name) {
        In in = new In(getClass().getResource(name));
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        return new Board(blocks);
    }

    @Test
    public void puzzle00() {
        Board board = loadBoard("puzzle00.txt");
        assertEquals(0, board.manhattan());
        assertEquals(2, board.neighbors().spliterator().getExactSizeIfKnown());
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(0, solver.moves());
    }

    @Test
    public void puzzle01() {
        Board board = loadBoard("puzzle01.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(1, solver.moves());
    }

    @Test
    public void puzzle02() {
        Board board = loadBoard("puzzle02.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(2, solver.moves());
    }

    @Test
    public void puzzle03() {
        Board board = loadBoard("puzzle03.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(3, solver.moves());
    }

    @Test
    public void puzzle04() {
        Board board = loadBoard("puzzle04.txt");
        assertEquals(2, board.neighbors().spliterator().getExactSizeIfKnown());
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(4, solver.moves());
    }

    @Test
    public void puzzle05() {
        Board board = loadBoard("puzzle05.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(5, solver.moves());
    }

    @Test
    public void puzzle06() {
        Board board = loadBoard("puzzle06.txt");
        assertEquals(2, board.neighbors().spliterator().getExactSizeIfKnown());
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(6, solver.moves());
    }

    @Test
    public void puzzle07() {
        Board board = loadBoard("puzzle07.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(7, solver.moves());
    }

    @Test
    public void puzzle09() {
        Board board = loadBoard("puzzle09.txt");
        assertEquals(3, board.neighbors().spliterator().getExactSizeIfKnown());
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(9, solver.moves());
    }

    @Test
    public void puzzle17() {
        Board board = loadBoard("puzzle17.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(7, solver.moves());
    }

    @Test
    public void puzzle2x2_00() {
        Board board = loadBoard("puzzle2x2-00.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(0, solver.moves());
    }

    @Test
    public void puzzle2x2_01() {
        Board board = loadBoard("puzzle2x2-01.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(1, solver.moves());
    }

    @Test
    public void puzzle2x2_02() {
        Board board = loadBoard("puzzle2x2-02.txt");
        assertEquals(2, board.manhattan());
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(2, solver.moves());
    }

    @Test
    public void puzzle2x2_03() {
        Board board = loadBoard("puzzle2x2-03.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(3, solver.moves());
    }

    @Test
    public void puzzle2x2_04() {
        Board board = loadBoard("puzzle2x2-04.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(4, solver.moves());
    }

    @Test
    public void puzzle2x2_05() {
        Board board = loadBoard("puzzle2x2-05.txt");
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(5, solver.moves());
    }

    @Test
    public void puzzle2x2_unsolvable1() {
        Board board = loadBoard("puzzle2x2-unsolvable1.txt");
        assertEquals(3, board.manhattan());
        Solver solver = new Solver(board);
        assertFalse(solver.isSolvable());
    }
}
