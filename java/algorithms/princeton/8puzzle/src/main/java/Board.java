import java.util.ArrayList;

public class Board {

    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = deepCopy(blocks);
    }

    private static int[][] deepCopy(int[][] source) {
        final int[][] copy = new int[source.length][source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = new int[source.length];
            for (int j = 0; j < source.length; j++) {
                copy[i][j] = source[i][j];
            }
        }
        return copy;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] != i * dimension() + j + 1) {
                    hamming++;
                }
            }
        }
        return --hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int val = blocks[i][j];
                if (val == i * dimension() + j + 1) continue;
                if (val == 0) continue;
                int x = (val - 1) / dimension();
                int y = (val - 1) % dimension();
                manhattan += Math.abs(x - i);
                manhattan += Math.abs(y - j);
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int[][] twin = deepCopy(this.blocks);
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (twin[i][j] != 0) {
                    if (j == 0 && twin[i][j + 1] != 0) {
                        twin[i][j] += twin[i][j + 1];
                        twin[i][j + 1] = twin[i][j] - twin[i][j + 1];
                        twin[i][j] -= twin[i][j + 1];
                        return new Board(twin);
                    }
                    if (j > 0 && twin[i][j - 1] != 0) {
                        twin[i][j] += twin[i][j - 1];
                        twin[i][j - 1] = twin[i][j] - twin[i][j - 1];
                        twin[i][j] -= twin[i][j - 1];
                        return new Board(twin);
                    }
                    if (i == 0 && twin[i + 1][j] != 0) {
                        twin[i][j] += twin[i + 1][j];
                        twin[i + 1][j] = twin[i][j] - twin[i + 1][j];
                        twin[i][j] -= twin[i + 1][j];
                        return new Board(twin);
                    }
                    if (i > 0 && twin[i - 1][j] != 0) {
                        twin[i][j] += twin[i - 1][j];
                        twin[i - 1][j] = twin[i][j] - twin[i - 1][j];
                        twin[i][j] -= twin[i - 1][j];
                        return new Board(twin);
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] == 0) {
                    if (i > 0) {
                        Board board = new Board(deepCopy(blocks));
                        board.blocks[i - 1][j] = blocks[i][j];
                        board.blocks[i][j] = blocks[i - 1][j];
                        neighbors.add(board);
                    }
                    if (j > 0) {
                        Board board = new Board(deepCopy(blocks));
                        board.blocks[i][j - 1] = blocks[i][j];
                        board.blocks[i][j] = blocks[i][j - 1];
                        neighbors.add(board);
                    }
                    if (i < dimension() - 1) {
                        Board board = new Board(deepCopy(blocks));
                        board.blocks[i + 1][j] = blocks[i][j];
                        board.blocks[i][j] = blocks[i + 1][j];
                        neighbors.add(board);
                    }
                    if (j < dimension() - 1) {
                        Board board = new Board(deepCopy(blocks));
                        board.blocks[i][j + 1] = blocks[i][j];
                        board.blocks[i][j] = blocks[i][j + 1];
                        neighbors.add(board);
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object.getClass() == this.getClass()) {
            Board other = (Board) object;
            if (other.dimension() != dimension()) return false;
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (blocks[i][j] != other.blocks[i][j]) return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension());
        stringBuilder.append('\n');
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                stringBuilder.append(String.format("%2d", blocks[i][j]));
                if (j < dimension() - 1) {
                    stringBuilder.append(' ');
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
