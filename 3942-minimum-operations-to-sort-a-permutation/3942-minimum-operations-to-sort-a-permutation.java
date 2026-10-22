class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zeroIdx = -1;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        // Check if the permutation is cyclically increasing
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        
        if (isIncreasing) {
            // Direct left rotations vs (Reverse + Left Rotations + Reverse)
            return Math.min(zeroIdx, n - zeroIdx + 2);
        }
        
        // Check if the permutation is cyclically decreasing
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }
        
        if (isDecreasing) {
            // (Reverse + Left Rotations) vs (Left Rotations + Reverse)
            int pathA = n - zeroIdx;
            int pathB = ((zeroIdx + 1) % n) + 1;
            return Math.min(pathA, pathB);
        }
        
        // Neither configuration can be sorted using allowed moves
        return -1;
    }
}
