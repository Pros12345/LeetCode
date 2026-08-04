import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> presenceSet = new HashSet<>();
        for (int num : nums) {
            if (num < min)
                min = num;
            if (num > max)
                max = num;
            presenceSet.add(num);
        }
        for (int i = min + 1; i < max; i++) {
            if (!presenceSet.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }
}
