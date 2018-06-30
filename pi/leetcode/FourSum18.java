import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


public class FourSum18
{
  private static Set<List<Integer>> getUniqueIdxs(List<TwoTuple> ab, List<TwoTuple> cd, int[] nums)
  {
    Set<List<Integer>> sol = new HashSet<List<Integer>> ();
    for(TwoTuple abIdx : ab)
    {
      for(TwoTuple cdIdx : cd)
      {
        if(abIdx.contains(cdIdx.i1) || abIdx.contains(cdIdx.i2)) continue;
        List<Integer> sortedIdxs = Arrays.asList(nums[abIdx.i1], nums[abIdx.i2], nums[cdIdx.i1], nums[cdIdx.i2]);
        Collections.sort(sortedIdxs);
        sol.add(sortedIdxs);
      }
    }

    return sol;
  }
  public static List<List<Integer>> fourSum(int [] nums, int target)
  {
    if(nums == null) return null;
    HashMap<Integer, List<TwoTuple>> allTwoSums = new HashMap<>();

    for(int i1=0; i1<nums.length; i1++)
    {
      for(int i2=i1+1; i2<nums.length; i2++)
      {
        int twoSum = nums[i1] + nums[i2];
        List<TwoTuple> tupleList = allTwoSums.get(twoSum);
        if(tupleList == null) tupleList = new ArrayList<TwoTuple>();
        tupleList.add(new TwoTuple(i1,i2));

        allTwoSums.put(twoSum, tupleList);
      }
    }

    Set<List<Integer>> fourSumSolIdx = new HashSet<List<Integer>> ();
    for(int twoSum : allTwoSums.keySet())
    {
      int otherTwoSum = target - twoSum;
      // System.out.println(twoSum + ", " + otherTwoSum);
      if(!allTwoSums.containsKey(otherTwoSum)) continue;

      List<TwoTuple> abIdxs = allTwoSums.get(twoSum);
      List<TwoTuple> cdIdxs = allTwoSums.get(otherTwoSum);

      // System.out.println(abIdxs);
      // System.out.println(cdIdxs);
      Set<List<Integer>> sol = getUniqueIdxs(abIdxs, cdIdxs, nums);
      for(List<Integer> l : sol)
      {
        fourSumSolIdx.add(l);
      }
    }

    List<List<Integer>> fourSumSol = new ArrayList<List<Integer>>();
    for(List<Integer> idxs : fourSumSolIdx)
    {
      /*List<Integer> sol = new ArrayList<Integer>();
      for(int i: idxs)
      {
        sol.add(i);
      }
      fourSumSol.add(sol);*/
      fourSumSol.add(idxs);
    }

    return fourSumSol;
  }
  public static void main(String [] args)
  {
    unitTest(new int[] {1,2,3,4,5}, 10);
    unitTest(new int[] {1, 0, -1, 0, -2, 2}, 0);
    unitTest(new int[] {-3, -2, -1, 0, 0, 1, 2, 3}, 0);
  }

  private static void unitTest(int [] nums, int target)
  {
    System.out.println(String.format("target = %d, nums = %s", target, Arrays.toString(nums)));
    List<List<Integer>> sols = fourSum(nums, target);
    System.out.println("sols = ");
    for(List<Integer> sol: sols)
    {
      System.out.println(sol);
    }
  }

  private static class TwoTuple
  {
    private int i1, i2;
    public TwoTuple(int i1, int i2)
    {
      this.i1 = i1<i2?i1:i2;
      this.i2 = i1<i2?i2:i1;
    }
    public boolean contains(int i)
    {
      if(this.i1 == i) return true;
      if(this.i2 == i) return true;
      return false;
    }
    public boolean equals(Object o)
    {
      if(o == null) return false;
      if(o == this) return true;

      if(o.getClass() != this.getClass()) return false;

      TwoTuple that = (TwoTuple) o;

      if(that.i1 != this.i1) return false;
      if(that.i2 != this.i2) return false;

      return true;
    }

    public String toString()
    {
      return "("+ this.i1 + ", " + this.i2 + ")";
    }
  }
}