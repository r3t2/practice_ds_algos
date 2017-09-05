import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SkylineProblem
{
	public List<int[]> getSkyLine(int[][] buildings)
	{
		/*create new max priority queue*/
		PriorityQueue<Integer> heightPQ = new PriorityQueue<Integer> (11, 
			new Comparator<Integer>()
			{
				public int compare(Integer lhs, Integer rhs)
				{
					return -1*lhs.compareTo(rhs);
				}
			});

		PriorityQueue<Integer> buildingEndPQ = new PriorityQueue<Integer> ();

		/*Consider each building left to right. 
		1. For each building, add the height to heighPQ. Max height will be known in constant time.
		2. For each building, also add the right end point to buildingPQ which will be used to delete the 
		height corresponding to that building. */
		int [] building;
		int Li, Ri, Hi, nextBuilding=0;

		while (true)
		{
			if(buildingEndPQ.isEmpty() || (buildingEndPQ.isEmpty() == false && buildings[nextBuilding][0]))
		}
		





		return null;
	}

	private class MinComparator implements Comparator<Integer>
	{
		public int compare(Integer lhs, Integer rhs)
		{
			return lhs.compareTo(rhs);
		}
	}
	private class MaxComparator implements Comparator<Integer>
	{
		public int compare(Integer lhs, Integer rhs)
		{
			return -1*lhs.compareTo(rhs);
		}
	}
}