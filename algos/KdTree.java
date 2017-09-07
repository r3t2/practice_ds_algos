import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.Iterator;

public class KdTree
{

  private Node root = null;

  private int size = 0;

  /*number of dimensions used*/
  private final int kd = 2;

  /*default constructor. constructs empty set of points*/
  public KdTree()
  {

  }


  public boolean isEmpty()
  {
    return size == 0;
  }

  public void insert(Point2D p)
  {
    root = insert(root, p, 0);
  }

  private Node insert(Node n, Point2D p, int d)
  {
    if(n == null) return new Node(p, d);

    if(n.p.equals(p)) return n; // return n if the current node contains a point equal to the input point.

    Comparator<Point2D> comp;

    if(n.d == 0) comp = Point2D.X_ORDER;
    else comp = Point2D.Y_ORDER;

    if(comp.compare(p, n.p) < 0)
    {
      n.left = insert(n.left, p, (d+1)%kd);
    }
    else
    {
      n.right = insert(n.right, p, (d+1)%kd);
    }

    return n;

  }

  /* does the DS contain the input point */
  public boolean contains(Point2D p)
  {
    return false;
  }
  /*return Iterable of points contained in the input rectangle*/
  public Iterable<Point2D> range(RectHV r)
  {
    return null;
  }

  /* number of points bounded by the rectangle defined by r */
  public int count(RectHV r)
  {
    return 0;
  }



  public Point2D nearest(Point2D p)
  {
    return nearest(root, p);
  }

  private Point2D nearest(Node n, Point2D p)
  {
  	if(n==null) return null;

  	if(n.p.equals(p)) return n.p; // if the point in current node equals p, then return n.p;

  	double sDist;
  	if(n.d == 0) sDist = Math.pow(p.x() - n.p.x(), 2);
  	else sDist = Math.pow(p.y() - n.p.y(), 2);
  	
  	Comparator<Point2D> comp;

  	if(n.d == 0) comp = Point2D.X_ORDER;
  	else comp = Point2D.Y_ORDER;

  	Point2D retP;
  	if(comp.compare(p, n.p) < 0)
  	{
  		retP = nearest(n.left, p);
  		if(retP!=null && retP.distanceSquaredTo(p) < sDist)
  		{
  			return retP;
  		}
  		else
  		{
  			retP = nearest(n.right, p);
  			if(retP!=null && retP.distanceSquaredTo(p) < n.p.distanceSquaredTo(p)) return retP;
  			else return n.p;
  		}
  	}
  	else
  	{
  		retP = nearest(n.right, p);
  		if(retP!=null && retP.distanceSquaredTo(p) < sDist)
  		{
  			return retP;
  		}
  		else
  		{
  			retP = nearest(n.left, p);
  			if(retP!=null && retP.distanceSquaredTo(p) < n.p.distanceSquaredTo(p)) return retP;
  			else return n.p;
  		}
  	}

  }




  public void draw()
  {
  	draw(root, 0.0, 1.0, 0.0, 1.0);
  }

  private void draw(Node n, double xmin, double xmax, double ymin, double ymax)
  {
  	if(n==null) return;

  	drawNode(n, xmin, xmax, ymin, ymax);

  	if(n.d == 0)
  	{
  		draw(n.left, xmin, n.p.x(), ymin, ymax);
  		draw(n.right, n.p.x(), xmax, ymin, ymax);
  	}
  	else
  	{
  		draw(n.left, xmin, xmax, ymin, n.p.y());
  		draw(n.right, xmin, xmax, n.p.y(), ymax);
  	}
  }

  private void drawNode(Node n, double xmin, double xmax, double ymin, double ymax)
  {
  	StdDraw.setPenColor(StdDraw.BLACK);
  	StdDraw.setPenRadius(0.01);
  	n.p.draw();
  	StdDraw.setPenRadius(0.001);

  	if(n.d == 0)
  	{
  		StdDraw.setPenColor(StdDraw.RED);
  		StdDraw.line(n.p.x(), ymin, n.p.x(), ymax);
  	}
  	else
  	{
  		StdDraw.setPenColor(StdDraw.BLUE);
  		StdDraw.line(xmin, n.p.y(), xmax, n.p.y());
  	}

  }




  public static void main(String[] args)
  {
  	KdTree kd = new KdTree();
  	kd.insert(new Point2D(0.5, 0.5));
  	kd.insert(new Point2D(0.25, 0.5));
  	kd.insert(new Point2D(0.65, 0.25));
  	kd.insert(new Point2D(0.25, 0.25));
  	kd.insert(new Point2D(0.75, 0.75));

  	kd.draw();
  }

  private static class Node
  {
    /*default constructor*/
    public Node() {}

    public Node(Point2D p, int d) {this.p = p; this.d = d;}

    /*reference to the point at this Node*/
    private Point2D p; 

    /*dimension to be compared at this node*/
    private int d = 0; 

    /*references to Nodes left and right to this Node*/
    private Node left, right;
  }
}