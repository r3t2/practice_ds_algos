import java.util.*;

/*
paths in Binary Tree that matches a given sum. 
path needs to be on the path from root to leaf but don't have to start/end at root/leaf.
*/
public class Ch4p12PathsWithSum
{
    private Node root;
    public int numPathsWithSum(int T)
    {
        Map<Integer, Integer> cumSums = new HashSet<>();
        cumSums.put(0, 1);
        return numPathsWithSum(root, cumSums, 0, T);
    }

    private int numPathsWithSum(Node n, Set<Integer> cumSums, int S, int T)
    {
        if(n == null) return 0;

        int cnt = 0;
        cnt = cumSums.getOrDefault(S+n.key-T, 0);

        int sumCnt = cumSums.getOrDefault(S+n.key, 0);
        cumSums.put(S+n.key, sumCnt+1);

        cnt += numPathsWithSum(n.left, cumSums, S+n.key, T);
        cnt += numPathsWithSum(n.right, cumSums, S+n.key, T);

        if(sumCnt == 0) cumSums.remove(S+n.key);
        else cumSums.put(S+n.key, sumCnt);

        return cnt;
    }
}