class Solution {
    public int smallestIndex(int[] nums) {
        // Iterate sequentially through the array to find the smallest index first
        for (int i = 0; i < nums.length; i++) {
            if (getDigitSum(nums[i]) == i) {
                return i;
            }
        }
        // Return -1 if no such index satisfies the condition
        return -1;
    }

    // Helper method to calculate the sum of digits of a given number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
