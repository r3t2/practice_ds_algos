/*
LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.

*/


public class LongestCommonSubseqDPPrac
{
    public static String longestCommonSubseqDP(String X, String Y)
    {
        if(X == null || Y == null) throw new IllegalArgumentException();

        int nX = X.length(), nY = Y.length();

        if(nX == 0 || nY == 0) return "";

        int [][] sol = new int [nX][nY];

        int [][] px = new int [nX][nY], py = new int [nX][nY];

        for(int iX = 0; iX < nX; iX++)
        {
            for(int iY = 0; iY < nY; iY++)
            {
                if(iX == 0 || iY == 0)
                {
                    if(X.charAt(iX) == Y.charAt(iY))
                    {
                        sol[iX][iY] = 1;
                        px[iX][iY] = iX-1; py[iX][iY] = iY-1;
                    }
                    else
                    {
                        sol[iX][iY] = 0;
                        if(iX == 0) { px[iX][iY] = iX; py[iX][iY] = iY-1; }
                        else { px[iX][iY] = iX-1; py[iX][iY] = iY; }
                    }

                }
                else
                {
                    if(X.charAt(iX) == Y.charAt(iY))
                    {
                        sol[iX][iY] = sol[iX-1][iY-1] + 1;
                        px[iX][iY] = iX-1;
                        py[iX][iY] = iY-1;
                    }
                    else
                    {
                        sol[iX][iY] = Math.max(sol[iX-1][iY], sol[iX][iY-1]);
                        px[iX][iY] = sol[iX-1][iY] >= sol[iX][iY-1] ? iX-1 : iX;
                        py[iX][iY] = sol[iX-1][iY] >= sol[iX][iY-1] ? iY : iY-1;
                    }

                }

            }
        }

        int iX = nX-1, iY = nY-1;
        int tiX, tiY;
        int cnt = sol[iX][iY];
        StringBuilder sb = new StringBuilder(cnt);
        sb.setLength(cnt);

        //System.out.println("cnt = " + cnt);

        while(iX !=-1 && iY!=-1)
        {
            if(px[iX][iY] == iX-1 && py[iX][iY] == iY-1)
            {
                cnt--;
                sb.setCharAt(cnt, X.charAt(iX));    
            }
                tiX = px[iX][iY]; 
                tiY = py[iX][iY];
                iX = tiX;
                iY = tiY;
        }

        return sb.toString();


    }

    public static void main(String [] args)
    {
        runTest("abcdhelloxyz", "mnhellow");

        runTest("GeeksforGeeks", "GeeksQuiz");

        runTest("axbyczd", "xyzabcd");

        runTest("ahhbcedlelo", "xyhzewlxlyoz");

        //runTest("axbyczd", "awbcd");

        runTest("abcd", "efgh");

        runTest("abcd", "abcd");

        runTest("a", "a");

        runTest("a", "b");

        runTest("", "b");

        runTest("", "");
    }

    private static void runTest(String X, String Y)
    {
        System.out.printf("str1 = %s, str2 = %s \n", X, Y);
        System.out.printf("lcSubSeq = %s\n", LongestCommonSubseqDPPrac.longestCommonSubseqDP(X,Y));
    }


}



