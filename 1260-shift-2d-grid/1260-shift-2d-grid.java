class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        int[] arr = new int[total];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[(i * n + j + k) % total] = grid[i][j];
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(arr[i * n + j]);
            }
            result.add(row);
        }

        return result;
    }
}