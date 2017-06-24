public class FastCollinearPoints {
	public static double[] insertSort(double[] array) {
		int length = array.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] >= array[j-1]) {
					break;
				} else {
					array = swap(array, j, j-1);
				}
			}
		}
		return array;
	}
	
	public static double[] swap(double[] array, int a, int b) {
		double temp = array[b];
		array[b] = array[a];
		array[a] = temp;
		return array;
	}
	
	public FastCollinearPoints(Point[] points) {
		int numOfPoints = points.length;
		double[] slope = new double[numOfPoints];
		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfPoints; j++) {
				if (i == j) {
					continue;
				} else {
					/*System.out.println(points[i]);
					System.out.println(points[j]);*/
					slope[j] = points[i].slopeTo(points[j]);
				}
			}
			double[] sortedSlope = insertSort(slope);
			for (int k = 0; k < slope.length; k++) {
				/*System.out.println(slope[k]);
				System.out.println(sortedSlope[k]);*/
				if (k + 2 < slope.length) {
					if (sortedSlope[k] == sortedSlope[k+1] && sortedSlope[k+1] == sortedSlope[k+2]) {
						LineSegment line = new LineSegment(points[k], points[k+2]);
						line.draw();
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		p1.draw();
		Point p2 = new Point(1, 2);
		p2.draw();
		p1.drawTo(p2);
		Point p3 = new Point(2, 4);
		p3.draw();
		Point p4 = new Point(3, 6);
		p4.draw();
		Point p5 = new Point(4, 8);
		p5.draw();
		Point[] points = {p1, p2, p3, p4, p5};
		FastCollinearPoints cp = new FastCollinearPoints(points);
	}
}
