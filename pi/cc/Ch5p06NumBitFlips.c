#include <stdio.h>
#include <stdlib.h>


int numFlips(int a, int b)
{
    int cnt = 0;
    int i;
    for(i=0; i<32; i++)
    {
        if((a&0x1) != (b&0x1)) cnt += 1;
        a = a>>1;
        b = b>>1;
    }

    return cnt;
}

int numFlipOpt(int a, int b)
{
    int c = a^b;
    int cnt = 0;
    while(c!=0)
    {
        cnt +=1;
        c = c & (c-1);
    }
    return cnt;
}

void runTest(int a, int b)
{
    printf("a = %08x, b = %08x, ", a, b);
    printf("numFlips = %02d, opt = %02d\n", numFlips(a,b), numFlipOpt(a,b));
}
int main(void)
{
    runTest(0xFFFFFFFF, 0x0);
    runTest(0xFFFFFFFF, 0xFFFFFFFE);
    runTest(0x7FFFFFFF, 0xFFFFFFFF);
    runTest(0x1000, 0x1001);
    runTest(0x0, 0x0);
}

