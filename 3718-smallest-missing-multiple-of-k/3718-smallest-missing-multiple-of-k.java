import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int currentMultiple = k;
        while (numSet.contains(currentMultiple)) {
            currentMultiple += k;
        }
        return currentMultiple;
    }
}
