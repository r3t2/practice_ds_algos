import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class DiGraphUtils
{
  
  /**
  * Check if a graph is bipartite.
  * Achieved by visiting every vertex once and check if adjacent vertices are of different type.
  * 
  */
  public static boolean isBipartite (DiGraph dg, int[] nodeType)
  {
    // V is the number of vertices in the digraph
    int V = dg.V();
    int v;
    
    // marked[v] is true if the BFS visited vertex v
    boolean [] marked = new boolean[V];
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


  public static void main(String[] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));

    if(args[0].equals("bipartite"))
    {
      DiGraph dg = new DiGraph(4);
      dg.addEdge(0, 1);
      dg.addEdge(1, 2);
      dg.addEdge(2, 1);
      dg.addEdge(1, 3);

      int[] nodeTypeBipartite = {0, 1, 0, 0};
      int[] nodeTypeNonBipartite = {0, 1, 1, 0};

      System.out.println("expected = true, actual = " + isBipartite(dg, nodeTypeBipartite));
      System.out.println("expected = false, actual = " + isBipartite(dg, nodeTypeNonBipartite));
    }
  }
  
  

}
