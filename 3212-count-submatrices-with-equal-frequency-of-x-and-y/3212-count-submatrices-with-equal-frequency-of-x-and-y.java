class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length; // Fixed to grid[0].length for non-square matrices
        int totalSubmatrices = 0;

        // prefixX[i][j] stores total 'X' from (0,0) to (i-1, j-1)
        int[][] prefixX = new int[m + 1][n + 1];
        // prefixY[i][j] stores total 'Y' from (0,0) to (i-1, j-1)
        int[][] prefixY = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int isX = (grid[i][j] == 'X') ? 1 : 0;
                int isY = (grid[i][j] == 'Y') ? 1 : 0;

                // Standard 2D Prefix Sum inclusion-exclusion principle
                prefixX[i + 1][j + 1] = prefixX[i][j + 1] + prefixX[i + 1][j] - prefixX[i][j] + isX;
                prefixY[i + 1][j + 1] = prefixY[i][j + 1] + prefixY[i + 1][j] - prefixY[i][j] + isY;

                int currentXCount = prefixX[i + 1][j + 1];
                int currentYCount = prefixY[i + 1][j + 1];

                // Check constraints: equal counts of X and Y, and at least one X
                if (currentXCount == currentYCount && currentXCount > 0) {
                    totalSubmatrices++;
                }
            }
        }

        return totalSubmatrices;
    }
}
