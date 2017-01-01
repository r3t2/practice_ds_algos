/**
BreadthFirstPaths.java -- BFS on DiGraph

BreadFirstPaths(DiGraph dg, int v) -- Constructor that runs BFS on DiGraph dg using v as the source vertex.
boolean hasPathTo(int v) -- returns true if there is a path from source vertex to v
Iterable<Integer> pathTo(int v) -- returns path from source vertex to vertex v if it exists, else null.

*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths
{
	private boolean [] marked;
	private int [] edgeTo;
	private int origin;
	private int V;
	
	public BreadthFirstPaths(DiGraph dg, int v)
	{
		origin = v;
		V = dg.V();
		
		marked = new boolean[V];
		edgeTo = new int[V];

		for (int i=0; i<V; i++)
		{
			marked[i] = false;
			edgeTo[i] = -1;
		}
		
		bfs(dg, v);		

	}
	
	private void bfs(DiGraph dg, int v)
	{
		Queue<Integer> vQueue = new LinkedList<Integer> ();
		vQueue.add(v);
		marked[v] = true;
		edgeTo[v] = v;
		
		// 
		while(vQueue.peek() != null)
		{
			v = vQueue.remove();


			for (int w: dg.adj(v))
			{
				if(!marked[w])
				{
					marked[w] = true;
					vQueue.add(w);
					edgeTo[w] = v;
				}
			}
		}
	}

	public boolean hasPathTo(int v)
	{
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v)
	{
		if (!marked[v])
			return null;


		Stack<Integer> path = new Stack<Integer>();
		
		while(v != origin)
		{
			path.push(v);
			v = edgeTo[v];
		}
		path.push(origin);
	
		return path;

	}

	public static void main(String[] args) throws Exception
	{
		DiGraph dg = new DiGraph(4);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(0, 2);
		dg.addEdge(2, 1);
		dg.addEdge(1, 3);

		System.out.println(dg);

		BreadthFirstPaths bfsp = new BreadthFirstPaths(dg, 0);
		System.out.println(bfsp.pathTo(2));
	}

}
