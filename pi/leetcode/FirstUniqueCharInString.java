/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
*/
import java.util.*;
class FirstUniqueCharInString {
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> uniq = new HashMap<>();
        HashSet<Character> dup = new HashSet<>();
        TreeMap<Integer, Character> treeMap = new TreeMap<> ();


        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(dup.contains(c)) continue;
            if(uniq.containsKey(c))
            {
                int idx = uniq.remove(c);
                treeMap.remove(idx);
                dup.add(c);
            }
            else
            {
                uniq.put(c, i);
                treeMap.put(i, c);
            }

        }
        if(treeMap.isEmpty()) return -1;
        else return treeMap.firstEntry().getKey();
              
    }

    /*private static class PQNode implements Comparable<PQNode>
    {
        private Character char;
        private int index;

        public int compareTo(PQNode that)
        {
            if(this.index < that.index) return -1;
            if(this.index > that.index) return +1;
            return 0;
        }

        public boolean equals(Object o)
        {
            if(o==null) return false;
        }
    }*/

    public static void main(String [] args)
    {
        runTest("leetcode");
        runTest("loveleetcode");
        runTest("");
        runTest("aabbccdd");
    }
    private static void runTest(String s)
    {
        System.out.printf("input = %s, first unique char idx = %d\n", s,
                                                                    firstUniqChar(s));
    }
}