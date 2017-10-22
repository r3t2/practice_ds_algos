/*
Dynamic Programming | Set 31 (Optimal Strategy for a Game)
Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.
Note: The opponent is as clever as the user.


*/

import java.util.Arrays;


public class PickFromArrayTwoPlayerGameDPPrac
{
    /**/
    public static int maxPossible(int [] arr)
    {
        if(arr == null) throw new IllegalArgumentException();

        int N = arr.length;
        int [][][] scratch = new int[N][N][];
        int [] sol = maxPossible(arr, scratch, 0, arr.length-1, 0);

        return sol[0];
    }

    private static void printScratch(int [][][] scratch)
    {
        int N = scratch.length;
        for(int r = 0; r < N; r++)
        {
            for(int c = 0; c < N; c++)
            {
                System.out.printf("(r=%d, c=%d), sol=%s  ", r, c, Arrays.toString(scratch[r][c]));
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }


    private static int [] maxPossible(int [] arr, int[][][] scratch, int st, int end, int player)
    {

        if(st > end) throw new IllegalArgumentException("st > end" + st + "  " + end);

        if(scratch[st][end] != null) return Arrays.copyOf(scratch[st][end], 2);

        int [] sol; 
        int [] solSt, solEnd;

        int max;

        if(st == end) {sol = new int [] {0, 0}; sol[player] = arr[st];}
        else
        {
            solSt = maxPossible(arr, scratch, st+1, end, 1-player);
            solEnd = maxPossible(arr, scratch, st, end-1, 1-player);

            if(solSt[player] + arr[st] > solEnd[player] + arr[end])
            {
                sol = solSt;
                sol[player] = solSt[player] + arr[st];
            }
            else
            {
                sol = solEnd;
                sol[player] = solEnd[player] + arr[end];
            }
            
        }
        scratch[st][end] = sol;

        // System.out.printf("st = %d, end = %d\n", st, end);
        // printScratch(scratch);

        return Arrays.copyOf(sol, 2);
    }
    
    public static void main(String [] args)
    {
        runTest(new int [] {1,3,15,5});
        runTest(new int [] {15, 5, 1, 18});

    }

    private static void runTest(int [] arr)
    {
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("max possible for player 0 = " + PickFromArrayTwoPlayerGameDPPrac.maxPossible(arr));
    }

}


