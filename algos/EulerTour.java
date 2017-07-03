import java.util.Deque;
import java.util.LinkedList;

public class EulerTour
{
  /* get a copy of the graph to remove edges as we process them */
  private UnDirectedGraph g;

  /* an outgoing edge from this vertex will be processed next*/
  private int nextV;

  private int V;
  private int E;

  private Deque<Integer> [] cycles;


  public EulerTour(UnDirectedGraph g)
  {
    
    this.g = new UnDirectedGraph(g);

    V = g.V();
    E = g.E();

    cycles = (Deque<Integer> []) new Deque[V];

    nextV = 0;
    while(nextV != -1)
    {
      cycles[nextV] = getNextCycle();
    }


  }

  private Deque<Integer> getNextCycle()
  {
    return new LinkedList<Integer>();
  }
}