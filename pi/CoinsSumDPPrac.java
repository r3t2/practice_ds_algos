/* Given a list of N coins, their values (V1, V2, … , VN), and the total sum S. Find the minimum number of coins the sum of which is S (we can use as many coins of one type as we want), or report that it’s not possible to select coins in such a way that they sum up to S. */
import java.util.HashMap;
import java.util.Arrays;

public class CoinsSumDPPrac
{
    public static int findMinCoins(int V, int [] vals)
    {
        if(V < 0) throw new IllegalArgumentException();

        HashMap<Integer, Integer> solMap = new HashMap<Integer, Integer> ();

        return findMinCoins(V, vals, solMap);

    }

    private static int findMinCoins(int V, int [] vals, HashMap<Integer, Integer> solMap)
    {

        if(V == 0) return 0;

        if(solMap.get(V) != null) return solMap.get(V);

        int minCoins = Integer.MAX_VALUE;

        boolean hasSol = false;

        int temp;

        for(int val: vals)
        {
            if(V - val >= 0)
            {
                temp = findMinCoins(V - val, vals, solMap);
                if( (temp != -1) && (temp + 1 < minCoins)) minCoins = temp + 1;

                hasSol = hasSol | (temp != -1);
            }
        }

        if(hasSol == false) minCoins = -1;

        System.out.printf("put: %d, %d \n", V, minCoins);
        solMap.put(V, minCoins);

        return minCoins;
    }

    public static void main(String [] args)
    {
        runTest(5, new int[] {3, 2, 1});

        runTest(5, new int[] {2, 4});

        runTest(9, new int[] {1, 5});
    }

    private static void runTest(int V, int[] vals)
    {
        System.out.printf("V = %d, vals = %s\n", V, Arrays.toString(vals));
        System.out.println("min coins = " + CoinsSumDPPrac.findMinCoins(V, vals));
    }

}
