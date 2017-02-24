import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by deadm_000 on 1/27/2017.
 */
public class PercolationTest {
    protected Percolation load(InputStream resourceAsStream) {
        Scanner scanner = new Scanner(resourceAsStream);
        int gridSize = scanner.nextInt();
        Percolation percolation = new Percolation(gridSize);
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            percolation.open(row, col);
        }
        return percolation;
    }
}
