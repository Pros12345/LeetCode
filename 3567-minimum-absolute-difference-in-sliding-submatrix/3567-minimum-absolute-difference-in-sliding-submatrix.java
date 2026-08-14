import java.util.Arrays;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numRows = m - k + 1;
        int numCols = n - k + 1;
        int[][] result = new int[numRows][numCols];
        int[] kgrid = new int[k * k];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int kIdx = 0;
                for (int r = i; r < i + k; r++) {
                    for (int c = j; c < j + k; c++) {
                        kgrid[kIdx++] = grid[r][c];
                    }
                }
                Arrays.sort(kgrid);
                int minDiff = Integer.MAX_VALUE;
                for (int p = 1; p < kgrid.length; p++) {
                    if (kgrid[p] != kgrid[p - 1]) {
                        int diff = kgrid[p] - kgrid[p - 1];
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }
                result[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }
        return result;
    }
}
