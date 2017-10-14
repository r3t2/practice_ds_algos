/*
Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
Insert
Remove
Replace
*/
public class EditDistanceDPPrac
{
  public static int editDistance(String x, String y)
  {
    if(x == null || y == null) throw new IllegalArgumentException();

    int nx = x.length(), ny = y.length();
    int [][] dp = new int[nx+1][ny+1];

    /* idx pos 0 corresponds to empty string*/
    for(int ix = 0; ix <= nx; ix++) dp[ix][0] = ix;
    for(int iy = 0; iy <= ny; iy++) dp[0][iy] = iy;

    char cx, cy;
    for(int ix = 1; ix <= nx; ix++)
    {
      for(int iy = 1; iy <= ny; iy++)
      {
        cx = x.charAt(ix-1);
        cy = y.charAt(iy-1);

        if(cx == cy) dp[ix][iy] = dp[ix-1][iy-1];
        else
        {
          dp[ix][iy] = Math.min(dp[ix-1][iy-1] + 1, dp[ix][iy-1] + 1);
          dp[ix][iy] = Math.min(dp[ix][iy], dp[ix-1][iy] + 1);
        }
      }

    }
    return dp[nx][ny];

  }

  public static void main(String[] args)
  {
    runTest("", "");
    runTest("A", "");
    runTest("", "abcd");
    runTest("abcd", "xyzw");
    runTest("abcd", "abccccccd");
  }

  private static void runTest(String x, String y)
  {
    System.out.printf("x = %s, y = %s, editDist = %d\n", 
      x, y, EditDistanceDPPrac.editDistance(x,y));
  }

}
