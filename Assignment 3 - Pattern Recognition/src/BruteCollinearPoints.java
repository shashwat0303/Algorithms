import java.util.HashMap;

public class BruteCollinearPoints {
	public BruteCollinearPoints(Point[] points) {
		int numOfPoints = points.length;
		double[] slopes = new double[3];
		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfPoints; j++) {
				for (int k = 0; k < numOfPoints; k++) {
					for (int l = 0; l < numOfPoints; l++) {
						if (i == j || i == k || i == l || j == k || j == l || k == l) {
							continue;
						} else {
							slopes[0] = points[i].slopeTo(points[j]);
							slopes[1] = points[i].slopeTo(points[k]);
							slopes[2] = points[i].slopeTo(points[l]);
							if (slopes[0] == slopes[1] && slopes[1] == slopes[2]) {
								LineSegment line = new LineSegment(points[i], points[j]);
								line.draw();
							}
						}
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 1);
		Point p5 = new Point(3, 3);
		Point[] points = {p1, p2, p3, p4, p5};
		BruteCollinearPoints cp = new BruteCollinearPoints(points);
	}
}
