import java.util.Arrays;

/**
3. 
Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
color(i): determine the color of the pebble in bucket i.
The performance requirements are as follows:

At most n calls to color().
At most n calls to swap().
Constant extra space.
*/

public class SortColorBucketsCeraW2P3
{
	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int WHITE = 2;

	public static void swap(int [] x, int i, int j)
	{
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	private static void check(int x)
	{
		if(!(x==RED || x==BLUE || x==WHITE)) throw new IllegalArgumentException();
	}

	public static void sortColors(int [] x)
	{
		if (x == null) throw new NullPointerException();


		int pivot = RED;

		int lt = 0, i = 0 , gt = x.length-1;

		while(i <= gt)
		{
			check(x[i]);
			if(x[i] < pivot) swap(x, i++, lt++);
			else if(x[i] > pivot) swap(x, i, gt--);
			else i++;
		}

	}

	public static void main(String[] args)
	{
		int [] x;

		x = new int[] {RED, BLUE, WHITE, WHITE, RED, BLUE, BLUE, WHITE, RED};
		sortColors(x);
		System.out.println(Arrays.toString(x));

		x = null;
		try{ sortColors(x); }
		catch(Exception e) {e.printStackTrace();}
		System.out.println(Arrays.toString(x));

		x = new int[] {};
		try{ sortColors(x); }
		catch(Exception e) {e.printStackTrace();}
		System.out.println(Arrays.toString(x));

		x = new int[] {RED};
		try{ sortColors(x); }
		catch(Exception e) {e.printStackTrace();}
		System.out.println(Arrays.toString(x));

		x = new int[] {BLUE, WHITE, BLUE, WHITE, WHITE, BLUE};
		try{ sortColors(x); }
		catch(Exception e) {e.printStackTrace();}
		System.out.println(Arrays.toString(x));

		x = new int[] {BLUE, WHITE, BLUE, WHITE, WHITE, 4};
		try{ sortColors(x); }
		catch(Exception e) {e.printStackTrace();}
		System.out.println(Arrays.toString(x));

	}
}