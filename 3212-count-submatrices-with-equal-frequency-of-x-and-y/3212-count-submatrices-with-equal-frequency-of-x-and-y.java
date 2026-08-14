class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int totalSubmatrices = 0;
        int[][] prefixX = new int[m + 1][n + 1];
        int[][] prefixY = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int isX = (grid[i][j] == 'X') ? 1 : 0;
                int isY = (grid[i][j] == 'Y') ? 1 : 0;
                prefixX[i + 1][j + 1] = prefixX[i][j + 1] + prefixX[i + 1][j] - prefixX[i][j] + isX;
                prefixY[i + 1][j + 1] = prefixY[i][j + 1] + prefixY[i + 1][j] - prefixY[i][j] + isY;

                int currentXCount = prefixX[i + 1][j + 1];
                int currentYCount = prefixY[i + 1][j + 1];
                if (currentXCount == currentYCount && currentXCount > 0) {
                    totalSubmatrices++;
                }
            }
        }
        return totalSubmatrices;
    }
}
