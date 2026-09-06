class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;
        
        // Step 1: Build the suffix minimum array
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
        
        // Step 2 & 3: Track prefix max from left to right and find the first stable index
        int prefixMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Check stability condition
            if (prefixMax - suffixMin[i] <= k) {
                return i; // Return immediately to guarantee the smallest index
            }
        }
        
        return -1;
    }
}
