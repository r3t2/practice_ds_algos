import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints 
{
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment> ();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        check(points);

        Point[] points2 = new Point[points.length];
        for(Point p: points)
        {
            process(points, p);
        }
    }

    private void process(Point [] points, Point p)
    {
        Comparator<Point> slopeComparator = p.slopeOrder();

        Arrays.sort(points, slopeComparator);
    }

    private void check(Point [] points)
    {
        if(points == null) throw new IllegalArgumentException();

        for(Point p: points)
        {
            if(p == null) throw new IllegalArgumentException();
        }

        /*Use of HashTable not allowed in the spec*/
        int N = points.length;
        for(int i=0; i<N; i++)
        {
            for(int j=i+1; j<N; j++)
            {
                if(points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
    }

    // the number of line segments
    public int numberOfSegments()        
    {
        return lineSegments.size();
    }
    // the line segments
    public LineSegment[] segments()
    {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public static void main(String [] args)
    {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        System.out.println("done");
    }
}