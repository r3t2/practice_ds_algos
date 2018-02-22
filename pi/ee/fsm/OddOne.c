#include <stdio.h>

#define EVEN 0
#define ODD 1
struct state
{
    unsigned int state;
    unsigned int out;
    unsigned int next[2];
    unsigned int delay;
};
typedef struct state state;

const state fsm[2] = 
{
    {EVEN, 0, {0, 1}},
    {ODD , 1, {1, 0}}
};

unsigned int cState = EVEN; // current state is EVEN

int fsmRun(unsigned int in) // in may be 
{
    cState = fsm[cState].next[in];
    // delay 
    return fsm[cState].out; // this may be writing to a register for output
}

void runTest(int * inputs, int len)
{
    int i=0, out;
    for(; i<len; i++)
    {
        out = fsmRun(inputs[i]);
        printf("%d, ", out);
    }
    printf("\n");
}
int main(void)
{
    int inputs[10] = {1,1,1,1,1,1,1,1,1,1};
    runTest(inputs, 10);

    int inputs2[5] = {0,0,0,0,0};
    runTest(inputs2, 5);

    int inputs3[5] = {1,0,0,1,0};
    runTest(inputs3, 5);

    return 0;
}