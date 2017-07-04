import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class EulerTour
{
  /* get a copy of the graph to remove edges as we process them */
  private UnDirectedGraph g;



  private int V;
  private int E;

  private Deque<Integer> [] cycles;

  private Iterable<Integer> eulerPath;


  public EulerTour(UnDirectedGraph g)
  {
    
    this.g = new UnDirectedGraph(g);

    V = g.V();
    E = g.E();

    cycles = (Deque<Integer> []) new Deque[V];

    /* an outgoing edge from this vertex will be processed next*/
    int nextV = 0;
    while(nextV < V)
    {
      cycles[nextV] = getNextCycle(nextV);
      nextV = getNextV(nextV);
    }

    for(int i=0; i<V; i++)
    {
      System.out.println(cycles[i]);
    }

    eulerPath = mergeCycles();

    System.out.println("eulerPath = \n" + eulerPath);


  }

  private Deque<Integer> getNextCycle(int nextV)
  {
    Deque<Integer> cycle = new LinkedList<Integer>();
    Iterator<Integer> adj_v_itr;

    int v = nextV, w;
    adj_v_itr = g.adj(v).iterator();

    while(adj_v_itr.hasNext())
    {
      w = adj_v_itr.next();
      g.removeEdge(v, w);
      cycle.add(w);

      v = w;
      adj_v_itr = g.adj(v).iterator();
    }

    if(v != nextV)
    {
      //throw new Exception("cycle doesn't terminate at nextV = " + nextV);
      System.out.println("cycle doesn't terminate at nextV = " + nextV);
      //cycle = null;
    }
    return cycle;
  }

  private int getNextV(int v)
  {
    Iterator<Integer> adj_v_itr = g.adj(v).iterator();

    while(!adj_v_itr.hasNext() && (v < V))
    {
      v = v+1;
      if(v<V)
        adj_v_itr = g.adj(v).iterator();
    }

    return v;
  }

  private Iterable<Integer> mergeCycles()
  {
    Deque<Integer> path = new LinkedList<Integer> ();

    boolean [] marked = new boolean[V];

    for (int i=0; i<V; i++)
    {
      marked[i] = false;
    }

    path.add(0);
    mergeCycles(0, marked, path);

    return path;

  }

  private void mergeCycles(int v, boolean [] marked, Deque<Integer> path)
  {
    marked[v] = true;

    if(cycles[v] == null)
    {
      /* nothing to add to path*/
      return;
    }

    for(int w: cycles[v])
    {
      path.add(w);
      if(!marked[w])
      {
        mergeCycles(w, marked, path);
      }
    }
  }

  public static void main(String[] args)
  {
    UnDirectedGraph g = new UnDirectedGraph(new Scanner(System.in));
    System.out.println(g);

    EulerTour et = new EulerTour(g);
  }
}