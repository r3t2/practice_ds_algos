import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints
{
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points)
    {
        /*Constructor takes O(N**4) time. check() takes O(N**2) time.*/
        check(points);

        int N = points.length;

        for(int p = 0; p < N; p++)
        {
            for(int q = p+1; q < N; q++)
            {
                for(int r = q+1; r<N; r++)
                {
                    for(int s = r+1; s<N; s++)
                    {
                        process(points[p], points[q], points[r], points[s]);
                    }
                }
            }
        }
    }

    private void process(Point p, Point q, Point r, Point s)
    {
        double p1 = p.slopeTo(q);
        double p2 = p.slopeTo(r);

        if(p1 != p2) return;

        double p3 = p.slopeTo(s);

        LineSegment maxLS;
        if((p1 == p2) && (p2 == p3))
        {
            maxLS = findMaximal(new Point [] {p, q, r, s});
            if(!lineSegments.contains(maxLS)) lineSegments.add(maxLS);
        }
    }

    private LineSegment findMaximal(Point [] pts)
    {
        Arrays.sort(pts);
        return new LineSegment(pts[0], pts[3]);
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
    public  int numberOfSegments()
    {
        return lineSegments.size();

    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        System.out.println("done");
    }
}