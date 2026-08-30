class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;

        // Step 1: Find the indices of the minimum and maximum elements
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Ensure minIndex is the smaller index and maxIndex is the larger index
        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);

        // Step 2: Calculate the three possible deletion costs
        int deleteFromFront = j + 1; // Remove both from the front
        int deleteFromBack = n - i; // Remove both from the back
        int deleteBothSides = (i + 1) + (n - j); // Remove one from front, one from back

        // Step 3: Return the minimum of the three options
        return Math.min(Math.min(deleteFromFront, deleteFromBack), deleteBothSides);
    }
}
