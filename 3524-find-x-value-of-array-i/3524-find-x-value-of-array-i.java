class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[rem] stores the number of subarrays ending at the current element 
        // whose product modulo k is equal to rem.
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentRem = num % k;

            // 1. A new subarray can start at the current element
            nextDp[currentRem]++;

            // 2. Extend existing subarrays from the previous element
            for (int rem = 0; rem < k; rem++) {
                if (dp[rem] > 0) {
                    int newRem = (rem * currentRem) % k;
                    nextDp[newRem] += dp[rem];
                }
            }

            // Accumulate the counts ending at the current position into the final result
            for (int rem = 0; rem < k; rem++) {
                result[rem] += nextDp[rem];
            }

            // Move to the next element
            dp = nextDp;
        }

        return result;
    }
}
