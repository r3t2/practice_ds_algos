/*
Given two sorted integer arrays, find the median element. 
Note that for an even sized collection, median element is to be defined 
as the average of the central two elements.
*/


import java.util.Arrays;

public class MedianFinder
{

	public static int medianLinearBrute(int [] a, int [] b)
	{
		if(a == null || b == null) throw new IllegalArgumentException();

		int ai=0, bi=0, cnt=0;
		int aLen = a.length, bLen = b.length;
		boolean isEven = ((aLen + bLen) % 2) == 0;

		int [] mergedArr = new int[aLen + bLen];

		while(ai < aLen && bi < bLen)
		{
			if(a[ai] < b[bi]) mergedArr[cnt] = a[ai++];
			else mergedArr[cnt] = b[bi++];
			cnt++;
		}
		while(ai<aLen)
		{
			mergedArr[cnt++] = a[ai++];
		}

		while(bi<bLen)
		{
			mergedArr[cnt++] = b[bi++];
		}

		int mid = (aLen+bLen)/2;
		if(isEven) return (mergedArr[mid] + mergedArr[mid - 1])/2;
		else return mergedArr[mid];
	}


	public static int medianLinear(int [] a, int [] b)
	{
		if(a == null || b == null) throw new IllegalArgumentException();

		int ai=0, bi=0;
		int aLen = a.length, bLen = b.length;
		boolean isEven = ((aLen + bLen) % 2) == 0;

		int cnt = 0;
		int prev = 0, curr = 0, offset = 0;

		while((cnt <= (aLen + bLen)/2) && (ai < aLen) && (bi < bLen))
		{
			prev = curr;
			if(a[ai] < b[bi]) {curr = a[ai]; ai++;}
			else {curr = b[bi]; bi++;};

			cnt++;
		}

		if(cnt != (aLen + bLen)/2 + 1)
		{
			offset = (aLen + bLen)/2 - cnt;
			if(bi == bLen)
			{
				curr = a[ai + offset];
				prev = a[ai + offset - 1];
			}
			else
			{
				curr = b[bi + offset];
				prev = b[bi + offset - 1];
			}
		}

		if(isEven) return (prev + curr)/2;
		else return curr;
	}

	public static void main(String [] args)
	{
		testMedian(new int[]{0,2,4,6}, new int[]{1,3,5,7});

		testMedian(new int[]{0,2,4,6,8}, new int[]{1,3,5,7});

		testMedian(new int[]{0,2}, new int[]{1,3,4,5,6,7});

		testMedian(new int[]{0,2}, new int[]{1,3,4,5,6,7,8});

		testMedian(new int[]{0}, new int[]{1,2,3,4,5,6,7});

		testMedian(new int[]{0}, new int[]{1,2,3,4,5,6,7,8});

		testMedian(new int[]{1,2,3,4,5,6,7,8}, new int[]{0});

		testMedian(new int[]{}, new int[]{0,1,2,3,4,5,6,7});

	}

	private static void testMedian(int [] arr1, int [] arr2)
	{
		System.out.println("arr1 = " + Arrays.toString(arr1));
		System.out.println("arr2 = " + Arrays.toString(arr2));

		int medianLinBr = medianLinearBrute(arr1, arr2);
		int medianLin = medianLinear(arr1, arr2);
		System.out.printf("medianLinearBrute = %d, medianLin = %d\n", medianLinBr, medianLin);
	}

}
