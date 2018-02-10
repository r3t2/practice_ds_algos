public class Ch1p5EditDistOne
{
    public static boolean oneEditDist(String a, String b)
    {
        if(Math.abs(a.length() - b.length()) > 1) return false;
        if(a.length() < b.length())
        {
            String t = a;
            a = b;
            b = t;
        }
        return oneEditDist(a, b, 0, 0, 0);
    }

    private static boolean oneEditDist(String l, String s, int li, int si, int miss)
    {
        while((li < l.length() && si < s.length()) && (l.charAt(li) == s.charAt(si)))
        {
            li++; si++;
        }
        if(li == l.length() || si == s.length()) return true;
        if(miss == 1) return false;

        boolean retReplace = oneEditDist(l, s, li+1, si+1, miss+1);
        boolean retDelete = oneEditDist(l, s, li+1, si, miss+1);

        return retReplace| retDelete;
    }

    public static void main(String [] args)
    {
        runTest("abc", "bc");
        runTest("abc", "ab");
        runTest("abc", "abd");
        runTest("abc", "xbd");
        runTest("abc", "axy");
        runTest("abc", "xya");
        runTest("abc", "xyabcd");
    }

    private static void runTest(String a, String b)
    {
        System.out.printf("a = %s, b = %s, oneEdit = %b\n", 
            a, b, Ch1p5EditDistOne.oneEditDist(a,b));
    }
}