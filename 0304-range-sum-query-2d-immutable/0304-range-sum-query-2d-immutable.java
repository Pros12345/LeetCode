class NumMatrix {
    private int[][] prefix; // 2D prefix sum array

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        prefix = new int[m + 1][n + 1]; // extra row and column for easier math

        // Build prefix sum
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = matrix[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Convert to 1-based indexing for prefix array
        int r1 = row1 + 1, c1 = col1 + 1;
        int r2 = row2 + 1, c2 = col2 + 1;

        return prefix[r2][c2]
                - prefix[r1 - 1][c2]
                - prefix[r2][c1 - 1]
                + prefix[r1 - 1][c1 - 1];
    }
}
