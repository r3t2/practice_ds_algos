/*
* StrongConnectedComponents.java
* public StrongConnectedComponents(DiGraph dg) -- Constructor that finds strong connnected components given a DiGraph dg.
* public int numComponents() -- Number of strong connected components
* public boolean stronglyConnected(int v, int w) -- are vertices v and w strongly connected?
* public int componentNum(int v) -- component id of vertex v
*/

/* imports */

public class StrongConnectedComponents
{
  /*fields*/
  // use to track if a vertex is visited.
  private Boolean [] marked;
  
  // maintain component number per vectex.
  private Integer [] component;
  
  // number of strongly connected components
  private int numComponents;
  
  // number of vectices
  private int V;
  
  
  /* algo
  * Get dgr from dg.
  * Find topological order of dgr.
  * Run DFS on dg considering vertices in the topo order of dgr.
  */ 
  public StrongConnectedComponents(DiGraph dg) throws Exception
  {
  
    V = dg.V();
    
    marked = new Boolean[V];
    component = new Integer[V];
    numComponents = 0;
    
    initialize(marked, false);
    initialize(component, -1);
    
    
    DiGraph dgr = dg.reverse();
    
    DepthFirstOrder dfOrder = new DepthFirstOrder(dgr);
    Iterable<Integer> topoOrder = dfOrder.reversePostOrder();
    
    for(int v: topoOrder)
    {
      dfs(dg, v);
      numComponents++;
    }
    
  }
  
  private void dfs(DiGraph dg, int v)
  {
    marked[v] = true;
    component[v] = numComponents;
    
    for(int w: dg.adj(v))
    {
      if(!marked[w])
      {
        dfs(dg, w);
      }
    }
  }
  
  private int numComponents()
  {
    return numComponents;
  }
  
  private boolean stronglyConnected(int v, int w)
  {
    return (component[v] == component[w]);
  }
  
  private int componentNum(int v)
  {
    return component[v];
  }
  
  private <T> void initialize(T[] arr, T val)
  {
    for (int i=0; i<arr.length; i++)
    {
      arr[i] = val;
    }
  }
  
  
}
