class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int currentDistance = Math.abs(i - start);
                minDistance = Math.min(minDistance, currentDistance);
                
                // Optimization: 0 is the minimum possible distance
                if (minDistance == 0) {
                    break;
                }
            }
        }
        
        return minDistance;
    }
}
