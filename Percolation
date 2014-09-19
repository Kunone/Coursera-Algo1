public class Percolation {

	public static void main(String[] args){
		double a = (double)23413/40000;
		System.out.println(a);
	}
	
	private int[][] grid;
	private WeightedQuickUnionUF uf;
	private int rootTop;
	private int rootBottom;
	private int N;

	private void UnitTop(int N) {
		for (int i = 0; i < N - 1; i++) {
			uf.union(i, i + 1);
		}
		rootTop = uf.find(0);
	}

	private void UnitBottom(int N) {
		int lastIndex = N * N - 1;
		for (int i = 0; i < N - 1; i++) {
			uf.union(lastIndex - i, lastIndex - i - 1);
		}
		rootBottom = uf.find(lastIndex);
	}

	private int XYTo1D(int i, int j) {
		return N * (i - 1) + j - 1;
	}

	private void ValidatingIndices(int i, int j) {
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("column index j out of bounds");
	}

	public Percolation(int N) {
		if (N <= 0)
			throw new IllegalArgumentException("N out of bounds");

		this.N = N;
		uf = new WeightedQuickUnionUF(N * N);
		UnitTop(N);
		UnitBottom(N);
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = 0;
			}
		}
	}

	public void open(int i, int j) {
		int indexUF = XYTo1D(i, j);

		ValidatingIndices(i, j);

		if (isOpen(i, j))
			return;

		grid[i - 1][j - 1] = 1;

		if (j > 1 && isOpen(i, j - 1)) {
			uf.union(indexUF, XYTo1D(i, j - 1));
		}
		if (j < N && isOpen(i, j + 1)) {
			uf.union(indexUF, XYTo1D(i, j + 1));
		}
		if (i > 1 && isOpen(i - 1, j)) {
			uf.union(indexUF, XYTo1D(i - 1, j));
		}
		if (i < N && isOpen(i + 1, j)) {
			uf.union(indexUF, XYTo1D(i + 1, j));
		}
	}

	public boolean isOpen(int i, int j) {
		ValidatingIndices(i, j);
		return grid[i - 1][j - 1] == 1;
	}

	public boolean isFull(int i, int j) {
		ValidatingIndices(i, j);

		if (!isOpen(i, j))
			return false;

		return uf.connected(rootTop, XYTo1D(i, j));
	}

	public boolean percolates() {
		return uf.connected(rootTop, rootBottom);
	}

}
