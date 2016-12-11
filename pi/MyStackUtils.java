import java.util.Stack;
import java.util.Random;

public class MyStackUtils
{
	/**
	* In-place sort of a Stack. Uses a temporary Stack as buffer.
	*/
	public static <E extends Comparable<E>> void sort(Stack<E> input)
	{

		if (input == null)
		{
			throw new NullPointerException();
		}

		if(input.isEmpty())
		{
			return;
		}

		Stack<E> buffer = new Stack<E> ();

		long i = 0;

		long N = input.size();

		E currMax = null;

		E temp = null;

		for (long numPop = N; numPop > 0; numPop--)
		{
			i =0;

			currMax = null;

			while (i < numPop)
			{
				temp = input.pop();

				if(i == 0)
				{
					currMax = temp;
				}
				else
				{
					if(currMax.compareTo(temp) < 0)
					{
						buffer.push(currMax);
						currMax = temp;
					}
					else
					{
						buffer.push(temp);
					}
				}

				i++;
			}

			input.push(currMax);

			while( !buffer.isEmpty())
			{
				input.push(buffer.pop());
			}
		}

	}

	public static void main(String[] args)
	{
		Stack<Integer> s = new Stack<Integer> ();

		Random r = new Random();
		for (int i=0; i<10; i++)
		{
			s.push(r.nextInt(100));
		}

		System.out.println(s);
		MyStackUtils.sort(s);

		System.out.println(s);
	}
}