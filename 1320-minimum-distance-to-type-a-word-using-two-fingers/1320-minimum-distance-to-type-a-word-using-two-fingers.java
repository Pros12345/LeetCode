class Solution {
    private int[] getCoord(char c) {
        int idx = c - 'A';
        return new int[] { idx / 6, idx % 6 };
    }

    private int getDist(char a, char b) {
        if (a == 0)
            return 0;
        int[] c1 = getCoord(a);
        int[] c2 = getCoord(b);
        return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1] - c2[1]);
    }

    public int minimumDistance(String word) {
        int n = word.length();
        Integer[][][] memo = new Integer[n][27][27];
        return dp(0, 0, 0, word, memo);
    }

    private int dp(int i, int f1, int f2, String word, Integer[][][] memo) {
        if (i == word.length()) {
            return 0;
        }
        if (memo[i][f1][f2] != null) {
            return memo[i][f1][f2];
        }

        char curr = word.charAt(i);
        char charF1 = (f1 == 0) ? 0 : (char) ('A' + f1 - 1);
        char charF2 = (f2 == 0) ? 0 : (char) ('A' + f2 - 1);
        int cost1 = getDist(charF1, curr) + dp(i + 1, curr - 'A' + 1, f2, word, memo);
        int cost2 = getDist(charF2, curr) + dp(i + 1, f1, curr - 'A' + 1, word, memo);
        return memo[i][f1][f2] = Math.min(cost1, cost2);
    }
}
