public class DiGraphUtils
{
  
  /**
  * Check if a graph is bipartite.
  * Achieved by visiting every vertex once and check if adjacent vertices are of different type.
  * 
  */
  public static boolean isBipartite (Digraph dg, int[] nodeType)
  {
    // V is the number of vertices in the digraph
    int V = dg.V();
    
    // marked[v] is true if the BFS visited vertex v
    boolean marked[V];
    for (int i=0; i<V; i++)
    {
      marked[i] = false;
    }
    
    for(int origin = 0; origin < V; origin++)
    {
      // skip running BFS from origin if it is already marked.
      if(marked[origin])
        continue;

      // create a Queue for BFS
      Queue<Integer> vQue = new LinkedList<Integer> ();
      vQue.add(origin);
      marked[origin] = true;

      // While vQue is not empty
      while(vQue.peek() != null)
      {
        
        v = vQue.remove();
        for(int w: dg.adj(v))
        {
          // If v and w are same type, then dg is not bipartite. Hence return false.
          if(nodeType[v] == nodeType[w])
            return false;
          
          // If w is not visited before, mark it and add to queue.
          if(!marked[w])
          {
            marked[w] = true;
            vQue.add(w);
          }
        }

      }
    
    }
    
    // If "return false" wasn't executed earlier, graph must be bipartite. Hence return true.
    return true;
  }
  
  

}
