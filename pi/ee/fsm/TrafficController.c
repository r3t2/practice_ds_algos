#include <stdio.h>

#define EAST 0
#define NORTH 1

#define RED 0
#define AMBER 1
#define GREEN 2

#define GO_NORTH 0
#define WAIT_NORTH 1
#define GO_EAST 2
#define WAIT_EAST 3
#define ADDRESS_OF_TIMER 0x123456
#define SYS_TIMER (*((volatile unsigned int *) ADDRESS_OF_TIMER))
#define CAR_E (*(volatile unsigned int *) 0x1234)
#define CAR_N (*(volatile unsigned int *) 0x3456)

typedef struct state
{
    unsigned int outE;
    unsigned int outN;
    unsigned int delay;
    unsigned int next[2][2];
} state;

const state fsm[4] = 
{
    //outE, outN, delay, cECN{00, 01, 10, 11}
    {RED, GREEN, 30, {GO_NORTH, GO_NORTH,   WAIT_NORTH, WAIT_NORTH}}, // GO_NORTH
    {RED, AMBER, 5,  {GO_EAST,  GO_EAST,    GO_EAST,    GO_EAST}}, // WAIT_NORTH
    {GREEN, RED, 30, {GO_EAST,  WAIT_EAST,  GO_EAST,    WAIT_EAST}}, // GO_EAST
    {AMBER, RED, 5,  {GO_NORTH, GO_NORTH,   WAIT_NORTH, WAIT_NORTH}}, // WAIT_EAST
};

unsigned int cState = GO_NORTH;

void fsmRun(unsigned int carE, unsigned int carN)
{
    cState = fsm[cState].next[carE][carN];
    // delay(cState)
    // writeToOutputRegister(outE, outN);
    printf("%d, %d\n", fsm[cState].outE, fsm[cState].outN); 
}

void fsmDriver()
{
    while(1)
    {
        SYS_TIMER = 1000; // units to be determined
        //set system timer.
        while(SYS_TIMER != 0);
        //
        fsmRun(CAR_E, CAR_N);
    }
}

int main(void)
{
    //initialization()
    //fsmDriver()
    return 0;
}