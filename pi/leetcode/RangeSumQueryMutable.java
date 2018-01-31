import java.util.*;

public class RangeSumQueryMutable
{
    private int [] segmentTree;
    private int [] nums;
    private int NhPow2;

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

        for(int i=M-NhPow2, j=0; i<M; i++, j++)
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
        nums[i] = val; // unneccary operation

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
        return 0;
    }
}
