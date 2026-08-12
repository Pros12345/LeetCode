import java.util.TreeSet;

public class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum1 = new int[m + 2][n + 2];
        int[][] sum2 = new int[m + 2][n + 2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum1[i][j] = sum1[i - 1][j - 1] + grid[i - 1][j - 1];
                sum2[i][j] = sum2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }
        TreeSet<Integer> topThree = new TreeSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                addSum(topThree, grid[i][j]);
                for (int r = 1; i + 2 * r < m && j - r >= 0 && j + r < n; r++) {
                    int ux = i, uy = j;
                    int dx = i + 2 * r, dy = j;
                    int lx = i + r, ly = j - r;
                    int rx = i + r, rj = j + r;
                    int borderSum = 0;
                    borderSum += sum2[lx + 1][ly + 1] - sum2[ux][uy + 2];
                    borderSum += sum1[rx + 1][rj + 1] - sum1[ux][uy];
                    borderSum += sum1[dx + 1][dy + 1] - sum1[lx][ly];
                    borderSum += sum2[dx + 1][dy + 1] - sum2[rx][rj + 2];
                    borderSum -= (grid[ux][uy] + grid[dx][dy] + grid[lx][ly] + grid[rx][rj]);
                    addSum(topThree, borderSum);
                }
            }
        }
        int[] result = new int[topThree.size()];
        int idx = 0;
        while (!topThree.isEmpty()) {
            result[idx++] = topThree.pollLast();
        }
        return result;
    }

    private void addSum(TreeSet<Integer> set, int sum) {
        set.add(sum);
        if (set.size() > 3) {
            set.pollFirst();
        }
    }
}
