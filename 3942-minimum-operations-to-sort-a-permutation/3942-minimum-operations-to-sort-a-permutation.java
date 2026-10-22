class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        if (isIncreasing) {
            return Math.min(zeroIdx, n - zeroIdx + 2);
        }
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }
        if (isDecreasing) {
            int pathA = n - zeroIdx;
            int pathB = ((zeroIdx + 1) % n) + 1;
            return Math.min(pathA, pathB);
        }
        return -1;
    }
}
