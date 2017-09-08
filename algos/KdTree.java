import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
    return contains(root, p);
  }

  private boolean contains(Node n, Point2D p)
  {

  	if(n == null) return false;

  	if(n.p.equals(p)) return true;

  	Comparator<Point2D> comp;
  	if(n.d == 0) comp = Point2D.X_ORDER;
  	else comp = Point2D.Y_ORDER;

  	if(comp.compare(p, n.p) <0) return contains(n.left, p);
  	else return contains(n.right, p);

  }

  /*return Iterable of points contained in the input rectangle*/
  public Iterable<Point2D> range(RectHV r)
  {
    Queue<Point2D> q = new LinkedList<Point2D> ();

    range(root, r, q);
    return q;
  }

  private void range(Node n, RectHV r, Queue<Point2D> q)
  {
    if(n == null) return;

    if(r.contains(n.p)) q.offer(n.p);

    if(n.d == 0)
    {
      if(r.xmin() <= n.p.x()) range(n.left, r, q);
      if(r.xmax() >= n.p.x()) range(n.right, r, q);
    }
    else
    {
      if(r.ymin() <= n.p.y()) range(n.left, r, q);
      if(r.ymax() >= n.p.y()) range(n.right, r, q);
    }
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


  	
  	Comparator<Point2D> comp;

  	if(n.d == 0) comp = Point2D.X_ORDER;
  	else comp = Point2D.Y_ORDER;

  	
  	if(comp.compare(p, n.p) < 0)
  	{
  		return nearestCompareLogic(n, n.left, n.right, p);
  	}
  	else
  	{
  		return nearestCompareLogic(n, n.right, n.left, p);
  	}

  }

  private Point2D nearestCompareLogic(Node n, Node tryFirst, Node trySecond, Point2D query)
  {
  	double sDist;

  	if(n.d == 0) sDist = Math.pow(query.x() - n.p.x(), 2);
  	else sDist = Math.pow(query.y() - n.p.y(), 2);

  	Point2D retP1, retP2;
  	
  	retP1 = nearest(tryFirst, query);
	if((retP1!=null) && (retP1.distanceSquaredTo(query) < sDist))
	{
		return retP1;
	}
	else
	{
		retP2 = nearest(trySecond, query);
	}

	return min3Points(query, n.p, retP1, retP2);
  }

  private Point2D min3Points(Point2D query, Point2D p1, Point2D p2, Point2D p3)
  {
  	Double s1, s2, s3;

  	if(p1 != null) s1 = query.distanceSquaredTo(p1);
  	else s1 = Double.POSITIVE_INFINITY;

  	if(p2 != null) s2 = query.distanceSquaredTo(p2);
  	else s2 = Double.POSITIVE_INFINITY;

  	if(p3 != null) s3 = query.distanceSquaredTo(p3);
  	else s3 = Double.POSITIVE_INFINITY;

  	if(s1 < s2)
  	{
  		if(s1 < s3) return p1;
  		else return p3;
  	}
  	else
  	{
  		if(s2 < s3) return p2;
  		else return p3;
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

  	Point2D p;
  	p = new Point2D(0.51, 0.51); System.out.println("nearest to" + p + "=" + kd.nearest(p));
  	p = new Point2D(0.26, 0.26); System.out.println("nearest to" + p + "=" + kd.nearest(p));
  	p = new Point2D(0.66, 0.26); System.out.println("nearest to" + p + "=" + kd.nearest(p));
  	p = new Point2D(0.23, 0.23); System.out.println("nearest to" + p + "=" + kd.nearest(p));
  	p = new Point2D(0.26, 0.01); System.out.println("nearest to" + p + "=" + kd.nearest(p));

  	p = new Point2D(0.75, 0.75); System.out.println("contains " + p + "?=" + kd.contains(p));
  	p = new Point2D(0.25, 0.5); System.out.println("contains " + p + "?=" + kd.contains(p));
  	p = new Point2D(0.43, 0.89); System.out.println("contains " + p + "?=" + kd.contains(p));

    RectHV r;
    r = new RectHV(0.0, 0.0, 1.0, 1.0); System.out.println("points in rectangle " + r + "= " + kd.range(r));
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