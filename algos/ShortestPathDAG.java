import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;

public class ShortestPathDAG
{
  private double[] distTo;
  private int[] edgeTo;

  private int V;

  /*save reference to the graph being processed*/
  private EdgeWeightedDiGraph g;

  public ShortestPathDAG(EdgeWeightedDiGraph g, int s) throws Exception
  {
    this.g = g;
    this.V = g.V();

    distTo = new double[V];
    edgeTo = new int[V];

    initArr(distTo, Double.POSITIVE_INFINITY);
    initArr(edgeTo, -1);

    distTo[s] = 0;
    edgeTo[s] = s;

    runSpDag(s);
  }

  private void runSpDag(int s) throws Exception
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

  private Iterable<Integer> topoOrder(int s) throws Exception
  {
    boolean [] marked = new boolean[V];
    initArr(marked, false);

    Deque<Integer> dq = new LinkedList<Integer>();
    HashSet<Integer> inStack = new HashSet<Integer> ();

    inStack.add(s);
    topoOrder(marked, dq, inStack, s);

    return dq;
  }

  private void topoOrder(boolean [] marked, Deque<Integer> dq, HashSet inStack, int v) throws Exception
  {
    marked[v] = true;
    int w;
    for(DirectedEdge dE: g.adj(v))
    {
      w = dE.to();

	  if(inStack.contains(w))
	  {
	    throw new Exception("cycle detected");
	  }

      if(marked[w] == false)
      {
      	inStack.add(w);
        topoOrder(marked, dq, inStack, w);
      }
    }
    dq.addFirst(v);
    inStack.remove(v);
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

  public String toString()
  {
  	StringBuilder sb = new StringBuilder();
  	sb.append("distTo: " + Arrays.toString(distTo) + "\n");
  	sb.append("edgeTo: " + Arrays.toString(edgeTo));

  	return sb.toString();
  }


  public static void main(String[] args) throws Exception
  {
  	Scanner sc = new Scanner (System.in);
  	EdgeWeightedDiGraph dg = new EdgeWeightedDiGraph(sc);
  	System.out.println(dg);

  	ShortestPathDAG sp = new ShortestPathDAG(dg, 0);

  	System.out.println(sp);
  }


}