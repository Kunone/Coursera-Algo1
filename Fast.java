import java.util.Arrays;

public class Fast {

	private static final String separated = " -> ";

	public static void main(String[] args) {

		prepareDraw();
		// read in the input
		String fileName = args[0];
		//String fileName = "./test/input6.txt";
		Point[] points = readAndDrawPoints(fileName);

		checkCollinear(points);

		endDraw();
	}

	private static void checkCollinear(Point[] p) {

		int N = p.length;
		double[] sl = new double[N];

		for (int i = 0; i < N - 3; i++) {
			Arrays.sort(p, i + 1, N, p[i].SLOPE_ORDER);
			Arrays.sort(p, 0, i, p[i].SLOPE_ORDER);

			for (int j = 0; j < N; j++)
				sl[j] = p[i].slopeTo(p[j]);
			int count = 1;
			int ii = 0;
			for (int k = i + 2; k < N; k++) {
				if (sl[k - 1] == sl[k]) {
					while (sl[ii] < sl[k] && ii < i)
						ii++;
					if (sl[ii] != sl[k])
						count++;
				} else {
					if (count > 2)
						segmentOut(i, k - count, count, p);
					count = 1;
				}
			}
			if (count > 2)
				segmentOut(i, N - count, count, p);
		}
	}

	private static void segmentOut(int o, int g, int count, Point[] q) {
		Point[] r = new Point[count + 1];
		r[0] = q[o];
		for (int l = 1; l < count + 1; l++)
			r[l] = q[l + g - 1];
		Arrays.sort(r);
		for (int l = 0; l < count; l++)
			StdOut.printf("%s -> ", r[l]);
		StdOut.printf("%s%n", r[count]);
		r[0].drawTo(r[count]);
		StdDraw.show(0);
	}

	private static void prepareDraw() {
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger
	}

	private static void endDraw() {
		// display to screen all at once
		StdDraw.show(0);
		// reset the pen radius
		StdDraw.setPenRadius();
	}

	private static Point[] readAndDrawPoints(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			points[i] = p;
			p.draw();
		}
		return points;
	}

}
