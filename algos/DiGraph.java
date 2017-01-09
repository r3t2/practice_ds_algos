


/**
Digraph(int V) -- Constructor to initialize V vertices.
Digraph(In in) -- Constructor to construct a graph by reading from input stream

int V() -- number of vertices
int E() -- number of edges

void addEdge(int v, int w) -- add edge between vertex v and w.

Iterable<Integer> adj(int v) -- Iterable object of vertices adjacent to the vertex v
*/

import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;


public class DiGraph
{
    // number of vertices
    private final int V;

    //number of edges
    private int E;

    // maintain a collection of adjacent vertices
    private Collection<Integer> [] adj;

    public DiGraph(int V) 
    {
        this.V = V;
        E = 0;

        init();
    }

    public DiGraph(Scanner sc) throws Exception
    {
        if (sc == null)
        {
            throw new NullPointerException("input scanner object is null. :(");
        }

        this.V = sc.nextInt();
        int E_expected = sc.nextInt();

        init();

        for(int i=0; i<E_expected; i++)
        {
            addEdge(sc.nextInt(), sc.nextInt());
        }

        if(sc.hasNextInt())
        {
            throw new IllegalArgumentException();
        }
    }

    private void init()
    {
        adj = (ArrayList<Integer> []) new ArrayList[V];
        for(int v=0; v<V; v++)
        {
            adj[v] = new ArrayList<Integer>();
        }
    }

    /*
    Add edge from vertex v to vertex w.
    */
    public void addEdge(int v, int w) throws Exception
    {
        check(v); check(w);

        adj[v].add(w);

        E++;
    }

    /*
    Adjacent vertices to vertex v
    */
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

    public DiGraph reverse() throws Exception
    {
        DiGraph dgReverse = new DiGraph(V);
        
        for (int v=0; v < V; v++)
        {
            for (int w: adj[v])
            {
                dgReverse.addEdge(w, v);
            }
        }
        
        return dgReverse;
        
    }

    private void check(int  v) throws Exception
    {
        if(v<0 || v>=V)
            throw new IllegalArgumentException("v = "+ v);
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

    public static void main(String[] args) throws Exception
    {
        System.out.println("args[] = " + Arrays.toString(args));

        String CURRENT_DIR = System.getProperty("user.dir");

        DiGraph dg;

        if(args.length == 1)
        {
            System.out.println("CURRENT_DIR =  " + CURRENT_DIR);
            dg = new DiGraph(new Scanner(new File( CURRENT_DIR + File.separator + args[0])));
        }
        else
        {
            dg = new DiGraph(4);
            dg.addEdge(0, 1);
            dg.addEdge(1, 2);
            dg.addEdge(2, 1);
            dg.addEdge(1, 3);
        }

        System.out.println(dg);

        DiGraph dgr = dg.reverse();
        System.out.println(dgr);


    }

}
