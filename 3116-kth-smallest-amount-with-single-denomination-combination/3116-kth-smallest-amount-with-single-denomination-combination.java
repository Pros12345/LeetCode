class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Binary search range for the answer
        long low = 1;
        // Upper bound estimation: smallest coin * k
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countAmountsLessThanOrEqual(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1; // Increase the search space
            }
        }
        return ans;
    }

    // Helper method to count how many valid coin amounts exist <= maxVal
    private long countAmountsLessThanOrEqual(long maxVal, int[] coins) {
        long totalCount = 0;
        int n = coins.length;

        // Iterate through all 2^n - 1 non-empty subsets using bitmasking
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int elementCount = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    elementCount++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    // Break early if LCM surpasses maxVal to avoid overflow
                    if (currentLcm > maxVal) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                // Inclusion-Exclusion logic: 
                // Add if subset size is odd, subtract if even
                if (elementCount % 2 == 1) {
                    totalCount += maxVal / currentLcm;
                } else {
                    totalCount -= maxVal / currentLcm;
                }
            }
        }
        return totalCount;
    }

    // GCD utility function
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // LCM utility function
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
