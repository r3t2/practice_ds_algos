/**
* Computes topological order of a given DiGraph
*/

// imports
import java.util.Stack;

public class DepthFirstOrder
{
  // DFS visit?
  private boolean [] marked;
  private int V;
  private Stack<Integer> reversePost = new Stack<Integer> ();
  
  public DepthFirstOrder(DiGraph dg)
  {
    this.V = dg.V();
    
    marked = new boolean[V];
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
    for (int i=0; i<a.length; a++)
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

}
