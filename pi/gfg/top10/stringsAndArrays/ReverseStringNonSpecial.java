
public class ReverseStringNonSpecial
{

    public static String reverseNonSpecial(String x)
    {
        if (x == null) throw new IllegalArgumentException("input String is null");

        int len = x.length();

        int ltPtr = 0, rtPtr = len-1;

        char [] xArry = x.toCharArray();

        while(true)
        {
            while(ltPtr < len && isSpecial(xArry[ltPtr]) == true) ltPtr ++;
            while(rtPtr >= 0 && isSpecial(xArry[rtPtr]) == true) rtPtr--;

            if(ltPtr >= rtPtr || ltPtr == len || rtPtr == -1) break;

            swap(xArry, ltPtr, rtPtr);
            ltPtr++;
            rtPtr--;
        }

        return new String(xArry);
    }

    private static void swap(char [] x, int i, int j)
    {
        char temp = x[j];
        x[j] = x[i];
        x[i] = temp;
    }

    public static boolean isSpecial(char x)
    {
        if( (x >= '0' && x <= '9') || 
            (x >= 'A' && x <= 'Z') || 
            (x >= 'a' && x <= 'z') ) 
            return false;
        else return true;
    }

    public static void main(String [] args)
    {
        runTest("abcd");
        runTest("abc");
        runTest("a");
        runTest("");
        runTest("$#%^");
        runTest("$a#%b^");
        runTest("c$a#%b^d");
        runTest("abc$%^def");
        runTest("#$%abcdef$%^");
    }

    private static void runTest(String x)
    {
        System.out.println("input = " + x);
        System.out.println("input = " + ReverseStringNonSpecial.reverseNonSpecial(x) + "\n");
    }
}