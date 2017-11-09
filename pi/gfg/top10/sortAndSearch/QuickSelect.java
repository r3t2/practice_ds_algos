import java.util.Arrays;

public class QuickSelect
{

  /*k is 0-indexed*/
  public static int kthSmallest(int [] x, int k)
  {
    return kthSmallest(x, k, 0, x.length-1);
  }

  private static int kthSmallest(int [] x, int k, int lo, int hi)
  {
    if(hi<lo) throw new IllegalArgumentException("Something went wrong. We shouldn't reach here.");

    PivotRetVal p = pivot(x, lo, hi);
    int pLo = p.pivLoIdx, pHi = p.pivHiIdx;

    if(pLo <= k && k <= pHi) return x[k];
    else if(k < pLo) return kthSmallest(x, k, lo, pLo-1);
    else /*if k > pHi*/ return kthSmallest(x, k, pHi+1, hi);
  }
  
  private static PivotRetVal pivot(int [] x, int lo, int hi)
  {
    int numElements = hi - lo + 1;

    int randIdx = lo + (int) (Math.random() * numElements); // generate a random index between lo - hi (both inclusive)
    swap(x, randIdx, lo); // first element will serve as pivot value

    int lt = lo, gt = hi, i = lo;
    int pivot = x[lo];

    while(i <= gt)
    {
      while(x[gt] > pivot) gt--;

      if(x[i] == pivot) i++;
      else if(x[i] < pivot)
      {
        swap(x, lt, i);
        lt++; i++;
      }
      else //x[i] > pivot
      {
        swap(x, i, gt);
      }

    }

    return new PivotRetVal(lt,gt);
    
  }

  private static void swap(int [] x, int i, int j)
  {
    int temp = x[j];
    x[j] = x[i];
    x[i] = temp;
  }

  public static void main(String [] args)
  {
    testPivot(new int []{5,5,6,1,2,7,7,8,5});
    testPivot(new int []{1,2,3,4,5});
    testPivot(new int []{5,4,3,2,1});
    testPivot(new int []{5});
    testPivot(new int []{1,1,1,1,1});

    System.out.printf("\n\nTesting kth smallest element\n");

    testkSmallest(new int []{5,5,6,1,2,7,7,8,5}, 3);
    testkSmallest(new int []{5,5,6,1,2,7,7,8,5}, 0);
    testkSmallest(new int []{5,5,6,1,2,7,7,8,5}, 6);
  }

  private static void testPivot(int [] x)
  {
    System.out.println("input = " + Arrays.toString(x));
    PivotRetVal p = QuickSelect.pivot(x, 0, x.length-1);
    System.out.println("pivoted output = " + Arrays.toString(x));
    System.out.printf("pivLoIdx = %d, pivHiIdx = %d\n\n", p.pivLoIdx, p.pivHiIdx);
  }

  private static void testkSmallest(int [] x, int k)
  {
    System.out.println("input = " + Arrays.toString(x));
    int ret = QuickSelect.kthSmallest(x, k);
    System.out.printf("k=%d smallest element = %d \n", k, ret);
  }

  private static class PivotRetVal
  {
    private int pivLoIdx;
    private int pivHiIdx;
    private PivotRetVal(int pivLoIdx, int pivHiIdx)
    {
      this.pivLoIdx = pivLoIdx;
      this.pivHiIdx = pivHiIdx;
    }
  }
}