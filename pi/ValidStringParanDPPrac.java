import java.util.Arrays;

public class ValidStringParanDPPrac
{
  public static boolean isValid(String x)
  {
    if(x==null) throw new IllegalArgumentException();
    
    int N = x.length();

    if(N == 0) return true;

    int [][] f = new int [N+1][N+1];

    for(int i=0; i<N+1; i++)
    {
      for(int j=0; j<N+1; j++)
      {
        f[i][j] = -1; // indicates not reacheable.
      }
    }
    
    f[0][0] = 0;
    char c;

    for(int nC=0; nC<N; nC++)
    {
      c = x.charAt(nC);
      
      if((c != '(') && (c != ')') && (c != '*')) throw new IllegalArgumentException();

      for(int nR = 0; nR <N+1; nR++)
      {
        if(f[nR][nC] == -1) continue;

        if(c == '(' || c == '*') f[nR+1][nC+1] = f[nR][nC] + 1;
        if((c == ')' || c == '*') && ( (nR-1)>=0) ) f[nR-1][nC+1] = f[nR][nC] -1;
        if(c == '*') f[nR][nC+1] = f[nR][nC];
      }
    }
  
    //printMat(f);

    return f[0][N] == 0;
  }

  public static void printMat(int [][] f)
  {
    for(int i=0; i<f.length; i++)
    {
      System.out.println(Arrays.toString(f[i]));
    }
  }

  public static void main(String [] args)
  {
    runTest("(*)");
    runTest("(*)(*)(**");
    runTest("***");
    runTest(")(");
    runTest("(*)(*)(**)");
    runTest("((())");
  }

  private static void runTest(String x)
  {
    System.out.println(x + " :: " + ValidStringParanDPPrac.isValid(x));
  }
}