import java.util.Arrays;

public class QuickSelect
{
  
  private static void pivot(int [] x, int lo, int hi)
  {
    int numElements = hi - lo + 1;

    //int randIdx = lo + (int) Math.random() * numElements; // generate a random index between lo - hi (both inclusive)
    //swap(x, randIdx, lo); // first element will serve as pivot value

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
  }

  private static void testPivot(int [] x)
  {
    System.out.println("input = " + Arrays.toString(x));
    QuickSelect.pivot(x, 0, x.length-1);
    System.out.println("pivoted output = " + Arrays.toString(x));
  }
}