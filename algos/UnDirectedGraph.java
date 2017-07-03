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

  public Iterable<Integer> adj(int v)
  {
    return adj[v];
  }

  public int V()
  {
    return V;
  }

  public int E()
  {
    return E;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(V + "\n");
    sb.append(E + "\n");

    for (int v=0; v<V; v++)
    {
        for(int w: adj[v])
        {
            sb.append(v + " " + w + "\n");
        }
    }

    return sb.toString();
  }

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    UnDirectedGraph g = new UnDirectedGraph(sc);

    UnDirectedGraph g2 = new UnDirectedGraph(g);

    System.out.println("g = \n" + g);
    System.out.println("g2 = \n" + g2);

    g2.removeEdge(0,5);
    g2.removeEdge(0,1);
    g2.removeEdge(0,2);
    g2.removeEdge(0,6);

    System.out.println("g = \n" + g);
    System.out.println("g2 = \n" + g2);
  }

}