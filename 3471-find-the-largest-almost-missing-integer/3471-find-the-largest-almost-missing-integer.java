import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total occurrences of each number in nums
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // Case 1: k == 1, find the maximum number that appears exactly once
        if (k == 1) {
            int maxVal = -1;
            for (int num : nums) {
                if (count.get(num) == 1) {
                    maxVal = Math.max(maxVal, num);
                }
            }
            return maxVal;
        }
        
        // Case 2: k == n, every element appears in the single subarray
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n, only nums[0] and nums[n-1] can be in exactly one subarray of size k
        int maxVal = -1;
        if (count.get(nums[0]) == 1) {
            maxVal = Math.max(maxVal, nums[0]);
        }
        if (count.get(nums[n - 1]) == 1) {
            maxVal = Math.max(maxVal, nums[n - 1]);
        }
        
        return maxVal;
    }
}
