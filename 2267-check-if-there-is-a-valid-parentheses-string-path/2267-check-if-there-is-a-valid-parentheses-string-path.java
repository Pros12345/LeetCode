class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length; // FIXED: Changed grid.length to grid[0].length
        
        // A valid path length must be even, start with '(', and end with ')'
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // FIXED: Set balance size to m + n to prevent index out of bounds for invalid paths
        memo = new boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        // Update balance k for the current cell
        k += (grid[i][j] == '(') ? 1 : -1;
        
        // If balance drops below 0, more ')' than '(' -> invalid
        // If balance exceeds maximum theoretical steps remaining, it can't be balanced
        if (k < 0 || k > (m - 1 - i) + (n - 1 - j)) {
            return false;
        }
        
        // If we reach the bottom-right cell, check if parentheses are balanced
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }
        
        // If already visited this state, return false
        if (memo[i][j][k]) {
            return false;
        }
        memo[i][j][k] = true;
        
        // Move down
        if (i + 1 < m && dfs(i + 1, j, k)) {
            return true;
        }
        
        // Move right
        if (j + 1 < n && dfs(i, j + 1, k)) {
            return true;
        }
        
        return false;
    }
}
