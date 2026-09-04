class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] prefMax = new int[n];
        int[] suffMin = new int[n];
        
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, nums[i]);
            prefMax[i] = maxVal;
        }
        
        int minVal = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minVal = Math.min(minVal, nums[i]);
            suffMin[i] = minVal;
        }
        
        for (int i = 0; i < n; i++) {
            if ((long) prefMax[i] - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
