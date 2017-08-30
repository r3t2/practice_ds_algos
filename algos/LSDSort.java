import java.util.Arrays;

public class LSDSort
{
	/*Radix used in sorting*/
	private int R;
	private int W;




	public LSDSort(int R)
	{
		this.R = R;
	}

	private int getChar(String x, int d)
	{
		if(d >= x.length())
		{
			return '\0';
		}
		else
		{
			return x.charAt(d);
		}
	}
	private void bucketSort(String[] x, int d)
	{
		int [] count = new int[R+1];
		/* possible optimization: pass the aux array as input. 
		Or even better alternate aux and input so don't have to copy back to x but need to keep track of the output */
		String [] aux = new String[x.length];

		for(int i=0; i<x.length; i++)
		{
			count[ getChar(x[i], d)+1 ] += 1;
		}

		for(int i=1; i<R+1; i++)
		{
			count[i] = count[i-1] + count[i];
		}

		int pos = 0;
		for(int i=0; i<x.length; i++)
		{
			pos = count[ getChar(x[i], d) ];
			aux[pos] = x[i];
			count[ getChar(x[i], d) ] = ++pos;
		}

		for(int i=0; i<x.length; i++)
		{
			x[i] = aux[i];
		}

	}
	public void sort(String [] x, int W)
	{
		if(x==null)
			return;
		
		this.W = W;

		for(int d=W-1; d>=0; d--)
		{
			bucketSort(x, d);
		}
	}
	/*Input is strings to be sorted.*/
	/*Output is available in place*/
	public void sort(String [] x)
	{
		W = -1;
		/*determine the largest string*/
		for(int i=0; i<x.length; i++)
		{
			if(W < x[i].length())
			{
				W = x[i].length();
			}
		}

		sort(x, W);

	}

	public static void main(String[] args)
	{
		LSDSort l = new LSDSort(256);

		String [] in = new String[] {"a", "b", "ab", "def", "c", "abc"};
		l.sort(in);
		System.out.println(Arrays.toString(in));

		in = new String[] {"", "", "ab", "def", "c", "abc"};
		l.sort(in);
		System.out.println(Arrays.toString(in));
	}
}