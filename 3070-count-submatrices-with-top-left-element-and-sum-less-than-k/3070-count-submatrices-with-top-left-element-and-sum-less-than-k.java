class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Tracks the sum of elements in each column up to the current row
        int[] cols = new int[n];

        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                // Add current cell to its column sum
                cols[j] += grid[i][j];

                // Accumulate the column sums for the current row
                rowSum += cols[j];

                // If the submatrix sum is within bounds, count it
                if (rowSum <= k) {
                    count++;
                } else {
                    // Since grid elements are positive, further elements in this row will exceed k
                    break;
                }
            }
        }

        return count;
    }
}
