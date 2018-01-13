class MissingNumberInArray {
    public int missingNumber(int[] nums) {
        if(nums == null) throw new NullPointerException();
        int n = nums.length;
        if(n==0) return 0;
        
        int S = n*(n+1)/2;
        for(int i: nums) S -= i;
        
        return S;
        
    }
}