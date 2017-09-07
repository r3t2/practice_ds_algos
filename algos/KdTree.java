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

  private void insert(Node n, Point2D p, int d)
  {
    if(n == null) return new Node(p);

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
    return null;
  }

  private static class Node
  {
    /*default constructor*/
    public Node() {}

    public Node(Point2D p) {this.p = p;}

    /*reference to the point at this Node*/
    private Point2D p; 

    /*dimension to be compared at this node*/
    private int d = 0; 

    /*references to Nodes left and right to this Node*/
    private Node left, right;
  }
}