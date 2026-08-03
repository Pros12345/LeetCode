class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4];

        for (int i = n - 1; i >= 0; i--) {
            int takeOne = stoneValue[i] - dp[1];

            int takeTwo = Integer.MIN_VALUE;
            if (i + 1 < n) {
                takeTwo = stoneValue[i] + stoneValue[i + 1] - dp[2];
            }

            int takeThree = Integer.MIN_VALUE;
            if (i + 2 < n) {
                takeThree = stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[3];
            }

            int currentMax = Math.max(takeOne, Math.max(takeTwo, takeThree));
            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = currentMax;
        }
        int aliceVsBob = dp[1];

        if (aliceVsBob > 0) {
            return "Alice";
        } else if (aliceVsBob < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
