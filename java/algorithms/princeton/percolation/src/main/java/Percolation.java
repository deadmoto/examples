import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF union;
    private final boolean[] grid;
    private final int gridSize;

    private int openSites = 0;

    public Percolation(int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException();
        }
        union = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        grid = new boolean[gridSize * gridSize];
        this.gridSize = gridSize;
    }

    private int translate(int row, int col) {
        if (row < 1 || row > gridSize) {
            throw new IndexOutOfBoundsException();
        }
        if (col < 1 || col > gridSize) {
            throw new IndexOutOfBoundsException();
        }
        return gridSize * row - gridSize + col;
    }

    private void openLeft(int row, int col) {
        int index = translate(row, col);
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                union.union(index, index - 1);
            }
        }
    }

    private void openRight(int row, int col) {
        int index = translate(row, col);
        if (col < gridSize) {
            if (isOpen(row, col + 1)) {
                union.union(index, index + 1);
            }
        }
    }

    private void openTop(int row, int col) {
        int index = translate(row, col);
        if (row == 1) {
            union.union(index, 0);
        } else {
            if (isOpen(row - 1, col)) {
                union.union(index, index - gridSize);
            }
        }
    }

    private void openBottom(int row, int col) {
        int index = translate(row, col);
        if (row < gridSize) {
            if (isOpen(row + 1, col)) {
                union.union(index, index + gridSize);
            }
        }
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        int index = translate(row, col);
        grid[index - 1] = true;
        openSites++;
        openBottom(row, col);
        openTop(row, col);
        openLeft(row, col);
        openRight(row, col);
    }

    public boolean isOpen(int row, int col) {
        int index = translate(row, col);
        return grid[index - 1];
    }

    public boolean isFull(int row, int col) {
        int index = translate(row, col);
        return union.connected(0, index);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        for (int i = 1; i <= gridSize; i++) {
            if (union.connected(0, gridSize * gridSize - gridSize + i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if (isFull(i, j)) {
                    //stringBuilder.append('\u21CA');
                    stringBuilder.append(union.find(translate(i, j)));
                } else if (isOpen(i, j)) {
                    stringBuilder.append('\u2193');
                } else {
                    stringBuilder.append('\u21CE');
                }
            }
            stringBuilder.append('\n');
        }
        stringBuilder.append(String.format("Open sites: %d%n", numberOfOpenSites()));
        stringBuilder.append(String.format("Percolates: %s", percolates()));
        return stringBuilder.toString();
    }
}
