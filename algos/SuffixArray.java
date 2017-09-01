public class SuffixArray
{
	/* reference to the original String */
	private String str;	

	private String [] suffixes;


	public SuffixArray(String str)
	{
		this.str = str;

		int N = str.length();

		suffixes = new String[N];

		for(int i=0; i<N; i++)
		{
			/* suffixes[i] is a substring that starts at i*/
			suffixes[i] = str.subString(i);
		}
	}

	/*Use Mamber-Myer's algorithm*/
	public void sort()
	{
		
	}
}