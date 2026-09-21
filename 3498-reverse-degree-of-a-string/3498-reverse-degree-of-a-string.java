class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int reversedAlphabetPos = 26 - (c - 'a');
            int stringPos = i + 1;
            totalDegree += reversedAlphabetPos * stringPos;
        }
        return totalDegree;
    }
}
