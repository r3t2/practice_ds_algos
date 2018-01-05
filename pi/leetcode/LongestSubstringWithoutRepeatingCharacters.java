public class LongestSubstringWithoutRepeatingCharacters
{
    public static int lengthOfLongestSubstring(String s)
    {
        int maxLen = 0;
        int st = 0;
        int end = 0;

        int [] cnt = new int [128];

        boolean isRepeat = false;

        while(st<=end && end<s.length())
        {
            while(!isRepeat && end < s.length())
            {
                char c = s.charAt(end);
                cnt[c] += 1;
                end++;
                if(cnt[c] > 1) isRepeat = true;
                else if(end - st > maxLen) maxLen = end-st;
            }

            while(isRepeat && st<end)
            {
                char c = s.charAt(st);
                cnt[c] -= 1;
                if(cnt[c] == 1) isRepeat = false;
                st++;
            }
        }
        return maxLen;
    }

    public static void main(String [] args)
    {
        runTest("abcabcdabc");
        runTest("abcdef");
        runTest("aaaa");
        runTest("a");
        runTest("");
    }

    private static void runTest(String s)
    {
        System.out.printf("input s = %s, longest substring length = %d\n", s, 
            LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));
    }
}