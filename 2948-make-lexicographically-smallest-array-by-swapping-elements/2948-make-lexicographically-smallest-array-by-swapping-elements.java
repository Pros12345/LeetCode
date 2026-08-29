import java.util.*;

public class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        // 2. Sort the pairs based on their values
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        // 3. Process the sorted array using a two-pointer sliding window to find groups
        while (i < n) {
            int j = i + 1;
            // Find all elements that belong to the same group
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            // 4. Extract and sort the original indices for this group
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k < j; k++) {
                indices.add(pairs[k][1]);
            }
            Collections.sort(indices);
            
            // Put the sorted values into the sorted positions
            for (int k = i; k < j; k++) {
                result[indices.get(k - i)] = pairs[k][0];
            }
            
            // Move to the next group
            i = j;
        }
        
        return result;
    }
}
