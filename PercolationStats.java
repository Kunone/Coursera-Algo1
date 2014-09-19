public class PercolationStats {

	private int T;
	private double[] thresholds;

	public static void main(String[] args) {

		while (!StdIn.isEmpty()) {
			int n = StdIn.readInt();
			int t = StdIn.readInt();
			PercolationStats perStats = new PercolationStats(n, t);

			StdOut.println("mean \t\t\t= " + perStats.mean());
			StdOut.println("stddev \t\t\t= " + perStats.stddev());
			StdOut.println("95% confidence interval = "
					+ perStats.confidenceLo() + "," + perStats.confidenceHi());
		}
	}

	public PercolationStats(int N, int T) {
		if (N <= 0)
			throw new IllegalArgumentException("N is illeagal");
		if (T <= 0)
			throw new IllegalArgumentException("T is illeagal");

		this.T = T;
		thresholds = new double[T];
		int openedCount = 0;
		int[] randomSite = new int[2];

		for (int i = 0; i < T; i++) {
			Percolation per = new Percolation(N);
			openedCount = 0;
			while (!per.percolates()) {
				do {
					randomSite[0] = StdRandom.uniform(1, N + 1);
					randomSite[1] = StdRandom.uniform(1, N + 1);
				} while (per.isOpen(randomSite[0], randomSite[1]));
				per.open(randomSite[0], randomSite[1]);
				openedCount++;
			}
			thresholds[i] = (double) openedCount / (N * N);
		}
	}

	public double mean() {
		return StdStats.mean(thresholds);
	}

	public double stddev() {
		if (T == 1)
			return Double.NaN;

		return StdStats.stddev(thresholds);
	}

	public double confidenceLo() {
		return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
	}

	public double confidenceHi() {
		return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
	}

}
