import java.util.Arrays;

public class LongestIncreasingPathInMatrix
{
    public static int longestIncreasingPath(int [][] mat)
    {
        int numRows = mat.length;
        int numCols = mat[0].length;

        Node[] vals = new Node[numRows * numCols];
        int [][] paths = new int[numRows][numCols];

        for(int nR=0; nR < numRows; nR++)
        {
            for(int nC=0; nC < numCols; nC++)
            {
                vals[nR*numCols + nC] = new Node(mat[nR][nC], nR, nC);
                paths[nR][nC] = 1;
            }
        }
        Arrays.sort(vals);

        for(Node n: vals)
        {
            updateAdjacent(n, paths, mat);
        }

        int max = -1;

        for(int nR=0; nR < numRows; nR++)
        {
            for(int nC=0; nC < numCols; nC++)
            {
                if(paths[nR][nC] > max) max = paths[nR][nC];
            }
        }

        return max;

    }
    private static void updateAdjacent(Node n, int [][] paths, int[][] mat)
    {
        if(n.x -1 >= 0) updateAdjacent(n, n.x-1, n.y, paths, mat);
        if(n.y -1 >= 0) updateAdjacent(n, n.x, n.y-1, paths, mat);
        if(n.x +1 < paths.length) updateAdjacent(n, n.x+1, n.y, paths, mat);
        if(n.y +1 < paths[0].length) updateAdjacent(n, n.x, n.y+1, paths, mat);

    }
    private static void updateAdjacent(Node n, int tx, int ty, int [][] paths, int [][] mat)
    {
        if(mat[n.x][n.y] < mat[tx][ty])
        {
            if(paths[tx][ty] < paths[n.x][n.y] + 1)
            {
                paths[tx][ty] = paths[n.x][n.y] + 1;
            }
        }
    }


    private static class Node implements Comparable<Node>
    {
        private int val;
        private int x;
        private int y;
        private Node(int val, int x, int y)
        {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node that)
        {
            if(this.val < that.val) return -1;
            if(this.val > that.val) return +1;

            return 0;
        }
    }

    public static void main(String [] args)
    {
        runTest(new int [][]{{9,9,4},{6,6,8},{2,1,1}});
        runTest(new int [][]{{3,4,5},{3,2,6},{2,1,1}});
        runTest(new int [][]{{3,4,5},{3,2,6},{2,2,1}});
    }

    private static void runTest(int [][] mat)
    {
        System.out.printf("input matrix = \n");
        for(int i=0; i<mat.length; i++)
        {
            System.out.println(Arrays.toString(mat[i]));
        }
        System.out.printf("longest path in matrix = %d\n\n",
            LongestIncreasingPathInMatrix.longestIncreasingPath(mat));
    }
}