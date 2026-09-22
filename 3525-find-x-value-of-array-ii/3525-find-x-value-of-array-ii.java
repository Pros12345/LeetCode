import java.util.Arrays;

class Solution {
    static class Node {
        int[] remain = new int[5];
        int prod = 1;
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        
        // Initialize tree nodes
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        // Pre-reduce elements modulo k to prevent overflow
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }

        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];

            // Update the segment tree
            update(0, 0, n - 1, index, value);

            // Query the suffix range [start, n - 1]
            Node queryResult = query(0, 0, n - 1, start, n - 1);
            ans[i] = queryResult.remain[x];
        }

        return ans;
    }

    private void build(int[] nums, int cur, int left, int right) {
        if (left == right) {
            tree[cur].remain[nums[left]] = 1;
            tree[cur].prod = nums[left];
            return;
        }
        int mid = left + (right - left) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            Arrays.fill(tree[cur].remain, 0);
            tree[cur].remain[val] = 1;
            tree[cur].prod = val;
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, lo, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, hi, idx, val);
        }
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int lo, int hi, int left, int right) {
        if (left <= lo && hi <= right) {
            return tree[cur];
        }
        int mid = lo + (hi - lo) / 2;
        if (right <= mid) {
            return query(2 * cur + 1, lo, mid, left, right);
        }
        if (left > mid) {
            return query(2 * cur + 2, mid + 1, hi, left, right);
        }
        Node leftNode = query(2 * cur + 1, lo, mid, left, right);
        Node rightNode = query(2 * cur + 2, mid + 1, hi, left, right);
        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.prod = (left.prod * right.prod) % k;
        
        for (int i = 0; i < k; i++) {
            res.remain[i] += left.remain[i];
            res.remain[(i * left.prod) % k] += right.remain[i];
        }
        return res;
    }
}
