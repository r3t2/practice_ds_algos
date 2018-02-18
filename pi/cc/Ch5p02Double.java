public class Ch5p02Double
{
    /* experiment with binary representation for doubles */
    private static long SIGN_MASK = (1L << 63);
    private static long MANTISSA_MASK = (1L << 52)-1;
    private static long EXPONENT_MASK = (-1L) & (~SIGN_MASK) & (~MANTISSA_MASK);

    public static void main(String [] args)
    {
        System.out.printf("SIGN_MASK        = 0x%016x\n", SIGN_MASK);
        System.out.printf("MANTISSA_MASK    = 0x%016x\n", MANTISSA_MASK);
        System.out.printf("EXPONENT_MASK    = 0x%016x\n", EXPONENT_MASK);

        runTest(1.0);
        runTest(2.0);
        runTest(3.0);
        runTest(4.0);
        runTest(0.5);
        runTest(0.25);
        runTest(0.125);
        runTest(0.0625);
        runTest(Double.NaN);
    }

    private static long extractSign(double x)
    {
        return (Double.doubleToLongBits(x) & SIGN_MASK) >> 63;
    }
    private static long extractExp(double x)
    {
        return (Double.doubleToLongBits(x) & EXPONENT_MASK) >> 52;
    }
    private static long extractMantissa(double x)
    {
        return (Double.doubleToLongBits(x) & MANTISSA_MASK);
    }
    private static void runTest(double x)
    {   
        System.out.printf(" input = %f\n", x);
        System.out.printf("    hexString                    = %s\n", Double.toHexString(x));
        System.out.printf("    binary representation of %f  = %08x\n", x, Double.doubleToLongBits(x));
        System.out.printf("raw binary representation of %f  = %08x\n", x, Double.doubleToRawLongBits(x)); 
        System.out.printf("sign                             = %d\n", extractSign(x));
        System.out.printf("exponent                         = %d\n", extractExp(x));
        System.out.printf("mantissa                         = %d\n", extractMantissa(x));

        System.out.printf("\n");
    }

}