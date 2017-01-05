/**
* Computes topological order of a given DiGraph
*/

// imports
import java.util.Stack;
import java.util.Arrays;

public class DepthFirstOrder
{
  // DFS visit?
  private Boolean [] marked;
  private int V;
  private Stack<Integer> reversePost = new Stack<Integer> ();
  
  public DepthFirstOrder(DiGraph dg)
  {
    this.V = dg.V();
    
    marked = new Boolean[V];
    initialize(marked, false);
    
    for (int s=0; s<V; s++)
    {
      if(!marked[s])
      {
        dfs(dg, s);
      }
    
    }
  
  }
  
  private <T> void initialize(T[] a, T value)
  {
    for (int i=0; i<a.length;i++)
    {
      a[i] = value;
    }
  }
  
  private void dfs(DiGraph dg, int v)
  {
    marked[v] = true;
    
    for (int w: dg.adj(v))
    {
      if(!marked[w])
      {
        dfs(dg, w);
      }
    }
    
    reversePost.push(v);
  }
  
  public Iterable<Integer> reversePostOrder()
  {
    return reversePost;
  }


  public static void main(String[] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));

    DiGraph dg = new DiGraph(6);

    dg.addEdge(5, 1);
    dg.addEdge(1, 2);
    dg.addEdge(2, 3);
    dg.addEdge(1, 4);
    dg.addEdge(4, 3);
    dg.addEdge(0, 3);


    DepthFirstOrder topo = new DepthFirstOrder(dg);
    System.out.println(topo.reversePostOrder());

  }

}
