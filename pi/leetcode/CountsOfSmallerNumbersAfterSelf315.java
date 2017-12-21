/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class CountsOfSmallerNumbersAfterSelf315
{
    public static List<Integer>  countSmaller(int[] nums) {
        BST bst = new BST();
        int cntLessThan;

        LinkedList<Integer> sol = new LinkedList<Integer> ();

        for(int i=nums.length-1; i>=0; i--)
        {
            cntLessThan = bst.lessThan(nums[i]);
            bst.insert(nums[i]);
            sol.addFirst(cntLessThan);
        }

        return sol;
    }

    private static class BST
    {
        private Node root = null;

        private void insert(int k)
        {
            root = insert(root, k);
        }

        private Node insert(Node n, int k)
        {
            if(n == null) return new Node(k);

            if(k < n.key) n.left = insert(n.left, k);
            else n.right = insert(n.right, k);
            n.subtreeSize++;

            return n;
        }

        private int lessThan(int k)
        {
            return lessThan(root, k);
        }

        private int lessThan(Node n, int k)
        {
            if(n == null) return 0;

            if(k <= n.key) return lessThan(n.left, k);
            else
            {
                int rtRes = lessThan(n.right, k);
                int ltRes = (n.left == null) ? 0 : n.left.subtreeSize;
                return ltRes + rtRes + 1;
            }
        }
    }

    private static class Node
    {
        private Node left;
        private Node right;
        private int key;

        private int subtreeSize;
        private Node(int k)
        {
            this.key = k;
            this.left = this.right = null;
            this.subtreeSize = 1;
        }
    }

    public static void main(String [] args)
    {
        runTest(new int [] {5, 6, 9, 2, 1, 3});
        runTest(new int [] {5, 2, 6, 1});
        runTest(new int [] {1,2,3,4,5});
        runTest(new int [] {5,4,3,2,1});
        runTest(new int [] {-1,-1});
        runTest(new int [] {5, 6, 9, 2, 1, 1, 1, 3, 3});
    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("counts less than = " 
            + CountsOfSmallerNumbersAfterSelf315.countSmaller(x) + "\n");
    }
}