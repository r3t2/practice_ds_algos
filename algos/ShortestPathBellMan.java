import java.util.Arrays;
import java.util.Scanner;


public class ShortestPathBellMan
{
	private Integer [] edgeTo;
	private Double [] distTo;

	private int V;
	private EdgeWeightedDiGraph g;

	public ShortestPathBellMan(EdgeWeightedDiGraph g, int s)
	{
		this.g = g;
		this.V = this.g.V();
		edgeTo = new Integer[V];
		distTo = new Double[V];



		init_arr(edgeTo, -1);
		init_arr(distTo, Double.POSITIVE_INFINITY);


		edgeTo[s] = s;
		distTo[s] = 0.0;

		runBellMan(s);

	}
	private void runBellMan(int s)
	{
		boolean graphUpdated;

		for (int i=0; i<V; i++)
		{
			graphUpdated = false;

			for (int j=0; j<V; j++)
			{
				for (DirectedEdge e: g.adj(j))
				{
					graphUpdated = graphUpdated || relaxEdge(e);
				}
			}
			
			if(graphUpdated == false)
			{
				System.out.println("ending relaxEdge when i = " + i);
				break;
			}
		}

	}

	private boolean relaxEdge(DirectedEdge e)
	{
		boolean retVal = false;

		int v = e.from(), w = e.to();
		double weight = e.weight();

		if(distTo[v] + weight < distTo[w])
		{
			distTo[w] = distTo[v] + weight;
			edgeTo[w] = v;
			retVal = true;
		}

		return retVal;
	}

	private <T> void init_arr(T[] arr, T val)
	{
		for (int i=0; i<arr.length; i++)
		{
			arr[i] = val;
		}
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("distTo = " + Arrays.toString(distTo) + "\n");
		sb.append("edgeTo = " + Arrays.toString(edgeTo));

		return sb.toString();
	}

	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);

		EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(sc);
		System.out.println(g);

		ShortestPathBellMan sp = new ShortestPathBellMan(g, 0);
		System.out.println(sp);
	}
}