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

        // If all elements are 0, no non-zero XOR subsequence is possible
        if (!hasNonZero) {
            return 0;
        }

        // If the total XOR sum is non-zero, take the whole array
        if (totalXor != 0) {
            return n;
        }

        // If the total XOR sum is zero, removing one non-zero element makes it non-zero
        return n - 1;
    }
}
