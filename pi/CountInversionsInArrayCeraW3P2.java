/** 
2.  Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such
that i<j but a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of
inversions. 
*/

import java.util.Arrays;

public class CountInversionsInArrayCeraW3P2
{
	public static int count(int [] x)
	{
		if(x == null) throw new NullPointerException();

		return count(x, 0, x.length-1);
	}

	private static int count(int [] x, int start, int end)
	{
		if(start >= end) return 0;	

		int mid = (start + end)/2;

		int cntL = count(x, start, mid);
		int cntR = count(x, mid+1, end);

		int cnt = merge(x, start, mid, end);

		return cntL + cntR + cnt;
	}

	private static int merge(int[] x, int start, int mid, int end)
	{
		int l1 = start, r1 = mid;
		int l2 = mid+1, r2 = end;

		int i1 = l1, i2 = l2;

		int [] aux = new int [end - start + 1];

		int cnt = 0;
		int i = 0, j = 0;

		while(i1<=r1 && i2<=r2)
		{
			if(x[i1] > x[i2])
			{
				aux[i++] = x[i2++];
				cnt += (r1 - i1 + 1);
			}
			else
			{
				aux[i++] = x[i1++];
			}
		}

		/* Only one of the two will be true */

		while(i1 <= r1) aux[i++] = x[i1++]; 

		while(i2 <= r2) aux[i++] = x[i2++];

		for(i=0,j=start; j<end; i++,j++) x[j] = aux[i];

		return cnt;
	}

	public static void main(String [] args)
	{
		int [] x;

		x = new int[] {0, 1, 2, 3};
		System.out.println("num inversions in " + Arrays.toString(x) + " = " + CountInversionsInArrayCeraW3P2.count(x));

		x = new int[] {3, 2, 1, 0};
		System.out.println("num inversions in " + Arrays.toString(x) + " = " + CountInversionsInArrayCeraW3P2.count(x));

		x = new int[] {};
		System.out.println("num inversions in " + Arrays.toString(x) + " = " + CountInversionsInArrayCeraW3P2.count(x));

		x = null;
		System.out.println("num inversions in " + Arrays.toString(x) + " = ");
		try { CountInversionsInArrayCeraW3P2.count(x); }
		catch (Exception e) { e.printStackTrace(); }

		x = new int[] {6, 3, 1, 2, 0, 4};
		System.out.println("num inversions in " + Arrays.toString(x) + " = " + CountInversionsInArrayCeraW3P2.count(x));

		x = new int[] {1, 1, 1, 1, 1};
		System.out.println("num inversions in " + Arrays.toString(x) + " = " + CountInversionsInArrayCeraW3P2.count(x));
	}
}
