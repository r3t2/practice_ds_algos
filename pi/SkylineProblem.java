import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Queue;

public class SkylineProblem
{
	public List<int[]> getSkyline(int[][] buildings)
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
		int nextBStart, nextBEnd, skyX=-1, prevSkyX=-1, skyH=-1, prevSkyH=-1;
		Node nextBEndNode;

		while (!(bEndPQ.isEmpty()) || (nextBIdx < buildings.length))
		{
			if(nextBIdx < buildings.length) nextBStart = buildings[nextBIdx][0];
			else nextBStart = -1;

			if(bEndPQ.isEmpty() == false) nextBEnd = bEndPQ.peek().x;
			else nextBEnd = Integer.MAX_VALUE;

			/*when nextBStart == nextBEnd, remove the building and add next building to compute skyline*/
			if((nextBStart>=0) && (nextBStart <= nextBEnd))
			{
				Hi = buildings[nextBIdx][2];
				Ri = buildings[nextBIdx][1];
				Li = buildings[nextBIdx][0];

				hPQ.offer(Hi);
				bEndPQ.offer(new Node(Ri, Hi, nextBIdx));

				nextBIdx++;

				prevSkyX = skyX;
				skyX = Li;
			}
			else
			{
				nextBEndNode = bEndPQ.poll();
				Hi = nextBEndNode.h;
				hPQ.remove(Hi);

				prevSkyX = skyX;
				skyX = nextBEndNode.x;
			}

			prevSkyH = skyH;
			skyH = hPQ.peek()==null?0:hPQ.peek();

			addToSkyline(skyline, new int[]{skyX, skyH});
			
		}

		return skyline;
	}

	private void addToSkyline(List<int[]> skyline, int[] currPoint)
	{
		if(skyline.isEmpty())
		{
			skyline.add(currPoint);
			return;
		}
		else
		{
			int[] prevPoint = skyline.remove(skyline.size()-1);
			/*If x value between curr and prev is same, keep current point*/
			if(prevPoint[0] == currPoint[0])
			{
				skyline.add(currPoint);
				return;
			}
			/*x value is not the same*/
			else
			{ 
				if(prevPoint[1] == currPoint[1])
				{
					skyline.add(prevPoint);
					return;
				}
				else
				{
					skyline.add(prevPoint);
					skyline.add(currPoint);
					return;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		ArrayList<int[]> buildings = new ArrayList<int[]>();
		Queue<Integer> intStream = new LinkedList<Integer>();
		int Li, Ri, Hi;

		String patternString = "\\d+";
		Pattern p = Pattern.compile(patternString);
		Matcher m;

		String inp;
		while(sc.hasNextLine())
		{
			inp = sc.nextLine();
			m = p.matcher(inp);
			while(!m.hitEnd() && m.find())
			{
				intStream.add(Integer.parseInt(m.group()));
			}
		}

		int numBuildings = 0;
		while(!intStream.isEmpty())
		{
			Li = intStream.poll();
			Ri = intStream.poll();
			Hi = intStream.poll();
			buildings.add(new int[]{Li, Ri, Hi});
			numBuildings++;
		}



		System.out.println("input buildings:");
		for(int[] building: buildings)
		{
			System.out.println(Arrays.toString(building));
		}

		SkylineProblem s = new SkylineProblem();
		int[][] buildingsArray = buildings.toArray(new int[numBuildings][3]);


		List<int[]> skyline = s.getSkyline(buildingsArray);

		System.out.println("skyline = ");
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