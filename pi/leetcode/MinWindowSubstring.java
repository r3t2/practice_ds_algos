/*
76. Minimum Window Substring
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/
public class MinWindowSubstring
{
    public static String minWindow(String s, String t)
    {
        if(s == null || t == null) return null;

        if(t.length() > s.length()) return "";

        if(t.length() == 0 || s.length() == 0) return "";

        int start = 0;
        int end = t.length();
        
        int [] sCnt = new int[128];
        int [] tCnt = new int[128];

        for(int i=0; i<end; i++)
        {
            sCnt[s.charAt(i)] += 1;
            tCnt[t.charAt(i)] += 1;
        }

        int minIdx = -1;
        int minLen = Integer.MAX_VALUE;

        do
        {
            while(end<s.length() && !contains(sCnt, tCnt))
            {
                sCnt[s.charAt(end)] += 1;
                end++;
            }

            while(start<end && contains(sCnt, tCnt))
            {
                if(end-start < minLen)
                {
                    minLen = end-start;
                    minIdx = start;
                }
                sCnt[s.charAt(start)] -= 1;
                start++;
            }
        }while(end<s.length());

        if(minIdx == -1) return "";
        else return s.substring(minIdx, minIdx+minLen);
    }

    private static boolean contains(int [] sCnt, int [] tCnt)
    {
        for(int i=0; i<sCnt.length; i++)
        {
            if(tCnt[i] > sCnt[i]) return false;
        }

        return true;
    }


    public static void main(String [] args)
    {
        runTest("ADOBEBANCCODE", "ABC");
        runTest("ADOBEBANCCODEACB", "ABC");
        runTest("XYZ", "ABC");
        runTest("XYZ", "");
        runTest("", "ABC");
        runTest("", "");
        runTest("a", "a");
        runTest("abc", "cba");
    }

    private static void runTest(String s, String t)
    {
        System.out.printf("s = %-20s, t = %-5s, min window substring = %s\n", s, t, 
            MinWindowSubstring.minWindow(s, t));
    }
}