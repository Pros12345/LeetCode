/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // If both nodes are null, they match
        if (t1 == null && t2 == null) {
            return true;
        }
        // If only one node is null or values don't match, it is not symmetric
        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }
        // Compare outer and inner children recursively
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
