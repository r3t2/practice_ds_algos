import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>
{
	private int x;
	private int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point that)
	{
		if(this.y < that.y) return -1;
		if(this.y > that.y) return +1;
		if(this.x < that.x) return -1;
		if(this.x > that.x) return +1;
		return 0;
	}

	public double	slopeTo(Point that)
	{
		if(this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
		if(this.y == that.y) return Double.POSITIVE_INFINITY;
		if(this.x == that.x) return (1.0 - 1.0)/1.0; // Positive zero;

		return (that.y - this.y)/(1.0*(that.x - this.x));
	}

	public Comparator<Point> slopeOrder()
	{
		return new SlopeComparator();
	}

	public void draw()
	{
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

	private class SlopeComparator implements Comparator<Point>
	{
		public int compare(Point p1, Point p2)
		{
			double m1 = slopeTo(p1);
			double m2 = slopeTo(p2);

			if(m1 < m2) return -1;
			else if(m1 > m2) return +1;
			else return 0;
		}
	}
}