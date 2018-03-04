import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
/*
Given two input arrays, return true if the words array is sorted according to the ordering array Input: words = ['cc', 'cb', 'bb', 'ac'] ordering = ['c', 'b', 'a'] Output: True

Input: words = ['cc', 'cb', 'bb', 'ac'] ordering = ['b', 'c', 'a'] Output: False
*/
class CheckOrderOfWordArrGivenCharOrder {
  public static boolean inOrder(String [] words, char [] order)
  {
    if(order.length == 0) return false; // throw IllegalArgumentException
    if(words.length <= 1) return true;
    // words have a character not in order[] --> what should be the behavior?
    
    
    Map<Character, Integer> orderMap = new HashMap<>();
    for(int i=0; i<order.length; i++)
    {
      orderMap.put(order[i], i);
    }
    
    for(int i=0; i<words.length-1; i++)
    {
      if(!orderCmp(words[i], words[i+1], orderMap)) return false;
    }
    return true;
  }
  // check if s1 comes before s2
  private static boolean orderCmp(String s1, String s2, Map<Character, Integer> orderMap)
  {
   int i = 0;
    while(i<s1.length() && i<s2.length() && s1.charAt(i)==s2.charAt(i)) i++;
    
    if(i == s1.length()) return true;
    
    // additionally check if character is not found in order
    if(orderMap.get(s1.charAt(i)) < orderMap.get(s2.charAt(i)) ) return true;
    else return false;    
    
  }
  public static void main(String[] args) {
    runTest(new String[] {"cc", "cb", "ba"}, new char [] {'c','b','a'});
    runTest(new String[] {"bb", "cb", "ba"}, new char [] {'c','b','a'});
    runTest(new String[] {"ab", "bc", "ca"}, new char [] {'a','b','c'});
  }
  private static void runTest(String [] words, char [] order)
  {
    System.out.printf("words = %s\t", Arrays.toString(words));
    System.out.printf("order = %s\n", Arrays.toString(order));
    System.out.printf("in order = %b\n", inOrder(words, order));
  }
}
