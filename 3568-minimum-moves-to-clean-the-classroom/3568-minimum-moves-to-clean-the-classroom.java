import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startX = 0, startY = 0, litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
        }

        // Locate 'S' and assign IDs to 'L' cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) return 0;

        // Queue for BFS storing: [x, y, current_energy, mask]
        List<int[]> q = new ArrayList<>();
        int initialMask = (1 << litterCount) - 1;
        q.add(new int[]{startX, startY, energy, initialMask});

        boolean[][][][] vis = new boolean[m][n][energy + 1][1 << litterCount];
        vis[startX][startY][energy][initialMask] = true;

        int[] dirs = {-1, 0, 1, 0, -1};
        int moves = 0;

        while (!q.isEmpty()) {
            List<int[]> nextQ = new ArrayList<>();
            for (int[] state : q) {
                int x = state[0];
                int y = state[1];
                int curEnergy = state[2];
                int mask = state[3];

                // If all litter collected (mask becomes 0)
                if (mask == 0) {
                    return moves;
                }

                for (int k = 0; k < 4; k++) {
                    int nx = x + dirs[k];
                    int ny = y + dirs[k + 1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    char nextChar = classroom[nx].charAt(ny);
                    if (nextChar == 'X') continue; // Obstacle

                    int nEnergy = curEnergy - 1;
                    if (nEnergy < 0) continue; // CRITICAL FIX: Out of energy to even make a move

                    if (nextChar == 'R') {
                        nEnergy = energy; // Reset energy to max upon stepping on 'R'
                    }

                    int nMask = mask;
                    if (nextChar == 'L' && (mask & (1 << litterId[nx][ny])) != 0) {
                        nMask ^= (1 << litterId[nx][ny]); // Collect litter
                    }

                    if (!vis[nx][ny][nEnergy][nMask]) {
                        vis[nx][ny][nEnergy][nMask] = true;
                        nextQ.add(new int[]{nx, ny, nEnergy, nMask});
                    }
                }
            }
            q = nextQ;
            moves++;
        }

        return -1;
    }
}
