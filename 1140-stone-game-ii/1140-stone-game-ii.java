class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        // memo[i][M] stores the max stones Alice can get starting at index i with current M
        // Max possible value for M is n, so we use size n + 1
        memo = new int[n][n + 1];
        suffixSum = new int[n];

        // Fill suffix sum array from right to left
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return getOptimalStones(0, 1, n);
    }

    private int getOptimalStones(int i, int M, int n) {
        // Base case: if no piles are left
        if (i >= n) {
            return 0;
        }

        // If all remaining piles can be taken in this turn
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return cached result if already calculated
        if (memo[i][M] > 0) {
            return memo[i][M];
        }

        int minOpponentStones = Integer.MAX_VALUE;

        // Try all valid moves for X
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            int opponentStones = getOptimalStones(i + X, nextM, n);
            minOpponentStones = Math.min(minOpponentStones, opponentStones);
        }
        memo[i][M] = suffixSum[i] - minOpponentStones;
        return memo[i][M];
    }
}
