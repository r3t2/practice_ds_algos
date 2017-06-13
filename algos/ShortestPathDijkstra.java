import java.util.PriorityQueue;


public class ShortestPathDijkstra
{
  private PriorityQueue<Integer> pq; // To find out the next vertex to relax its outward edges.
  private Double [] distTo;
  private Integer [] edgeTo;

  private int V;
  private int E;
  // input graph and the source vertex
  public ShortestPathDijkstra(EdgeWeightedDiGraph g, int s)
  {
    this.V = g.V();
    pq = new PriorityQueue<Integer> ();

    /*
    * Initialize the distance to vertex i from s to negative infinity.
    * Initialize the parent pointer to an invalid value (-1).
    */
    initArr(distTo, Double.NEGATIVE_INFINITY);
    initArr(edgeTo, -1);

    /*
    * Initialize the distance to source vertex to zero.
    * Initialize the parent to source vertex to s.
    */
    distTo[s] = 0.0;
    edgeTo[s] = s;
    
  }

  private <T> void initArr(T [] arr, T val)
  {
    for (int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }
}