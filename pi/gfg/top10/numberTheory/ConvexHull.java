import java.util.Deque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Comparator;

public class ConvexHull
{

  public static Iterable<Point> convexHull(Point [] points)
  {
    double yMin = Double.POSITIVE_INFINITY;
    Point pMin = null;

    for(Point p: points)
    {
      if(yMin > p.y) {yMin = p.y; pMin = p;}
    }

    /*Sort the points by the angle points make with the */
    Arrays.sort(points, pMin.polarComparator());
    Deque<Point> hull = new LinkedList<Point> ();
    //System.out.println(Arrays.toString(points));

    Point pm1, pm2; //points p_minus_1 and p_minus_2
    for(Point p0 : points)
    {
      while(hull.size() >=2)
      {
        pm1 = hull.removeFirst();
        pm2 = hull.removeFirst();
        if(isCCW(pm2, pm1, p0))
        {
          hull.addFirst(pm2);
          hull.addFirst(pm1);
          break;
        }
        else
        {
          hull.addFirst(pm2);
        }
      }
      hull.addFirst(p0);
    }

    return hull;
  }

  private static boolean isCCW(Point a, Point b, Point c)
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

  private static void rotateAndTranslate(Point a, Point b, Point c)
  {
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
      x -= p.x;
      y -= p.y;
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

    public Comparator<Point> polarComparator()
    {
      return new PolarComparator();
    }

    public class PolarComparator implements Comparator<Point>
    {
      public int compare(Point p1, Point p2)
      {
        
        double phi1 = Math.atan2(p1.y - y, p1.x - x);
        double phi2 = Math.atan2(p2.y - y, p2.x - x);

        if(phi1 > phi2) return +1;
        else if(phi1 < phi2) return -1;
        else return 0;
      }
    }
  }




  public static void main(String [] args)
  {
    runTest(new double [] {1,0, 0,2, 2,2, 3,4, 4,2});
    runTest(new double [] {0,0, 4,0, 4,4, 2,2, 0,4});
  }

  private static void runTest(double [] coords)
  {
    Point [] points = new Point[coords.length/2];
    for(int i=0; i<coords.length; i+=2)
    {
      points[i/2] = new Point(coords[i], coords[i+1]);
    }

    Iterable<Point> hull = ConvexHull.convexHull(points);
    System.out.println(hull);
  }
}