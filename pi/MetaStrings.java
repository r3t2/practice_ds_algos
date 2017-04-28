import java.util.Scanner;
import java.util.Arrays;

public class MetaStrings
{

  private static int SPECIAL_VALUE = Integer.MAX_VALUE;
  public static int areMetaStrings(String in1, String in2)
  {

    if (in1 == null || in2 == null)
    {
      return 0;
    }

    char [] arr1 = in1.toCharArray();
    char [] arr2 = in2.toCharArray();



    if (arr1.length != arr2.length)
    {
      return 0;
    }

    int [] diff = new int[arr1.length];

    for (int i=0; i<arr1.length; i++)
    {
      diff[i] = arr1[i] - arr2[i];
    }

    // System.out.println(Arrays.toString(diff));

    int val0 = SPECIAL_VALUE, val1 = SPECIAL_VALUE;
    for (int i=0; i<diff.length; i++)
    {
      if(diff[i] != 0)
      {
        if(val0 == SPECIAL_VALUE)
        {
          val0 = diff[i];
        }
        else if (val1 == SPECIAL_VALUE)
        {
          val1 = diff[i];
        }
        else
        {
          return 0;
        }
      }
    }


    
    if (val0 == -val1)
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }

  public static void main(String [] args)
  {
    Scanner sc = new Scanner(System.in);
    // sc.useDelimiter("\n");
    int N = sc.nextInt();
    sc.nextLine();

    String in1, in2;

    for(int i=0; i<N; i++)
    {
      in1 = sc.next();
      in2 = sc.next();
      // System.out.println("in1, in2 = " + in1 + ", " + in2);
      System.out.println(areMetaStrings(in1, in2));
    }

  }
}