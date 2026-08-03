class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp array to keep track of the next 3 states
        // dp[0] represents current, dp[1] is i+1, dp[2] is i+2, dp[3] is i+3
        int[] dp = new int[4];

        // Iterate backwards from the last stone to the first
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

            // Current maximum difference achievable from index i
            int currentMax = Math.max(takeOne, Math.max(takeTwo, takeThree));

            // Shift values to simulate i+1, i+2, i+3 for the next iteration
            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = currentMax;
        }

        // dp[1] now holds the maximum relative score from index 0
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
