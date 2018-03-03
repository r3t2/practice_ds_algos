import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
generate all permutations of a string with no duplicate chars
*/

class Ch08p07GenerateAllPermNoDup {
  
  public static List<String> allPerms(String input)
  {
    List<String> perms = new ArrayList<String> ();
    char [] str = input.toCharArray();
    
    boolean [] marked = new boolean[str.length];
    Arrays.fill(marked, false);
    
    StringBuilder sb = new StringBuilder();
    
    allPerms(str, perms, marked, sb);
    
    return perms;
    
  }
  private static void allPerms(char [] strArr, List<String> perms, boolean [] marked, StringBuilder sb)
  {
    if(sb.length() == strArr.length)
    {
      perms.add(sb.toString());
      return;
    }
    
    for(int i=0; i<strArr.length; i++)
    {
      if(marked[i] == true) continue;
      
      sb.append(strArr[i]);
      marked[i] = true;
      allPerms(strArr, perms, marked, sb);
      marked[i] = false;
      sb.deleteCharAt(sb.length()-1); // remove last char
    }
    
  }
  public static void main(String[] args) {
    runTest("abc");
    runTest("abcde");
  }
  private static void runTest(String x)
  {
    System.out.printf("input = %s\n", x);
    List<String> perms = allPerms(x);
    System.out.printf("number of permutations = %d\n, permutations = %s\n\n", perms.size(), perms.toString());
  }
}
