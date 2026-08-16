class Solution {
    class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        int size;

        Node(int len) {
            this.maxLen = len;
            this.prefLen = len;
            this.suffLen = len;
            this.size = len;
        }
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];
        build(0, 0, n - 1);
        int k = queryIndices.length;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            update(0, 0, n - 1, idx, c);
            result[i] = tree[0].maxLen;
        }
        return result;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(1);
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild], mid, start, end);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            chars[idx] = c;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        if (idx <= mid) {
            update(leftChild, start, mid, idx, c);
        } else {
            update(rightChild, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[leftChild], tree[rightChild], mid, start, end);
    }

    private Node merge(Node left, Node right, int mid, int start, int end) {
        Node parent = new Node(0);
        parent.size = left.size + right.size;
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        parent.prefLen = left.prefLen;
        parent.suffLen = right.suffLen;
        if (chars[mid] == chars[mid + 1]) {
            parent.maxLen = Math.max(parent.maxLen, left.suffLen + right.prefLen);
            if (left.prefLen == left.size) {
                parent.prefLen = left.size + right.prefLen;
            }
            if (right.suffLen == right.size) {
                parent.suffLen = right.size + left.suffLen;
            }
        }
        return parent;
    }
}
