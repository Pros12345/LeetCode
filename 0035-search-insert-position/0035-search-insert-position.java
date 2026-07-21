class Solution {
    public int searchInsert(int[] nums, int target) {
        int tar = 0;
        for (int i = 0; i < nums.length; i++) {

            if (target > nums[i]) {
                tar = i + 1;

            }
            if (nums[i] == target) {
                tar = i;
            }
        }
        return tar;
    }
}