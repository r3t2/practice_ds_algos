public class LineSegmentIntersection
{
    private static class LineSegment
    {
        private Point p0;
        private Point p1;

        public LineSegment(Point p0, Point p1)
        {
            this.p0 = p0;
            this.p1 = p1;
        }

        /* check if this LineSegment intersects with another LineSegment*/
        public boolean intersects(LineSegment l)
        {
            if( (isCCW(p0, p1, l.p0) != isCCW(p0, p1, l.p1)) &&
                (isCCW(l.p0, l.p1, p0) != isCCW(l.p0, l.p1, p1))
                ) return true;
            else return false;
        }

        private boolean isCCW(Point a, Point b, Point c)
        {
            a = new Point(a);
            b = new Point(b);
            c = new Point(c);
            rotateAndTranslate(a, b, c);

            c.minus(b);
            double phi = Math.atan2(c.y, c.x);
            if(phi > 0) return true;
            else return false;
        }

        private void rotateAndTranslate(Point a, Point b, Point c)
        {
            // System.out.println(a);
            // System.out.println(b);
            // System.out.println(c);
            b.minus(a); //b=b-a
            c.minus(a); //c=c-a
            a.minus(a); // a = a-a // a is origin.
            double phi = Math.atan2(b.y, b.x);
            // rotate clockwise by phi.
            double [][] rot = new double [][] { {Math.cos(phi), Math.sin(phi)},
                                                {Math.sin(-phi), Math.cos(phi)} };
            a.rotate(rot);
            b.rotate(rot);
            c.rotate(rot);
            // System.out.println(a);
            // System.out.println(b);
            // System.out.println(c);
        }
    }
    private static class Point
    {
        private double x;
        private double y;

        private Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        private Point(Point p)
        {
            this(p.x, p.y);
        }

        public void minus(Point p)
        {
            this.x -= p.x;
            this.y -= p.y;
        }

        public void plus(Point p)
        {
            this.x += p.x;
            this.y += p.y;
        }

        /* rotation matrix is of the form (for clockwise rotation):
        rot = [cos(phi) sin(phi)]
              [-sin(phi) cos(phi)]
        to rotate:
        rot* [x]
             [y]
        result: x = cos(phi) * x + sin(phi) * y
                y = -sin(phi)* x + cos(phi) * y
        */
        public void rotate(double [][] rot)
        {
            double new_x = rot[0][0] * x + rot[0][1] * y;
            double new_y = rot[1][0] * x + rot[1][1] * y;
            x = new_x;
            y = new_y;
        }

        public String toString()
        {
            return "("+ x + ", " + y + ")";
        }
    }


    public static void main(String [] args)
    {
        runTest(0, 0, 4, 4, 4, 0, 0, 4);
        runTest(0, 0, 4, 4, 10, 0, 0, 10);
        runTest(0, 0, 4, 4, 8, 0, 0, 8);
        runTest(0, 0, 8, 0, 8, 0, 0, 8);
    }

    public static void runTest( double x0, double y0,
                                double x1, double y1,
                                double x2, double y2,
                                double x3, double y3)
    {
        Point p0, p1, p2, p3;
        p0 = new Point(x0, y0);
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        p3 = new Point(x3, y3);

        LineSegment l0 = new LineSegment(p0, p1);
        LineSegment l1 = new LineSegment(p2, p3);

        System.out.printf("intersects = %B\n\n", l0.intersects(l1));
    }
}