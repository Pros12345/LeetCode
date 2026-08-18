import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        if (k == 1) {
            int maxVal = -1;
            for (int num : nums) {
                if (count.get(num) == 1) {
                    maxVal = Math.max(maxVal, num);
                }
            }
            return maxVal;
        }
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
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
