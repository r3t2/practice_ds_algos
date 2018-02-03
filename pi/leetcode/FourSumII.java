import java.util.*;
public class FourSumII
{
/* 454. 4Sum II
Given four lists A,
B, C, D of integer values, compute how many tuples (i, j, k, l) there are such
that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤
500. All integers are in the range of -228 to 228 - 1 and the result is
guaranteed to be at most 231 - 1. */

  public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
      Map<Integer, Integer> map = new HashMap<> ();

      for(int i=0; i<A.length; i++)
      {
        for(int j=0; j<B.length; j++)
        {
          int cnt = 0, sum = A[i] + B[j];
          if (map.containsKey(sum)) cnt = map.get(sum);
          map.put(sum, cnt+1);
        }
      }

      int cnt = 0;
      for(int k=0; k<C.length; k++)
      {
        for(int l=0; l<D.length; l++)
        {
          int sum = -(C[l] + D[k]);
          if(map.containsKey(sum))
          {
            cnt+=map.get(sum);
          }
        }
      }
      return cnt;
  }

  public static void main(String [] args)
  {
    runTest(new int[] {1,2,3}, new int[] {4,5,6}, new int[] {-3,-2,-1}, new int[] {-4,-5,-6});
    runTest(new int[] {1,2}, new int[] {-2,-1}, new int[] {-1,2}, new int[] {0,2});
    runTest(new int[] {-1,-1}, new int[] {-1,1}, new int[] {-1,1}, new int[] {1,-1});
  }

  private static void runTest(int [] a, int [] b, int [] c, int [] d)
  {
    System.out.printf("a = %s, b = %s, c = %s, d = %s \n", 
      Arrays.toString(a), Arrays.toString(a), Arrays.toString(a), Arrays.toString(a));
    System.out.printf("cnt = %d \n", FourSumII.fourSumCount(a, b, c, d));
  }
}
