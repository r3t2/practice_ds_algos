import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints 
{
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment> ();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        check(points);

        Point [] points2 = Arrays.copyOf(points, points.length);
        
        for(Point p: points)
        {
            process(points2, p);
        }
    }

    private void process(Point [] points, Point p0)
    {
        Comparator<Point> slopeComparator = p0.slopeOrder();

        Arrays.sort(points, slopeComparator);
        
        // System.out.println(Arrays.toString(points));

        double currSlope;

        /*skip identical points */
        int i = 0;
        while(i < points.length && p0.slopeTo(points[i]) == Double.NEGATIVE_INFINITY) i++;
        
        List<Point> pts = new ArrayList<Point> ();

        LineSegment maxLS;

        while(i < points.length)
        {
            currSlope = p0.slopeTo(points[i]);
            pts.add(p0);
            

            while(i < points.length && p0.slopeTo(points[i]) == currSlope)
            {
                pts.add(points[i]);
                i++;
            }

            if(pts.size() >= 4)
            {
                // System.out.println(pts);
                maxLS = findMaximal(pts.toArray(new Point[pts.size()]));
                if(!contains(lineSegments, maxLS)) lineSegments.add(maxLS);
            }

            pts.clear();

        }
        

    }

    /* because the spec doesn't allow overriding the equals methods in Point and LineSegment class */
    private boolean contains(ArrayList<LineSegment> ls, LineSegment x)
    {
        for(LineSegment l: ls)
        {
            /* very bad hack because if the linesegment is reversed, it will fail this test*/
            /* it will work in this situation because we take care of the order in 
            creating a maximal linesegment i.e. smaller point comes first */
            if(l.toString().equals(x.toString())) return true;
        }

        return false;
    }

    private LineSegment findMaximal(Point [] pts)
    {
        Arrays.sort(pts);
        return new LineSegment(pts[0], pts[pts.length-1]);
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

        // // draw the points
        // StdDraw.setPenRadius(0.5);
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        // StdDraw.show();
        System.out.println("checking against brute force method");
        BruteCollinearPoints bcollinear = new BruteCollinearPoints(points);
        for (LineSegment segment : bcollinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }

        System.out.println("done");
    }
}