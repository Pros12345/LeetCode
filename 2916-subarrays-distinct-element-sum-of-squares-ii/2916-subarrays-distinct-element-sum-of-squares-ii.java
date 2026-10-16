import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    // Segment Tree Nodes
    private long[] sumOfSquares;
    private long[] sumOfCounts;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumOfSquares = new long[4 * n];
        sumOfCounts = new long[4 * n];
        lazy = new long[4 * n];

        long totalSum = 0;
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int j = 0; j < n; j++) {
            int lastIdx = lastSeen.getOrDefault(nums[j], -1);
            
            // Increment the distinct count by 1 for all subarrays starting from lastIdx + 1 to j
            update(1, 0, n - 1, lastIdx + 1, j, 1);
            
            // Add the sum of squares of all subarrays ending at j to the total answer
            totalSum = (totalSum + sumOfSquares[1]) % MOD;
            
            // Record the current position of the element
            lastSeen.put(nums[j], j);
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        long val = lazy[node];
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // Update left child
        long leftLen = mid - start + 1;
        sumOfSquares[leftChild] = (sumOfSquares[leftChild] + 2 * val * sumOfCounts[leftChild] + val * val % MOD * leftLen) % MOD;
        sumOfCounts[leftChild] = (sumOfCounts[leftChild] + val * leftLen) % MOD;
        lazy[leftChild] = (lazy[leftChild] + val) % MOD;

        // Update right child
        long rightLen = end - mid;
        sumOfSquares[rightChild] = (sumOfSquares[rightChild] + 2 * val * sumOfCounts[rightChild] + val * val % MOD * rightLen) % MOD;
        sumOfCounts[rightChild] = (sumOfCounts[rightChild] + val * rightLen) % MOD;
        lazy[rightChild] = (lazy[rightChild] + val) % MOD;

        // Clear current lazy value
        lazy[node] = 0;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            long len = end - start + 1;
            sumOfSquares[node] = (sumOfSquares[node] + 2 * val * sumOfCounts[node] + val * val % MOD * len) % MOD;
            sumOfCounts[node] = (sumOfCounts[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;

        if (l <= mid) {
            update(2 * node, start, mid, l, r, val);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r, val);
        }

        sumOfSquares[node] = (sumOfSquares[2 * node] + sumOfSquares[2 * node + 1]) % MOD;
        sumOfCounts[node] = (sumOfCounts[2 * node] + sumOfCounts[2 * node + 1]) % MOD;
    }
}
