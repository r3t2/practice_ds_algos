import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation
{
	public static void main(String[] args)
	{
		int k = Integer.parseInt(args[0]);

		String [] strs = StdIn.readAllStrings();

		RandomizedQueue<String> r = new RandomizedQueue<String> ();

		for(int i=0; i<strs.length; i++)
		{
			r.enqueue(strs[i]);
		}

		Iterator<String> itr = r.iterator();
		int cnt = 0;

		while(itr.hasNext() && cnt < k)
		{
			System.out.println(itr.next());
			cnt++;
		}
	}
}