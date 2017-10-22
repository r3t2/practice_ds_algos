/*
Implementation of DiGraph as learned in algs4 from Coursera
*/

import java.util.Scanner;
import java.util.Collection;
import java.util.ArrayList;

public class DiGraph
{
    private int V; // number of vertices
    private int E; // number of edges

    private Collection<Integer> [] adj;

    /* create empty DiGraph */
    public DiGraph(int V)
    {
        this.V = V;
        init();
    }

    /* create DiGraph from Scanner input */
    public DiGraph(Scanner sc)
    {
        this.V = sc.nextInt();
        int E_exp = sc.nextInt();
        
        init();
        
        for(int i=0; i<E_exp; i++)
        {
            addEdge(sc.nextInt(), sc.nextInt());
        }
    }

    private void init()
    {
        adj = (ArrayList<Integer> []) new ArrayList [V];

        for(int i=0; i<V; i++)
        {
            adj[i] = new ArrayList<Integer> ();
        }
    }

    public void addEdge(int v, int w)
    {
        adj[v].add(w);
        this.E++;
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

}
