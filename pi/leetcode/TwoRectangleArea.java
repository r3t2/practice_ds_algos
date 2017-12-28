public class TwoRectangleArea
{
    public static int computeArea(  int r1l, int r1b, int r1r, int r1t,
                                    int r2l, int r2b, int r2r, int r2t)
    {
        int ril = Math.max(r1l, r2l);
        int rir = Math.min(r1r, r2r);
        int rib = Math.max(r1b, r2b);
        int rit = Math.min(r1t, r2t);

        int a1 = area(r1l, r1b, r1r, r1t);
        int a2 = area(r2l, r2b, r2r, r2t);
        int ai = area(ril, rib, rir, rit);

        return a1 + a2 - ai;
    }

    private static int area(int l, int b, int r, int t)
    {
        if(r <= l || t <= b) return 0;

        return (r-l) * (t-b);
    }

    public static void main(String [] args)
    {
        runTest(0, 0, 1, 1, 1, 1, 2, 2);
        runTest(0, 0, 2, 2, 1, 1, 3, 3);
        runTest(0, 0, 3, 3, 1, 1, 2, 2);
    }

    private static void runTest(int r1l, int r1b, int r1r, int r1t,
                                int r2l, int r2b, int r2r, int r2t)
    {
        System.out.printf("r1l = %d, r1b = %d, r1r = %d, r1t = %d\n", r1l, r1b, r1r, r1t);
        System.out.printf("r2l = %d, r2b = %d, r2r = %d, r2t = %d\n", r2l, r2b, r2r, r2t);
        System.out.printf("area = %d\n\n", TwoRectangleArea.computeArea(r1l, r1b, r1r, r1t, r2l, r2b, r2r, r2t));
    }
}