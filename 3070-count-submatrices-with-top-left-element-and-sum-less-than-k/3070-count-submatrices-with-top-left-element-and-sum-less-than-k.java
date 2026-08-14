class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                cols[j] += grid[i][j];
                rowSum += cols[j];
                if (rowSum <= k) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }
}
