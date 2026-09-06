class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[i] stores the number of distinct subsequences of s that equal t[0...i-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty string t can always be formed by an empty subsequence of s
        dp[0] = 1;
        
        // Iterate through each character of s
        for (int j = 1; j <= m; j++) {
            char sChar = s.charAt(j - 1);
            
            // Traverse backward to use the values from the previous iteration of s
            for (int i = n; i >= 1; i--) {
                char tChar = t.charAt(i - 1);
                
                // If characters match, add the ways to form t[0...i-2]
                if (sChar == tChar) {
                    dp[i] = dp[i] + dp[i - 1];
                }
                // If they don't match, dp[i] remains the same (inheriting dp[i][j-1] equivalent)
            }
        }
        
        return dp[n];
    }
}
