class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Array to store the last seen index of characters (ASCII extended set)
        int[] lastSeen = new int[128];
        // Initialize all indices to -1 (meaning not seen yet)
        java.util.Arrays.fill(lastSeen, -1);
        
        int maxLength = 0;
        int left = 0; // Left boundary of the sliding window
        
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            
            // If the character was seen inside the current window, move 'left'
            if (lastSeen[curr] >= left) {
                left = lastSeen[curr] + 1;
            }
            
            // Update the last seen position of the character
            lastSeen[curr] = right;
            
            // Calculate and update the maximum window size found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
