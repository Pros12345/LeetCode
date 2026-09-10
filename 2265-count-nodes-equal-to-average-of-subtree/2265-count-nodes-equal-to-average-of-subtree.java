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
    // Global variable to keep track of the matching nodes
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSubtreeDetails(root);
        return matchingNodesCount;
    }

    // Helper method that returns an array: {subtreeSum, nodeCount}
    private int[] calculateSubtreeDetails(TreeNode node) {
        // Base case: if the node is null, sum is 0 and count is 0
        if (node == null) {
            return new int[]{0, 0};
        }

        // Post-order traversal: process left and right subtrees first
        int[] leftDetails = calculateSubtreeDetails(node.left);
        int[] rightDetails = calculateSubtreeDetails(node.right);

        // Calculate total sum and node count for the current subtree
        int currentSum = node.val + leftDetails[0] + rightDetails[0];
        int currentCount = 1 + leftDetails[1] + rightDetails[1];

        // Calculate the integer average (rounded down)
        int average = currentSum / currentCount;

        // If the node's value matches the average, increment our global answer
        if (node.val == average) {
            matchingNodesCount++;
        }

        // Return the current sum and count back up to the parent node
        return new int[]{currentSum, currentCount};
    }
}
