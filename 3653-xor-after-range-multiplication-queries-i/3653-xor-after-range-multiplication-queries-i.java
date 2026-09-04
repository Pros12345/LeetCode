class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long MOD = 1_000_000_007L;
        
        // Process each query explicitly
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            long v = query[3];
            
            int idx = l;
            while (idx <= r) {
                nums[idx] = (int) (((long) nums[idx] * v) % MOD);
                idx += k;
            }
        }
        
        // Compute the final XOR sum of all elements
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        
        return xorSum;
    }
}
