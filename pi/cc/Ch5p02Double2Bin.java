import java.util.*;

public class Ch5p02Double2Bin
{
    public static String double2Bin(double d)
    {
        if(!(d>=0 && d<=1)) return null;

        StringBuilder sb = new StringBuilder();
        sb.append("0.");

        while(d != 0)
        {
            if(sb.length() > 66) return String.format("d = %s, binary so far = %s", Double.toHexString(d), sb.toString());
            d = 2*d;
            if(d>=1)
            {
                sb.append("1");
                d = d-1;
            }
            else sb.append("0");
            
        }

        return sb.toString();
    }

    public static void main(String [] args)
    {
        runTest(0.5);
        runTest(0.25);
        runTest(0.75);
        runTest(0.5 + 0.25 + 0.0625);

        Random rand = new Random();
        runTest(rand.nextDouble());
        runTest(rand.nextDouble());
        runTest(rand.nextDouble());
        runTest(rand.nextDouble());
        runTest(rand.nextDouble());
    }

    private static void runTest(double d)
    {
        System.out.printf("decimal = %f, %s, binary = %s\n", d, Double.toHexString(d), Ch5p02Double2Bin.double2Bin(d));
    }
}