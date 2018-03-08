#include <stdio.h>

// possibility: int getSum(int a,int b, int *out)
// set bit i to b in in[0]
void setbit(int *in, int b, int i)
{
    // clear bit at i;
    *in = (*in) & (~(1<<i));
    // set bit at i to b
    *in = (*in) | ((b & 0x1)<<i);
}
int getSum(int x, int y)
{
    int c_lut[2][2][2] = {{{0,0},{0,1}},{{0,1},{1,1}}};
    int s_lut[2][2][2] = {{{0,1},{1,0}},{{1,0},{0,1}}};

    //printf("c a b | c_lut s_lut\n");
    int c, a, b, s;
    /*for(c=0; c<2; c++)
    {
        for(a =0; a<2; a++)
        {
            for(b = 0; b<2; b++)
            {
                printf("%d %d %d | %d   %d\n", c, a, b, 
                                             c_lut[c][a][b], 
                                             s_lut[c][a][b]);
            }
        }
    }*/
    int i;
    //int c=0, a, b, s;
    int sum;
    c = 0;
    int c_pre;
    for(i=0; i<32; i++)
    {
        a = x & 0x1;
        b = y & 0x1;
        c_pre = c;
        //printf("i= %d %d %d %d | ", i, c, a, b);
        c = c_lut[c_pre][a][b];
        s = s_lut[c_pre][a][b];
        setbit(&sum, s, i);
        //printf("%d %d\n", c, s);


        x = x >> 1;
        y = y >> 1;
    }

    return sum;
}

void runTest(int x, int y)
{
    printf("x = %08x, y = %08x, sum = %08x\n", x, y, getSum(x,y));
}
int main(void)
{
    runTest(1,0);
    runTest(1,2);
    runTest(2,3);
    runTest(-1,2);
    runTest(-2,1);
    runTest(-2,2);
}