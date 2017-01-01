/**
Depth First Search on DiGraph.

DepthFirstPaths(int v) -- Constructor calculates the paths from vertex v to every other vertex reachable.

boolean hasPathTo (int w) -- returns true if there is a path from v to w

Iterable<Integer> pathTo(int w) -- returns the path from v to w as an Iterable

*/

import java.util.Stack;

public class DepthFirstPaths
{

	private	boolean [] marked;
	private int [] edgeTo;
	private int V;
	private int origin;

	public DepthFirstPaths(DiGraph dg, int v)
	{
		this.V = dg.V();
		this.origin = v;

		marked = new boolean[V];
		edgeTo = new int[V];

		for(int i=0; i<V; i++)
		{
			marked[i] = false;
			edgeTo[i] = -1;
		}

		marked[v] = true;
		edgeTo[v] = v;


		dfs(dg, v);

	}

	private void dfs(DiGraph dg, int v)
	{
		marked[v] = true;
		for(int w: dg.adj(v))
		{
			if(!marked[w])
			{
				edgeTo[w] = v;
				dfs(dg, w);	
			}	
		}

	}

	public boolean hasPathTo(int v)
	{
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v)
	{
		if(marked[v] == false)
			return null;
		
		Stack<Integer> path = new Stack<Integer>();
		

		while(v != origin)
		{
			path.push(v);
			v = edgeTo[v];
			
		}
		path.push(v);

		return path;
		
		
	}

	public static void main(String[] args) throws Exception
	{
		DiGraph dg = new DiGraph(4);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 1);
		dg.addEdge(1, 3);

		System.out.println(dg);

		DepthFirstPaths dfsp = new DepthFirstPaths(dg, 0);
		System.out.println(dfsp.pathTo(2));
	}



}