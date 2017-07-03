import java.util.Deque;
import java.util.LinkedList;

public class EulerTour
{
  /* get a copy of the graph to remove edges as we process them */
  private UnDirectedGraph g;



  private int V;
  private int E;

  private Deque<Integer> [] cycles;


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


  }

  private Deque<Integer> getNextCycle(int nextV)
  {
    Deque<Integer> cycle = new LinkedList<Integer>();
    Iterable<Integer> adj_v_itr;

    int v = nextV, w;
    adj_v_itr = g.adj(v);

    while(adj_v_itr.hasNext())
    {
      w = adj_v_itr.next();
      g.removeEdge(v, w);
      cycle.add(w);

      v = w;
      adj_v_itr = g.adj(v);
    }

    if(v != nextV)
    {
      throw new Exception("cycle doesn't terminate at nextV = " + nextV);
      //cycle = null;
    }
    return cycle;
  }

  private int getNextV(int v)
  {
    Iterable<Integer> adj_v_itr = g.adj(v);

    while(!adj_v_itr.hasNext() && (v < V))
    {
      v = v+1;
    }

    return v;
  }
}