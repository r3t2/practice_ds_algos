import java.util.List;
import java.util.ArrayList;

public class SpiralMatrix
{
    public static List<Integer> spiralOrder(int [][] order)
    {
        List<Integer> l = new ArrayList<Integer>();
        if (order == null) return null;

        int nRows = order.length;
        if(nRows == 0) return l;

        int nCols = order[0].length;

        int i=0, j=0;
        while(i<(nRows+1)/2 && j<(nCols+1)/2)
        {
            nextRing(   i, j, 
                        nRows-2*i, nCols-2*i,
                        order, l);
            i+=1;
            j+=1;
        }
        return l;
    }
    private static void nextRing(int rOfs, int cOfs, 
                                int nR, int nC, 
                                int[][] matrix, List<Integer> l)
    {
        int r = rOfs, c = cOfs;
        int r0 = r;
        if(nC == 1)
        {
            while(r<rOfs+nR)
            {
                l.add(matrix[r][c]);
                r++;
            }
            return;
        }
        while(c < cOfs + nC)
        {
            l.add(matrix[r][c]);
            c++;
        }

        r = rOfs+1; c = cOfs+nC-1;
        int c0 = c;
        while(r < rOfs + nR)
        {
            l.add(matrix[r][c]);
            r++;
        }

        r = rOfs + nR -1; c = cOfs + nC - 2;
        while(r0!=r && c >= cOfs)
        {
            l.add(matrix[r][c]);
            c--;
        }

        r = rOfs + nR - 2; c = cOfs;
        while(c0!=c && r > rOfs)
        {
            l.add(matrix[r][c]);
            r--;
        }
    }

    public static void main(String [] args)
    {
        runTest(new int [][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
        runTest(new int [][] {{1,2,3},{4,5,6},{7,8,9}});
        runTest(new int [][] {{}});
        runTest(null);
        runTest(new int [][] {{1,2,3,4},{5,6,7,8}});
        runTest(new int [][] {{1,2,3,4},{5,6,7,8}, {9,10,11,12}});
        runTest(new int [][] {{1,2,3,4}});
        runTest(new int [][] {{1},{2},{3},{4}});
        runTest(new int [][] {{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
        runTest(new int [][] {{1}});
    }

    private static void runTest(int [][] mat)
    {
        System.out.println("sprital output = " + SpiralMatrix.spiralOrder(mat));
    }
}