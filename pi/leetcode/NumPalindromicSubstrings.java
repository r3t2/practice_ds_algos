/*647. Palindromic Substrings*/
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
*/

public class NumPalindromicSubstrings
{
    public static int countSubstrings(String s)
    {
        char [] a = s.toCharArray();
        int n = a.length;
        int cnt = 0;

        for(int i=0; i<2*n-1; i++)
        {
            cnt += numPalindromesAround(a, i/2, (i+1)/2);
        }

        return cnt;
    }

    private static int numPalindromesAround(char [] a, int lt, int rt)
    {
        int cnt=0;

        while(lt>=0 && rt<a.length && a[lt]==a[rt])
        {
            cnt++;
            lt--;
            rt++;
        }
        return cnt;
    }

    public static void main(String [] args)
    {
        runTest("abc");
        runTest("aba");
        runTest("aaa");
        runTest("");
        runTest("a");
        runTest("ababa");
    }

    private static void runTest(String s)
    {
        System.out.printf("num palindrome substrings of \"%s\"= %d\n", s, 
            NumPalindromicSubstrings.countSubstrings(s));
    }
}