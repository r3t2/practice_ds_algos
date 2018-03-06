import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class MinLenSubarraySumGtK {
  public static int minSubarrayGtK(int [] arr, int k)
  {
    if(arr.length == 0) return -1;
    
    int lt = 0, rt = 0;
    int sum = 0;    
    
    int minLen = Integer.MAX_VALUE;
    while(rt < arr.length)
    {
      while(rt<arr.length && sum <= k)
      {
        if(arr[rt] > sum + arr[rt])
        {
          sum = arr[rt];
          lt = rt;
        }
        else
        {
          sum += arr[rt];
        }
        rt+=1;
      }
      
      while(lt<rt)
      {
        if(minLen > (rt - lt) && sum > k)
        {
          minLen = rt-lt;
        }
        sum -= arr[lt];
        lt += 1;
      }
    }
    
    
    return minLen;
  }
  public static void main(String[] args) {
    runTest(new int[] {-1, -2, 2, 4, -3, 6}, 5);
    runTest(new int[] {-1, -2, 2, 4, -3, 5}, 5);
    runTest(new int[] {-1, -2, 2, 4, -3, 6}, 6);
    runTest(new int[] {-1, -2, 2, 5, -3, 6}, 4);
  }
  
  private static void runTest(int [] x, int k)
  {
    System.out.printf("input = %s, k = %d\nminSubarrayLen = %d\n\n",
                     Arrays.toString(x), k,
                     minSubarrayGtK(x,k));
  }
}
