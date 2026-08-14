import java.util.Arrays;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Output dimensions match the number of valid sliding window positions
        int numRows = m - k + 1;
        int numCols = n - k + 1;
        int[][] result = new int[numRows][numCols];
        
        // Temporary array to hold elements of a single k x k submatrix
        int[] kgrid = new int[k * k];
        
        // Slide the submatrix window across the main grid
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                
                // 1. Collect all elements inside the current k x k submatrix
                int kIdx = 0;
                for (int r = i; r < i + k; r++) {
                    for (int c = j; c < j + k; c++) {
                        kgrid[kIdx++] = grid[r][c];
                    }
                }
                
                // 2. Sort the extracted submatrix elements
                Arrays.sort(kgrid);
                
                // 3. Find the minimum absolute difference between adjacent distinct elements
                int minDiff = Integer.MAX_VALUE;
                for (int p = 1; p < kgrid.length; p++) {
                    if (kgrid[p] != kgrid[p - 1]) {
                        int diff = kgrid[p] - kgrid[p - 1];
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }
                
                // If all elements in the window are identical, the min unique difference is 0
                result[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }
        
        return result;
    }
}
