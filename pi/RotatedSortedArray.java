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

	public int blindPivotBSearch(int [] x, int searchKey)
	{
		return blindPivotBSearch(x, 0, x.length-1, searchKey);
	}

	private int blindPivotBSearch(int [] x, int lo, int hi, int searchKey)
	{
		if(hi<lo)
			return -1;

		int mid = (lo+hi)/2;

    //System.out.println(String.format("lo = %d, mid = %d, hi = %d", lo, mid, hi));

		if(x[mid] == searchKey)
			return mid;

    if(x[mid] < x[lo])
    {
      if( ((searchKey < x[mid]) && (searchKey < x[hi])) || ((searchKey > x[mid]) && (searchKey > x[hi])) )
      {
        return blindPivotBSearch(x, lo, mid-1, searchKey);
      }
      else
      {
        return blindPivotBSearch(x, mid+1, hi, searchKey);
      }
    }
    else if (x[mid] >x[lo])
    {
      if(searchKey < x[mid] && searchKey > x[lo])
      {
        return blindPivotBSearch(x, lo, mid-1, searchKey); 
      }
      else
      {
        return blindPivotBSearch(x, mid+1, hi, searchKey); 
      }
    }
    else /* x[mid] = x[lo]*/
    {
      
    }

	}

	public int pivotBinarySearch(int [] x, int searchKey)
	{
		int pivot = pivot(x);
		return pivotBinarySearch(x, 0, x.length-1, pivot, searchKey);

	}
	private int pivotBinarySearch(int [] x, int lo, int hi, int pivot, int searchKey)
	{
		if(hi < lo)
			return -1;

		int mid = (lo+hi)/2;

		int midVal = x[vBufIdx(mid, pivot, x.length)];
		if(midVal == searchKey)
			return vBufIdx(mid, pivot, x.length);

		if(midVal > searchKey)
		{
			return pivotBinarySearch(x, lo, mid-1, pivot, searchKey);
		}
		else
		{
			return pivotBinarySearch(x, mid+1, hi, pivot, searchKey);
		}

	}
	private int vBufIdx(int idx, int pivot, int bufLen)
	{
		int finalIdx = (idx+pivot) % bufLen;
		if(finalIdx < 0) finalIdx += bufLen;

		return finalIdx;
	}

	public static void main(String [] args)
	{
		int [] x;

		x = new int []{0,1,2,3,4,5};
		runPivot(x);
		runBinarySearch(x, 5);
    runBlindPivotBSearch(x,5);

		x = new int []{4,0,1,2,3};
		runPivot(x);
		runBinarySearch(x, 0);
    runBlindPivotBSearch(x, 0);

		x = new int []{1,2,3,4,0};
		runPivot(x);
		runBinarySearch(x, 5);
    runBlindPivotBSearch(x, 5);

		x = new int []{1,2,3,4,4,4,4,0};
		runPivot(x);
		runBinarySearch(x, 0);
    runBlindPivotBSearch(x, 0);

		x = new int []{4,4,4,4,4,4};
		runPivot(x);
		runBinarySearch(x, 4);
    runBlindPivotBSearch(x, 4);

		x = new int []{4,4,4,1,4,4};
		runPivot(x);
		runBinarySearch(x, 1);
    runBlindPivotBSearch(x, 1);

	}
	private static void runPivot(int [] x)
	{
		RotatedSortedArray r = new RotatedSortedArray();
		System.out.println("\ninput = " + Arrays.toString(x));
		System.out.println("pivot = " + r.pivot(x));	
	}
	private static void runBinarySearch(int [] x, int searchKey)
	{
		RotatedSortedArray r = new RotatedSortedArray();
		System.out.println("searchKey = " + searchKey);
		System.out.println("output index = " + r.pivotBinarySearch(x, searchKey));
	}
  private static void runBlindPivotBSearch(int [] x, int searchKey)
  {
    RotatedSortedArray r = new RotatedSortedArray();
    System.out.println("searchKey = " + searchKey);
    System.out.println("output index = " + r.blindPivotBSearch(x, searchKey));
  }
}