#include <stdio.h>
#include <stdlib.h>

int bit(int x, int i)
{
    return ((x & (1<<i)) != 0);
}
int singleFlip(int x)
{
    int maxCnt = 0;
    int *xArry = calloc(32, sizeof(int));

    int i=0;
    xArry[0] = x & 0x1;

    for(i=1; i<32;i++)
    {
        if(bit(x, i) == 0)
        {   xArry[i] = 0; }
        else
        {   xArry[i] = xArry[i-1] + 1; }
    }

    if(xArry[31] == 0 && maxCnt < xArry[30] + 1)
    {
        maxCnt = xArry[30] + 1;
    }
    for(i=30; i>=0; i--)
    {
        if(xArry[i] != 0 && xArry[i+1] != 0)
        {
            xArry[i] = xArry[i+1];
        }

        else if((xArry[i] == 0) && (i-1 >= 0) && (maxCnt < xArry[i-1] + xArry[i+1] +1))
        {
            maxCnt = xArry[i-1] + xArry[i+1] +1;
        }
        else if((xArry[i] == 0) &&  (maxCnt < xArry[i+1] +1))
        {
            maxCnt = xArry[i+1] +1;
        }
    }




    return maxCnt;
}


void runTest(unsigned int x)
{
    printf("input = %08x", x);
    printf(" , consecutive ones after single flip = %d\n", singleFlip(x));
}


int main()
{
    runTest(0);
    runTest(0xFFFFFFFF);
    runTest(0xFFFFFFFF ^ (1<<16));
    runTest(0xFFFFFFFF ^ (1<<0));
    runTest(0xFFFFFFFF ^ (1<<31));
    runTest(0b0111001110111);
}

