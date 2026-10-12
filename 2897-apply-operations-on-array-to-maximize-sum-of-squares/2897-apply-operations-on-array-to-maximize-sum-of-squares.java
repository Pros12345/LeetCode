import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        long MOD = 1_000_000_007;
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        long totalSumOfSquares = 0;
        for (int step = 0; step < k; step++) {
            long currentNumber = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    currentNumber |= (1L << i);
                    bitCounts[i]--; 
                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentNumber * currentNumber) % MOD) % MOD;
        }

        return (int) totalSumOfSquares;
    }
}
