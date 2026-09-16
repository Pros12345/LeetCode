class Solution {
    public int numberOfSets(int n, int k) {
        int totalN = n + k - 1;
        int totalK = 2 * k;
        if (totalK > totalN) {
            return 0;
        }
        int MOD = 1_000_000_007;
        int[] dp = new int[totalK + 1];
        dp[0] = 1;
        for (int i = 1; i <= totalN; i++) {
            for (int j = Math.min(i, totalK); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }
        return dp[totalK];
    }
}
