/* Given a large text file, find an efficient algorithm to find the least
distance(measured in number of words) between any two given words. */

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class MinDistBetween2WordsInStrList
{

	private HashMap<String, List<Integer>> str2Idxs = new HashMap<String, List<Integer>>();

	public MinDistBetween2WordsInStrList(File f) throws Exception
	{
		/* equivalent to __init__ */
		Scanner sc = new Scanner(f);

		String str;
		List<Integer> idxList;
		int idxCnt = 0;

		while(sc.hasNext())
		{
			str = sc.next();

			idxList = str2Idxs.get(str);

			if(idxList == null) idxList = new ArrayList<Integer>();

			idxList.add(idxCnt);

			str2Idxs.put(str, idxList);

			idxCnt++;
		}

	}

	public int getMinDist(String a, String b)
	{
		List<Integer> aList = str2Idxs.get(a);
		List<Integer> bList = str2Idxs.get(b);

		if(aList == null || bList == null) return -1;

		Integer [] aArr = aList.toArray(new Integer[aList.size()]);
		Integer [] bArr = bList.toArray(new Integer[bList.size()]);

		return closestDiff(aArr, bArr);
	}

	/* some method */
	private static int closestDiff(Integer [] a, Integer [] b)
	{
		if(a == null || b == null) throw new IllegalArgumentException();

		if(a.length == 0 || b.length == 0) throw new IllegalArgumentException();

		int ai = 0, bi = 0;
		int curDiff = 0;
		int minDiff = Integer.MAX_VALUE;

		while( ai < a.length && bi < b.length  )
		{
			curDiff = Math.abs(a[ai] - b[bi]);

			if(minDiff > curDiff) minDiff = curDiff;

			if( ((ai+1) < a.length) && Math.abs(a[ai+1] - b[bi]) <= curDiff) ai++;
			else if( ((bi+1) < b.length) && Math.abs(a[ai] - b[bi+1]) <= curDiff) bi++;
			else {ai++; bi++;}
		}

		while( ai < a.length )
		{
			curDiff = Math.abs(a[ai] - b[bi-1]);

			if(minDiff > curDiff) minDiff = curDiff;

			ai++;
		}

		while( bi < b.length)
		{
			curDiff = Math.abs(a[ai-1] - b[bi]);

			if(minDiff > curDiff) minDiff = curDiff;

			bi++;
		}


		return minDiff;

	}


	public static void main(String[] args) throws Exception
	{
		Integer [] a, b;

		System.out.println("testing closestDiff");

		a = new Integer[]{0, 10, 12, 100, 110};
		b = new Integer[]{8, 13, 41, 90, 99};
		testClosestDiff(a, b);

		a = new Integer[]{5, 10, 30, 55};
		b = new Integer[]{0, 20, 40, 45, 51, 56};
		testClosestDiff(a, b);

		a = new Integer[]{0, 10, 30, 50};
		b = new Integer[]{24, 28, 32, 48};
		testClosestDiff(a, b);

		a = new Integer[]{};
		b = new Integer[]{24, 28, 32, 48};
		try {testClosestDiff(a, b);}
		catch(Exception e) {e.printStackTrace();}


		System.out.println("testing with File");
		File f = new File("./test/misc/tinyText.txt");
		MinDistBetween2WordsInStrList mDist = new MinDistBetween2WordsInStrList(f);
		System.out.println(mDist.getMinDist("Jack", "Jill"));

	}

	private static void testClosestDiff(Integer [] a, Integer [] b)
	{
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("b = " + Arrays.toString(b));
		System.out.println("closestDiff = " + closestDiff(a, b));
	}

}
