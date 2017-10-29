import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.Queue;
import java.util.LinkedList;

public class PointSET
{
	private RedBlackBST<Point2D, Integer> bst;
	/*default constructor*/
	public PointSET()
	{
		bst = new RedBlackBST<Point2D, Integer>();
	}

	public boolean isEmpty()
	{
		return bst.isEmpty();
	}

	public int size()
	{
		return bst.size();
	}

	public void insert(Point2D p)
	{
		if(!bst.contains(p))
		{
			bst.put(p, 0);
		}
	}

	public boolean contains(Point2D p)
	{
		return bst.contains(p);
	}

	public void draw()
	{
		
		StdDraw.setPenColor(StdDraw.BLACK);
  		StdDraw.setPenRadius(0.01);

		for(Point2D p: bst.keys())
		{
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV r)
	{
		Queue<Point2D> q = new LinkedList<Point2D> ();

		for(Point2D p: bst.keys())
		{
			if(r.contains(p)) q.offer(p);
		}

		return q;
	}

	public Point2D nearest(Point2D q)
	{
		Double minDist = Double.POSITIVE_INFINITY;
		Point2D minDistPoint = null;

		for(Point2D p: bst.keys())
		{
			if(minDist > p.distanceSquaredTo(q))
			{
				minDist = p.distanceSquaredTo(q);
				minDistPoint = p;
			}
		}

		return minDistPoint;

	}

	public static void main(String[] args)
	{
	  	PointSET kd = new PointSET();
	  	kd.insert(new Point2D(0.5, 0.5));
	  	kd.insert(new Point2D(0.25, 0.5));
	  	kd.insert(new Point2D(0.65, 0.25));
	  	kd.insert(new Point2D(0.25, 0.25));
	  	kd.insert(new Point2D(0.75, 0.75));

	  	System.out.println("size = "  + kd.size());

	  	kd.draw();

	  	Point2D p;
	  	p = new Point2D(0.51, 0.51); System.out.println("nearest to" + p + "=" + kd.nearest(p));
	  	p = new Point2D(0.26, 0.26); System.out.println("nearest to" + p + "=" + kd.nearest(p));
	  	p = new Point2D(0.66, 0.26); System.out.println("nearest to" + p + "=" + kd.nearest(p));
	  	p = new Point2D(0.23, 0.23); System.out.println("nearest to" + p + "=" + kd.nearest(p));
	  	p = new Point2D(0.78, 0.78); System.out.println("nearest to" + p + "=" + kd.nearest(p));

	  	p = new Point2D(0.75, 0.75); System.out.println("contains " + p + "?=" + kd.contains(p));
	  	p = new Point2D(0.25, 0.5); System.out.println("contains " + p + "?=" + kd.contains(p));
	  	p = new Point2D(0.43, 0.89); System.out.println("contains " + p + "?=" + kd.contains(p));

	    RectHV r;
	    r = new RectHV(0.0, 0.0, 1.0, 1.0); System.out.println("points in rectangle " + r + "= " + kd.range(r));
	}
}