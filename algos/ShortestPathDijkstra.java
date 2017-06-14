import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class ShortestPathDijkstra
{
  private PriorityQueue<PQElement> pq; // To find out the next vertex to relax its outward edges.
  private Double [] distTo;
  private Integer [] edgeTo;

  /*save reference to the graph being processed*/
  private EdgeWeightedDiGraph g;

  private int V;
  private int E;
  // input graph and the source vertex
  public ShortestPathDijkstra(EdgeWeightedDiGraph g, int s)
  {
    this.g = g;

    this.V = this.g.V();
    pq = new PriorityQueue<PQElement> ();

    /*
    * Initialize the distance to vertex i from s to negative infinity.
    * Initialize the parent pointer to an invalid value (-1).
    */

    distTo = new Double[V];
    edgeTo = new Integer[V];

    initArr(distTo, Double.POSITIVE_INFINITY);
    initArr(edgeTo, -1);

    /*
    * Initialize the distance to source vertex to zero.
    * Initialize the parent to source vertex to s.
    */
    distTo[s] = 0.0;
    edgeTo[s] = s;

    pq.add(new PQElement(s, 0.0));
    runDijkstra();
    
  }

  private <T> void initArr(T [] arr, T val)
  {
    for (int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }

  private void runDijkstra()
  {
    int v, w;
    PQElement p;

    while(!pq.isEmpty())
    {
      p = pq.remove();
      v = p.v;
      /* for every directedEdge dE in outgoing edges of vertex v*/
      for(DirectedEdge dE : g.adj(v))
      {
        relaxEdge(dE);
      }
    }
  }

  public Iterable<Integer> pathTo(int dest)
  {
    Deque<Integer> dq = new LinkedList<Integer> ();
    
    int i = dest;

    while((edgeTo[i] != -1) && (edgeTo[i] != i))
    {
      i = edgeTo[i];
      dq.addFirst(i);
    }

    return dq;

  }

  private void relaxEdge(DirectedEdge e)
  {
    PQElement tempPqElement;

    int v = e.from(); int w = e.to(); double weight = e.weight();
    if(distTo[v] + weight < distTo[w])
    {
      distTo[w] = distTo[v] + e.weight();
      edgeTo[w] = v;

      tempPqElement = new PQElement(w, distTo[w]);

      /*O(N)*/
      if(pq.contains(tempPqElement)) // equals method in PQElement class is such that it checks only the vertex part.
      {
        pq.remove(tempPqElement);
      }

      /*O(logN)*/
      pq.add(tempPqElement);
    }
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("distTo:: " + Arrays.toString(distTo) + "\n");
    sb.append("edgeTo:: " + Arrays.toString(edgeTo) + "\n");

    return sb.toString();
  }

  private class PQElement implements Comparable<PQElement>
  {
    int v; // vertex
    double weight;

    public PQElement(int v, double weight)
    {
      this.v = v;
      this.weight = weight;
    }

    public boolean equals(PQElement that)
    {
      return this.v == that.v;
    }

    public int compareTo(PQElement that)
    {
      Double thisW = new Double(this.weight);
      Double thatW = new Double(that.weight);

      return thisW.compareTo(thatW);
    }

  }

  public static void main(String [] args)
  {
    Scanner sc = new Scanner(System.in);
    EdgeWeightedDiGraph edwg = new EdgeWeightedDiGraph(sc);
    System.out.println(edwg);

    ShortestPathDijkstra sp = new ShortestPathDijkstra(edwg, 0);
    System.out.println(sp);

    System.out.println("path to 1:: " + sp.pathTo(1));
    System.out.println("path to 3:: " + sp.pathTo(3));

  }
}