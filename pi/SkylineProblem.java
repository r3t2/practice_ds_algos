import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class SkylineProblem
{
	public List<int[]> getSkyLine(int[][] buildings)
	{
		/*create new max priority queue*/
		PriorityQueue<Integer> hPQ = new PriorityQueue<Integer> (11, new MaxComparator());

		PriorityQueue<Node> bEndPQ = new PriorityQueue<Node> (11, new MinComparator());

		List<int[]> skyline = new ArrayList<int[]> ();

		/*Consider each building left to right. 
		1. For each building, add the height to heighPQ. Max height will be known in constant time.
		2. For each building, also add the right end point to buildingPQ which will be used to delete the 
		height corresponding to that building. */
		int [] building;
		int Li, Ri, Hi, nextBIdx=0;
		int nextBStart, nextBEnd, skyX, skyH;
		Node nextBEndNode;

		while (!(bEndPQ.isEmpty()) || (nextBIdx < buildings.length))
		{
			if(nextBIdx < buildings.length) nextBStart = buildings[nextBIdx][0];
			else nextBStart = Integer.MAX_VALUE;

			if(bEndPQ.isEmpty() == false) nextBEnd = bEndPQ.peek().x;
			else nextBEnd = Integer.MAX_VALUE;

			if(nextBStart < nextBEnd)
			{
				Hi = buildings[nextBIdx][2];
				Ri = buildings[nextBIdx][1];
				Li = buildings[nextBIdx][0];

				hPQ.offer(Hi);
				bEndPQ.offer(new Node(Ri, Hi, nextBIdx));

				nextBIdx++;

				skyX = Li;
			}
			else
			{
				nextBEndNode = bEndPQ.poll();
				Hi = nextBEndNode.h;
				hPQ.remove(Hi);

				skyX = nextBEndNode.x;
			}


			skyH = hPQ.peek()==null?0:hPQ.peek();
			skyline.add(new int[]{skyX, skyH});


		}

		return skyline;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		ArrayList<int[]> buildings = new ArrayList<int[]>();
		int Li, Ri, Hi;

		int numBuildings = 0;

		while(sc.hasNext())
		{
			Li = sc.nextInt();
			Ri = sc.nextInt();
			Hi = sc.nextInt();

			buildings.add(new int[] {Li, Ri, Hi});
			numBuildings++;
		}

		SkylineProblem s = new SkylineProblem();
		List<int[]> skyline = s.getSkyLine(buildings.toArray(new int[numBuildings][3]));


		System.out.println(skyline);
		for(int[] point : skyline)
		{
			System.out.println(point[0] + ", " + point[1]);
		}




	}

	private class MinComparator implements Comparator<Node>
	{
		public int compare(Node lhs, Node rhs)
		{
			if(lhs.x < rhs.x) return -1;
			else if (lhs.x > rhs.x) return 1;
			else return 0;
		}
	}
	private class MaxComparator implements Comparator<Integer>
	{
		public int compare(Integer lhs, Integer rhs)
		{
			return -1*lhs.compareTo(rhs);
		}
	}

	private static class Node
	{
		private Node(int x, int h, int idx)
		{
			this.x = x;
			this.h = h;
			this.idx = idx;
		}
		int x;
		int h;
		int idx;
	}
}