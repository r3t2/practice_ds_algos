import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class StringQuickSort
{

	public StringQuickSort()
	{
	}

	public int getChar(String x, int d)
	{
		if(d<x.length())
		{
			return x.charAt(d);
		}
		else
		{
			return -1;
		}
	}

	public void sort(String [] x)
	{
		sort(x, 0, x.length-1, 0);
	}

	/* Sort the array of Strings between lo and hi using character value at position d*/
	private void sort(String [] x, int lo, int hi, int d)
	{
		if(hi <= lo || d>10)
			return;

		int pivot = getChar(x[lo], d);

		int lt_ptr = lo;
		int gt_ptr = hi;
		int i = lo;

		/*pivoting start*/
		while(i<=gt_ptr)
		{
			if( getChar(x[i], d) < pivot)
			{
				exch(x, i, lt_ptr);
				i++; lt_ptr++;
			}
			else if (getChar(x[i], d) > pivot)
			{
				exch(x, i, gt_ptr);
				gt_ptr--;
			}
			else
			{
				i++;
			}
		}

		//System.out.println("d = " + d + ", lo = " + lo + ", lt_ptr = " + lt_ptr + ", gt_ptr = " + gt_ptr);

		/* sort the elements smaller than pivot */
		sort(x, lo, 		lt_ptr-1, 	d);

		/*sort all elements equal to pivot*/
		if(pivot >= 0)
		{
			sort(x, lt_ptr, 	gt_ptr, 	d+1);
		}

		/*sort all elements greater than pivot*/
		sort(x, gt_ptr+1, 	hi, 		d);
	}

	private void exch(String[] x, int i, int j)
	{
		String temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	public static void main(String [] args)
	{
		String [] x = new String [] {"b", "c", "a"};
		StringQuickSort s = new StringQuickSort();

		s.sort(x);

		System.out.println(Arrays.toString(x));

		x = new String [] {"ba", "cb", "a"};
		s.sort(x);
		System.out.println(Arrays.toString(x));

		Scanner sc = new Scanner(System.in);
		ArrayList<String> arr = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			arr.add(sc.nextLine());
		}
		x = arr.toArray(new String[arr.size()]);
		System.out.println(Arrays.toString(x));
		s.sort(x);
		System.out.println(Arrays.toString(x));

	}
}