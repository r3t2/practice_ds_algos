import java.util.Arrays;
import java.util.Scanner;


public class LargestConsecutiveSumInArray
{

	public LargestConsecutiveSumInArray(int[] arr)
	{
		int N = arr.length;

		int [] maxStartingAt = new int[N];
		int [] lenStartingAt = new int[N];

		maxStartingAt[N-1] = arr[N-1];
		lenStartingAt[N-1] = 1;

		max_val_index = N-1;
		max_val = arr[N-1];


		for (int i = N-2; i >= 0 ; i--)
		{
			if(arr[i]  > arr[i] + maxStartingAt[i+1])
			{
				maxStartingAt[i] = arr[i];
				lenStartingAt[i] = 1;
			}
			else
			{
				maxStartingAt[i] = arr[i] + maxStartingAt[i+1];
				lenStartingAt[i] = lenStartingAt[i+1] + 1;
			}

			if(max_val < maxStartingAt[i])
			{
				max_val = maxStartingAt[i];
				max_val_index = i;
			}

			
		}

		System.out.println("maxStartingAt = " + Arrays.toString(maxStartingAt));
		System.out.println("lenStartingAt = " + Arrays.toString(lenStartingAt));


	}

	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int [] arr = new int[N];

		for(int i=0; i<N; i++)
		{
			arr[i] = sc.nextInt();
		}

		LargestConsecutiveSumInArray l = new LargestConsecutiveSumInArray(arr);
	}

}