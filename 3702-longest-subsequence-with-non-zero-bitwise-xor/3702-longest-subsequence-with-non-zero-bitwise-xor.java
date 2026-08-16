class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        if (!hasNonZero) {
            return 0;
        }
        if (totalXor != 0) {
            return n;
        }
        return n - 1;
    }
}
