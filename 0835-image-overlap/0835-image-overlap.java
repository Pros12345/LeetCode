import java.util.*;

public class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Step 1: Store the coordinates of all 1s for both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1)
                    list1.add(new int[] { i, j });
                if (img2[i][j] == 1)
                    list2.add(new int[] { i, j });
            }
        }

        // Step 2: Map to record the frequency of each unique shift vector (rowShift, colShift)
        // We use a String key "rowShift,colShift" to represent the vector unique transformation
        Map<String, Integer> counts = new HashMap<>();
        int maxOverlap = 0;

        // Step 3: Calculate the delta/shift between every pair of 1s
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int rowShift = p2[0] - p1[0];
                int colShift = p2[1] - p1[1];
                String key = rowShift + "," + colShift;

                counts.put(key, counts.getOrDefault(key, 0) + 1);
                maxOverlap = Math.max(maxOverlap, counts.get(key));
            }
        }

        return maxOverlap;
    }
}
