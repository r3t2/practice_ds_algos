import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;

public class UnDirectedGraph
{
  private Collection<Integer> [] adj;
  private int V;
  private int E;


  public UnDirectedGraph(int V)
  {
    init(V);
  }

  public UnDirectedGraph(Scanner sc)
  {
    V = sc.nextInt();
    init(V);

    int E_expected = sc.nextInt();
    for(int i=0; i<E_expected; i++)
    {
      addEdge(sc.nextInt(), sc.nextInt());
    }
  }

  public UnDirectedGraph(UnDirectedGraph g)
  {
    init(g.V());

    for(int i=0; i<V; i++)
    {
      adj[i] = new ArrayList<Integer> (g.adj[i]);
    }

    this.E = g.E();

  }

  private void init(int V)
  {
    this.V = V;
    this.E = 0;
    adj = (ArrayList<Integer> []) new ArrayList[V];
    for(int i=0; i<V; i++)
    {
      adj[i] = new ArrayList<Integer> ();
    }
    
  }

  public void addEdge(int u, int v)
  {
    adj[u].add(v);
    adj[v].add(u);
    E++;
  }

  public void removeEdge(int u, int v)
  {
    adj[u].remove(v);
    adj[v].remove(u);
    E--;
  }

  public int V()
  {
    return V;
  }

  public int E()
  {
    return E;
  }

}