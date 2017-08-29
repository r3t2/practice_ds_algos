import java.util.Arrays;

public class BucketSort
{
	private int[] count;
	private int R;

	public BucketSort(int R)
	{
		count = new int[R+1];
		this.R = R;
	}



	public void sort(int[] x)
	{
		int [] aux = new int[x.length];

		/*count items*/
		for(int i=0; i<x.length; i++)
		{
			count[ x[i]+1 ] += 1;
		}

		for(int i=1; i<R+1; i++)
		{
			count[i] = count[i-1] + count[i];
		}

		int pos;
		for(int i=0; i<x.length; i++)
		{
			pos = count[x[i]];
			aux[pos] = x[i];
			count[x[i]] = ++pos;
		}

		for(int i=0; i<x.length; i++)
		{
			x[i] = aux[i];
		}


	}

	public static void main(String[] args)
	{
		BucketSort b = new BucketSort(10);

		int [] x = new int[] {1,2,3,0,4,1,0};
		b.sort(x);

		System.out.println(Arrays.toString(x));
	}
}