import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // Include the current element in the window
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);

            // If the frequency exceeds k, shrink the window from the left
            while (countMap.get(nums[right]) > k) {
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                left++;
            }

            // Calculate the maximum valid window length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
