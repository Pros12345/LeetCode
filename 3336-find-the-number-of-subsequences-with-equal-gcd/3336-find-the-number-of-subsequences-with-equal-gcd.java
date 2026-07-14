class Solution {
    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int[][] dp = new int[201][201];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] ndp = new int[201][201];

            for (int g1 = 0; g1 <= 200; g1++) {
                for (int g2 = 0; g2 <= 200; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long cur = dp[g1][g2];

                    // skip
                    ndp[g1][g2] = (int)((ndp[g1][g2] + cur) % MOD);

                    // put in seq1
                    int ng1 = (g1 == 0) ? num : gcd(g1, num);
                    ndp[ng1][g2] = (int)((ndp[ng1][g2] + cur) % MOD);

                    // put in seq2
                    int ng2 = (g2 == 0) ? num : gcd(g2, num);
                    ndp[g1][ng2] = (int)((ndp[g1][ng2] + cur) % MOD);
                }
            }

            dp = ndp;
        }

        long ans = 0;

        for (int g = 1; g <= 200; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}