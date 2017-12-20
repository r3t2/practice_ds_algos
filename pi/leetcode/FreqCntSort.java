import java.util.Arrays;
import java.util.Comparator;

    /*
    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:

    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:

    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:

    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
    */
public class FreqCntSort
{
    

    public static String frequencySort(String s) {
        int [] cnt = new int[256];

        for(int i=0; i<s.length(); i++) cnt[i] = 0;
        for(int i=0; i<s.length(); i++) cnt[s.charAt(i)]++;

        Comparator<Character> comp = new FreqCntComparator(cnt);

        char [] temp = s.toCharArray();
        Character [] chars = new Character[temp.length];
        
        for(int i=0; i<temp.length; i++) chars[i] = temp[i];

        Arrays.sort(chars, comp);
        for(int i=0; i<chars.length;i++) temp[i] = chars[i];

        return new String(temp);
        
    }

    public static class FreqCntComparator implements Comparator<Character>
    {
        private int [] cnt;
        public FreqCntComparator(int [] cnt)
        {
            this.cnt = cnt;
        }

        public int compare(Character c1, Character c2)
        {
            char pc1 = c1, pc2 = c2;
            if(cnt[c1] < cnt[pc2]) return +1;
            if(cnt[c1] > cnt[pc2]) return -1;
            return c1.compareTo(c2);
        }
    }

    public static void main(String [] args)
    {
        runTest("abcdd");
        runTest("abcabc");
        runTest("aaabbb");
        runTest("ababab");
        runTest("");
        runTest("a");

    }

    private static void runTest(String s)
    {
        System.out.println("input  = " + s);
        System.out.println("sorted = " + FreqCntSort.frequencySort(s) + "\n");
    }
}