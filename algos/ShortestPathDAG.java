import java.util.Deque;
import java.util.LinkedList;


public class ShortestPathDAG
{
  private double[] distTo;
  private int[] edgeTo;

  private int V;

  /*save reference to the graph being processed*/
  private EdgeWeightedDiGraph g;

  public ShortestPathDAG(EdgeWeightedDiGraph g, int s)
  {
    this.g = g;
    this.V = g.V();

    distTo = new double[V];
    edgeTo = new int[V];

    initArr(distTo, Double.POSITIVE_INFINITY);
    initArr(edgeTo, -1);

    runSpDag(s);
  }

  private void runSpDag(int s)
  {
    Iterable<Integer> topo = topoOrder(s);
    for(Integer v : topo)
    {
      for(DirectedEdge dE : g.adj(v))
      {
        relaxEdge(dE);
      }
    }
  }

  private void relaxEdge(DirectedEdge e)
  {
    int v = e.from(), w = e.to();
    double weight = e.weight();

    if(distTo[v] + weight < distTo[w])
    {
      distTo[w] = distTo[v] + weight;
      edgeTo[w] = v;
    }
  }

  private Iterable<Integer> topoOrder(int s)
  {
    boolean [] marked = new boolean[V];
    initArr(marked, false);

    Deque<Integer> dq = new LinkedList<Integer>();
    topoOrder(marked, dq, s);

    return dq;
  }

  private void topoOrder(boolean [] marked, Deque<Integer> dq, int v)
  {
    marked[v] = true;
    int w;
    for(DirectedEdge dE: g.adj(v))
    {
      w = dE.to();
      if(marked[w] == false)
      {
        topoOrder(marked, dq, w);
      }
    }
    dq.addFirst(v);
  }

  private void initArr(double [] arr, double val)
  {
    for(int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }

  private void initArr(int [] arr, int val)
  {
    for(int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }

  private void initArr(boolean [] arr, boolean val)
  {
    for(int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }


}