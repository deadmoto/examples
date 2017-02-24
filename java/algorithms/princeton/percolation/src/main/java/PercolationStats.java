import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] doubles;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.doubles = new double[trials];
        this.trials = trials;
        for (int i = 0; i < this.trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            doubles[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(doubles);
    }

    public double stddev() {
        return StdStats.stddev(doubles);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        int n = Integer.parseInt(args[0]);
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        int trials = Integer.parseInt(args[1]);
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }
        PercolationStats percolationStats = new PercolationStats(n, trials);
        double mean = percolationStats.mean();
        double dev = percolationStats.stddev();
        double lo = percolationStats.confidenceLo();
        double hi = percolationStats.confidenceHi();
        System.out.println(String.format("mean                    = %f", mean));
        System.out.println(String.format("stddev                  = %.16f", dev));
        System.out.println(String.format("95%% confidence interval = %.16f, %.16f", lo, hi));
    }
}