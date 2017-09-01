import java.util.Arrays;

public class RotatedSortedArray
{
	public RotatedSortedArray()
	{

	}

	public int pivot(int [] x)
	{
		int lo = 0;
		int hi = x.length-1;
		int mid = (lo+hi)/2;

		/* if the array doesn't have a rotation i.e., pivot is at 0, return 0*/
		/*For arrays where all elements are equal, we'll still call the recursive method below*/
		if((x[lo] < x[mid])&&(x[mid] < x[hi]))
		{
			return 0;
		}

		/*Find pivot recursively.*/
		return pivot(x, 0, x.length-1);
	}

	private int pivot(int [] x, int lo, int hi)
	{
		if(lo > hi)
			return -1;

		if((hi - lo) == 1)
		{
			if(x[lo] > x[lo+1]) return (lo+1);
			else return -1;
		}
		else if((hi - lo) == 2)
		{
			if(x[lo] > x[lo+1]) return (lo+1);
			else if (x[lo+1] > x[lo+2]) return (lo+2);
			else return -1;
		}

		int mid = (lo+hi)/2;

		/*for non-rotated array, expected: x[lo] <= x[mid] && x[mid] <= x[hi]*/
		if((x[lo] >= x[mid]) && (x[mid] < x[hi]))
		{
			return pivot(x, lo, mid);
		}
		else if((x[mid] >= x[hi]) && (x[lo] < x[mid]))
		{
			return pivot(x, mid, hi);
		}
		/*x[lo] == x[mid] && x[mid] == x[hi]*/
		/* need to search both sides*/
		else
		{
			int res = pivot(x, lo, mid);
			if(res == -1)
				res = pivot(x, mid, hi);
			return res;
		}
	}

	public static void main(String [] args)
	{
		runPivot(new int []{0,1,2,3,4});

		runPivot(new int []{4,0,1,2,3});

		runPivot(new int []{1,2,3,4,0});

		runPivot(new int []{1,2,3,4,4,4,4,0});

		runPivot(new int []{4,4,4,4,4,4});

		runPivot(new int []{4,4,4,1,4,4});
	}
	private static void runPivot(int [] x)
	{
		RotatedSortedArray r = new RotatedSortedArray();
		System.out.println("input = " + Arrays.toString(x));
		System.out.println("pivot = " + r.pivot(x));	
	}
}