public class Brute {

	private static final String separated = " -> ";

	public static void main(String[] args) {

		prepareDraw();

		String fileName = args[0];
		// String fileName = "./test/input8.txt";
		Point[] points = readAndDrawPoints(fileName);
		int n = points.length;

		int c = 4;
		for (int i = 0; i <= n - c; i++) {
			for (int j = i + 1; j <= n - c + 1; j++) {
				for (int k = j + 1; k <= n - c + 2; k++) {
					for (int l = k + 1; l <= n - c + 3; l++) {
						checkCollinear(points[i], points[j], points[k],
								points[l]);
					}
				}
			}
		}

		endDraw();
	}

	private static void checkCollinear(Point p1, Point p2, Point p3, Point p4) {
		if (p1.slopeTo(p2) == p1.slopeTo(p3)) {
			if (p1.slopeTo(p2) == p1.slopeTo(p4)) {
				StdOut.println(p1.toString() + separated + p2.toString()
						+ separated + p3.toString() + separated + p4.toString());
				p1.drawTo(p2);
				p2.drawTo(p3);
				p3.drawTo(p4);
			}
		}
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
