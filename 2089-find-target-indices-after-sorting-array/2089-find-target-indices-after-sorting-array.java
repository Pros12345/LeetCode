import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        // Step 1: Sort the array in non-decreasing order
        Arrays.sort(nums);
        
        // Step 2: Create a list to store the target indices
        List<Integer> result = new ArrayList<>();
        
        // Step 3: Iterate through the sorted array to find all matches
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        
        return result;
    }
}
