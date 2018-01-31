import java.util.*;

public class RangeSumQueryMutable
{
    private int [] segmentTree;
    private int [] nums;
    private int NhPow2;
    private static final int ROOT = 0;

    public RangeSumQueryMutable(int[] nums) {
        if(nums == null) throw new NullPointerException();
        this.nums = nums;
        if(nums.length == 0) return;

        int N = nums.length;
        int Nlog2Ceil = (int) Math.ceil(Math.log(N)/ Math.log(2));
        Nlog2Ceil = Math.max(Nlog2Ceil, 1);
        
        this.NhPow2 = (1 << Nlog2Ceil);
        int M = (1 << (Nlog2Ceil + 1)) - 1;

        segmentTree = new int[M];
        Arrays.fill(segmentTree, 0);

        for(int i=M-NhPow2, j=0; j<N; i++, j++)
        {
            segmentTree[i] = nums[j];
        }

        for(int i=M-NhPow2-1; i>=0; i--)
        {
            segmentTree[i] = segmentTree[2*i+1] + segmentTree[2*i+2];
        }
    }
    
    public void update(int i, int val) {
        int M = segmentTree.length;
        nums[i] = val; // unnecessary operation

        segmentTree[M-NhPow2+i] = val;
        int p = parent(M-NhPow2+i);
        do
        {
            segmentTree[p] = segmentTree[2*p+1] + segmentTree[2*p+2];
            p = parent(p);
        }while(p!=0);
    }

    private int parent(int i)
    {
        return (i-1)/2;
    }
    
    public int sumRange(int i, int j) {
        return sumRange(ROOT, 0, NhPow2-1, i, j);
    }

    private int sumRange(int node, int rangeLow, int rangeHigh, int qLow, int qHigh)
    {
        //If full overlap, return the computed sum for that range
        if(rangeLow >= qLow && rangeHigh <= qHigh) return segmentTree[node];

        //if no overlap, return 0
        if(rangeHigh < qLow || rangeLow > qHigh) return 0;

        int mid = (rangeLow + rangeHigh)/2;
        int lSum = sumRange(2*node+1, rangeLow, mid, qLow, qHigh);
        int rSum = sumRange(2*node+2, mid+1, rangeHigh, qLow, qHigh);
        return lSum + rSum;
    }

    public static void main(String [] args)
    {
        int [] nums = new int[]{4,3,2,5,6};
        RangeSumQueryMutable r = new RangeSumQueryMutable(nums);
        System.out.println(Arrays.toString(nums));
        int i, j;
        i=0; j=3; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=4; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=0; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=3; j=3; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=1; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=2; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));

        r.update(0, -4);
        r.update(4, -6);

        System.out.println("\n"+Arrays.toString(nums));
        i=0; j=3; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=4; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=0; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=3; j=3; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=1; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        i=0; j=2; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));        

        nums = new int [] {3};
        r = new RangeSumQueryMutable(nums);
        System.out.println("\n"+Arrays.toString(nums));
        i=0; j=0; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));
        r.update(0, 5);
        i=0; j=0; System.out.printf("i = %d, j= %d, sum = %d\n", i, j, r.sumRange(i, j));

        nums = new int [] {};
        r = new RangeSumQueryMutable(nums);
        System.out.println("\n"+Arrays.toString(nums));


    }
}
