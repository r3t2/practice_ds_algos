class SumOfTwoIntNoPlusMinus {
    public static int getSum(int x, int y) {
        int [][][] c_lut = new int[][][] {{{0,0},{0,1}},{{0,1},{1,1}}};
        int [][][] s_lut = new int[][][] {{{0,1},{1,0}},{{1,0},{0,1}}};
        
        /*for(int c=0; c<2; c++)
        {
            for(int a=0; a<2; a++)
            {
                for(int b=0; b<2; b++)
                {
                    System.out.printf("%d %d %d | %d %d\n", c, a, b, c_lut[c][a][b], s_lut[c][a][b]);
                }
            }
        }*/
        //System.out.printf("\n");
        int c=0;
        int s=0;
        int sum=0;
        for(int i=0; i<32; i++)
        {
            int a = x & 0x1;
            int b = y & 0x1;
            int cPre = c;
            c = c_lut[cPre][a][b];
            s = s_lut[cPre][a][b];
            sum = setBit(sum, s, i);
            //System.out.printf("%d %d %d | %d %d\n", cPre, a, b, c, s);
            
            x = x>>1;
            y = y>>1;
        }
        //System.out.printf("\n");
        return sum;
    }
    
    private static int setBit(int x, int b, int pos)
    {
        // clear bit pos
        x = x & (~(1<<pos));
        // set bit pos to b
        x |= ((b & 0x1)<<pos);
        
        return x;
    }

    public static void main(String [] args)
    {
        runTest(1,2);
        runTest(2,3);
        runTest(-2,3);
        runTest(-2,1);
    }

    private static void runTest(int x, int y)
    {
        System.out.printf("x = %d, y = %d, x+y = %d\n", x, y, getSum(x,y));
    }
}