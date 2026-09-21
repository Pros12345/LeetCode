class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentRem = num % k;
            nextDp[currentRem]++;
            for (int rem = 0; rem < k; rem++) {
                if (dp[rem] > 0) {
                    int newRem = (rem * currentRem) % k;
                    nextDp[newRem] += dp[rem];
                }
            }
            for (int rem = 0; rem < k; rem++) {
                result[rem] += nextDp[rem];
            }
            dp = nextDp;
        }
        return result;
    }
}
