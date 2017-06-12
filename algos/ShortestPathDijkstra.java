public class ShortestPathDijkstra
{
  private PriorityQueue<int> pq; // To find out the next vertex to relax its outward edges.
  private double [] distTo;
  private int [] edgeTo;

  private int V;
  private int E;
  // input graph and the source vertex
  public ShortestPathDijkstra(EdgeWeightedDiGraph g, int s)
  {
    this.V = g.V();
    pq = new PriorityQueue<int> ();

    initArr(distTo, Double.NEGATIVE_INFINITY);
    initArr(edgeTo, -1);

    /*
    * Initialize the distance to source vertex to zero.
    * Initialize the parent to source vertex to s.
    */
    distTo[s] = 0;
    edgeTo[s] = s;
    
  }
}