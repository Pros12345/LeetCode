class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int topRow = x;
        int bottomRow = x + k - 1;

        // Perform vertical flip by swapping row segments
        while (topRow < bottomRow) {
            for (int col = y; col < y + k; col++) {
                // Swap elements between topRow and bottomRow at the current column
                int temp = grid[topRow][col];
                grid[topRow][col] = grid[bottomRow][col];
                grid[bottomRow][col] = temp;
            }
            topRow++;
            bottomRow--;
        }

        return grid;
    }
}
