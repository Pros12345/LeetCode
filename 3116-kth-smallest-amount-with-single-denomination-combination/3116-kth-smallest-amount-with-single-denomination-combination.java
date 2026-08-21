class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
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
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long countAmountsLessThanOrEqual(long maxVal, int[] coins) {
        long totalCount = 0;
        int n = coins.length;
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int elementCount = 0;
            boolean overflow = false;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    elementCount++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    if (currentLcm > maxVal) {
                        overflow = true;
                        break;
                    }
                }
            }
            if (!overflow) {
                if (elementCount % 2 == 1) {
                    totalCount += maxVal / currentLcm;
                } else {
                    totalCount -= maxVal / currentLcm;
                }
            }
        }
        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
