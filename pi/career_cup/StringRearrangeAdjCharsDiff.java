/*
https://careercup.com/question?id=6097445623693312

Given a string, check if it is can be reorganized such that the same char is not next to each other, If possible, output a possible result 
example 
input: google 
one possible output: gogole
*/
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class StringRearrangeAdjCharsDiff
{
    public static int nonDPcnt = 0;
    public static int dPcnt = 0;
    public static String rearrange(String s, boolean dpEn)
    {
        Map<Character, Integer> cntMap = new HashMap<Character, Integer> ();

        for(int i=0; i<s.length(); i++)
        {
            Character c = new Character(s.charAt(i));
            if(cntMap.containsKey(c))
            {
                int cnt = cntMap.get(c);
                cntMap.put(c, ++cnt);
            }
            else
            {
                cntMap.put(c, 1);
            }
        }

        HashSet<Integer> dp = new HashSet<Integer> ();
        StringBuilder sb = new StringBuilder ();
        return rearrange(sb, cntMap, dp, dpEn);
    }
    private static int hashCode(char c, Map<Character, Integer> map)
    {
        int sum = c*127;
        for(Map.Entry<Character, Integer> entry: map.entrySet())
        {
            sum = 127*127*sum + 127*entry.getKey() + entry.getValue();
        }
        return sum;
    }
    private static String rearrange(StringBuilder sb, Map<Character, Integer> cntMap,
        HashSet<Integer> dp, boolean dpEn)
    {
        if(!dpEn) nonDPcnt++;
        else dPcnt++;

        // System.out.println(sb);
        // System.out.println(cntMap);

        int zeros = 0;
        for(Map.Entry<Character, Integer> entry: cntMap.entrySet())
        {
            if(entry.getValue() == 0) zeros+=1;
        }
        if(zeros == cntMap.size()) return sb.toString();

        // if we used up all the characters, return sb.toString()
        char lastChar = '\0';

        int n = sb.length();
        if(n != 0) lastChar = sb.charAt(n-1);

        int hash = hashCode(lastChar, cntMap);
        if(dp.contains(hash)) return "";

        for(Map.Entry<Character, Integer> entry: cntMap.entrySet())
        {
            char c = entry.getKey().charValue();
            int val = entry.getValue();
            if(val == 0) continue;

            if(n == 0 || lastChar != c)
            {
                sb.append(c);
                entry.setValue(val-1);
                String ret = rearrange(sb, cntMap, dp, dpEn);
                if (ret.length() != 0) return ret;
                
                sb.deleteCharAt(n);
                entry.setValue(val);
            }

        }
        dp.add(hash);
        return "";
    }

    public static void main(String [] args)
    {
        runTest("abcd");
        runTest("aaad");
        runTest("aaaabbbbccccdddd");
        runTest("aabbbbccdddd");
        runTest("abcedddd");
        runTest("zzzzzzzzabcdefgh");
    }

    private static void runTest(String s)
    {
        System.out.printf("input = %s, rearrange = %s, rearrange DP = %s, non DP cnt = %d, DP cnt = %d \n\n", 
            s, 
            StringRearrangeAdjCharsDiff.rearrange(s, false),
            StringRearrangeAdjCharsDiff.rearrange(s, true),
            StringRearrangeAdjCharsDiff.nonDPcnt,
            StringRearrangeAdjCharsDiff.dPcnt);
    }
}