import java.util.HashMap;
import java.util.Arrays;

public class LeetCode_TwoSums
{

	public static int[] twoSum(int [] nums, int target)
	{
		HashMap<Integer, Integer> valIdx = new HashMap<Integer, Integer> ();
		int [] ret = null;
		int t = 0;

		for (int i=0; i < nums.length; i++)
		{
			valIdx.put(nums[i], i);
		}


		for(int i=0; i < nums.length; i++)
		{
			t = target - nums[i];
			
			if(valIdx.containsKey(t))
			{
				int j = valIdx.get(t);
				if(i !=j )
				{
					ret = (new int[]{i, valIdx.get(target - nums[i])});
					break;
				}
			}
		}

		return ret;
	}

	public static void main(String[] args)
	{
		
		System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 1, 5}, 9)));
		System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));


	}
}
