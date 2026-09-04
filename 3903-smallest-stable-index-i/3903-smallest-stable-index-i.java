class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffMin = new int[n];
        
        // Pass 1: Backward pass to find suffix minimums
        int minVal = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
            suffMin[i] = minVal;
        }
        
        // Pass 2: Forward pass to compute prefix maximums and check stability on the fly
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
            if ((long) maxVal - suffMin[i] <= k) {
                return i; // Returns immediately at the very first stable index
            }
        }
        
        return -1;
    }
}
