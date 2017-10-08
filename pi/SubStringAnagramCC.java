/*every time the window moves one char, only two values from count arrays need to be compared. 
cnt_x <- freq count of chars in x
cnt_y <- freq count of chars in y[0:len_x]
set_mis <- pairs that don't match.
if(set_mis.isEmpty()) return true;

len_x = x.length(), len_y = y.length()
for i=1:len_y - len_x
  
  cnt_y[y.charAt[i-1]]--; 
  cnt_y[]++;
  
  Given 2 words, return true if second word has a substring that is also an anagram of word 1. 
LGE , GOOGLE- True 
GEO, GOOGLE - False*/

import java.util.Arrays;

public class SubStringAnagramCC
{
  public static final int ALPHABET_SIZE = 256; // Assuming extended ascii.

  /* 
  x - shorter string
  y - longer string
  method to find a substring in y that is an anagram of x
  */
  public static boolean anagramSubstring(String x, String y)
  {
    // X = BX, Y = ABCDE
    if(x == null || y == null) throw new IllegalArgumentException();

  // 2, 5
    int lenX = x.length(), lenY = y.length();
    if(lenX == 0 || lenY < lenX) throw new IllegalArgumentException();

    int[] cntX = new int[ALPHABET_SIZE];
    int[] cntY = new int[ALPHABET_SIZE];

    getCnts(x, cntX);
    getCnts(y.substring(0, lenX), cntY);


    if(Arrays.equals(cntX, cntY)) return true;

    for(int iY = 1; iY <= lenY - lenX; iY++)
    {
      cntY[y.charAt(iY-1)]--;
      cntY[y.charAt(iY+lenX-1)]++;
      if(Arrays.equals(cntX, cntY)) return true;
    }

    return false;
  }

  private static void getCnts(String x, int [] cnt)
  {
    for(int i=0; i < cnt.length; i++) cnt[i] = 0;

    for(int i=0; i < x.length(); i++) cnt[x.charAt(i)]++;
  }

  public static void main(String [] args)
  {
    runTest("AB", "ABCDE");
    runTest("AX", "ABCDE");
    runTest("ED", "ABCDE");
    runTest("AA", "CXXXAA");
    runTest("lge", "google");
    runTest("geo", "google");
    try{ runTest("", "ABCDE"); } catch(Exception e) {e.printStackTrace();}
    try{ runTest("AB", ""); } catch(Exception e) {e.printStackTrace();};
  }
  private static void runTest(String x, String y)
  {
    System.out.printf("x = %s, y = %s, anagramSubString = ", x, y);
    System.out.printf("%b\n",SubStringAnagramCC.anagramSubstring(x, y));
  }
}

