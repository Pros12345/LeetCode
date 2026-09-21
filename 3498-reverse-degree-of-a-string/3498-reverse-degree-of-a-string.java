class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            // Calculate reversed alphabet position ('a' = 26, 'b' = 25, ..., 'z' = 1)
            int reversedAlphabetPos = 26 - (c - 'a');

            // 1-indexed position in the string
            int stringPos = i + 1;

            // Add the product to the cumulative sum
            totalDegree += reversedAlphabetPos * stringPos;
        }

        return totalDegree;
    }
}
