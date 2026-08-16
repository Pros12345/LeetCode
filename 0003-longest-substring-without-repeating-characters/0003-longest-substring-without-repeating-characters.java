class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        java.util.Arrays.fill(lastSeen, -1);
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            if (lastSeen[curr] >= left) {
                left = lastSeen[curr] + 1;
            }
            lastSeen[curr] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
