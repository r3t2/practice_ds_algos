import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Ch08p04AllSubsets {
  public static List<Set<Integer>> allSubsets(Set<Integer> set)
  {
    List<Set<Integer>> subsetsRec = new ArrayList<Set<Integer>> ();
    List<Integer> setAsList = new ArrayList<Integer>();
    
    for(int e: set) setAsList.add(e);
    
    subsetsRec.add(new HashSet<Integer>());
    allSubsetRec(setAsList, subsetsRec, 0);
    
    //return allSubsetIter(set);
    //return subsetsRec;
    return allSubsetBit(set);
  }
  
  private static void allSubsetRec(List<Integer> listSet, List<Set<Integer>> subsets, int idx)
  {
    if(idx == listSet.size()) return;
    
    List<Set<Integer>> newSubsets = new ArrayList<Set<Integer>>();
    
    int e = listSet.get(idx);
    for(Set<Integer> s: subsets)
    {
      Set<Integer> newS = new HashSet<Integer> (s);
      newS.add(e);
      newSubsets.add(newS);
    }
    
    subsets.addAll(subsets.size()-1, newSubsets);
    
    allSubsetRec(listSet, subsets, idx+1);
    
    
  }
  
  private static List<Set<Integer>> allSubsetIter(Set<Integer> set)
  {
    List<Set<Integer>> subsets = new ArrayList<Set<Integer>>();
    subsets.add(new HashSet<Integer>()); // add empty set
    
    // for each element in set
    for(int e: set)
    {
      List<Set<Integer>> newSubsets = new ArrayList<Set<Integer>>();
      for(Set<Integer> s: subsets)
      {
        Set<Integer> newS = new HashSet<Integer> (s);
        newS.add(e);
        newSubsets.add(newS);
      }
      subsets.addAll(subsets.size()-1, newSubsets);
    }
    return subsets;
  }
  
  private static List<Set<Integer>> allSubsetBit(Set<Integer> set)
  {
    List<Integer> setAsList = new ArrayList<Integer>();
    for(int e: set) setAsList.add(e);
    int N = setAsList.size();
    
    List<Set<Integer>> subsets = new ArrayList<Set<Integer>>();
    for(int i=0; i<Math.pow(2,N);i++)
    {
      subsets.add(int2Subset(i, setAsList));
    }
    
    return subsets;
  }
  
  private static Set<Integer> int2Subset(int i, List<Integer> setAsList)
  {
    Set<Integer> set = new HashSet<>();
    
    int cnt = 0;
    while(i!=0)
    {
      if(i%2 == 1) set.add(setAsList.get(cnt));
      i = i>>1;
      cnt +=1;
    }
    
    return set;
  }
  
  
  
  public static void main(String[] args) {
    runTest(new int [] {1,2,3,4});
    runTest(new int [] {});
    runTest(new int [] {1,2,3});
    runTest(new int [] {1});
  }
  
  private static void runTest(int [] x)
  {
    Set<Integer> s = new HashSet<Integer>();
    for(int i: x)
    {
      s.add(i);
    }
    List<Set<Integer>> subsets = allSubsets(s);
    for(Set<Integer> ss: subsets)
    {
      System.out.println(ss);
    }
    
    System.out.println();
  }
}
