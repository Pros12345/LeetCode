import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        long MOD = 1_000_000_007;

        // Step 1: Count the frequency of each bit position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }

        long totalSumOfSquares = 0;

        // Step 2: Greedily construct the k largest possible numbers
        for (int step = 0; step < k; step++) {
            long currentNumber = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    currentNumber |= (1L << i);
                    bitCounts[i]--; // Consume one bit
                }
            }
            // Step 3: Add the square of the constructed number modulo 10^9 + 7
            totalSumOfSquares = (totalSumOfSquares + (currentNumber * currentNumber) % MOD) % MOD;
        }

        return (int) totalSumOfSquares;
    }
}
