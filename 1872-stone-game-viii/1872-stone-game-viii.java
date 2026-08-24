class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }

        // res represents the max score difference from the current suffix max
        int res = prefixSum[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            res = Math.max(res, prefixSum[i] - res);
        }

        return res;
    }
}
