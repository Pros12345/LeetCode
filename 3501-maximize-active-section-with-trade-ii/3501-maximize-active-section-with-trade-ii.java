import java.util.ArrayList;
import java.util.List;

class Solution {

    private static class Block {
        int start;
        int end;

        Block(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Block> blocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                int start = i;
                while (i < n && s.charAt(i) == '1') {
                    i++;
                }
                int end = i - 1;
                blocks.add(new Block(start, end));
            } else {
                i++;
            }
        }

        int k = blocks.size();
        int[] prev1 = new int[k];
        int[] next1 = new int[k];
        int[] internalGain = new int[k];

        for (int m = 0; m < k; m++) {
            prev1[m] = (m > 0) ? blocks.get(m - 1).end : -1;
            next1[m] = (m < k - 1) ? blocks.get(m + 1).start : n;

            int startM = blocks.get(m).start;
            int endM = blocks.get(m).end;

            internalGain[m] = startM - endM - prev1[m] + next1[m] - 2;
        }

        int[][] st = new int[k][20];
        int[] logTable = new int[k + 1];

        if (k > 0) {
            for (int j = 2; j <= k; j++) {
                logTable[j] = logTable[j >> 1] + 1;
            }
            for (int m = 0; m < k; m++) {
                st[m][0] = internalGain[m];
            }
            for (int j = 1; (1 << j) <= k; j++) {
                for (int m = 0; m + (1 << j) <= k; m++) {
                    st[m][j] = Math.max(st[m][j - 1], st[m + (1 << (j - 1))][j - 1]);
                }
            }
        }

        List<Integer> answer = new ArrayList<>(queries.length);

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            if (k == 0) {
                answer.add(totalOnes);
                continue;
            }

            int low = 0, high = k - 1;
            int idxStart = k;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (blocks.get(mid).start > l) {
                    idxStart = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            low = 0;
            high = k - 1;
            int idxEnd = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (blocks.get(mid).end < r) {
                    idxEnd = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (idxStart > idxEnd) {
                answer.add(totalOnes);
                continue;
            }

            int maxGain = 0;

            if (idxStart == idxEnd) {
                maxGain = Math.max(maxGain, getGain(blocks, prev1, next1, idxStart, l, r));
            } else if (idxStart + 1 == idxEnd) {
                maxGain = Math.max(maxGain, getGain(blocks, prev1, next1, idxStart, l, r));
                maxGain = Math.max(maxGain, getGain(blocks, prev1, next1, idxEnd, l, r));
            } else {
                maxGain = Math.max(maxGain, getGain(blocks, prev1, next1, idxStart, l, r));
                maxGain = Math.max(maxGain, getGain(blocks, prev1, next1, idxEnd, l, r));

                int L = idxStart + 1;
                int R = idxEnd - 1;
                int j = logTable[R - L + 1];
                int rmqValue = Math.max(st[L][j], st[R - (1 << j) + 1][j]);
                maxGain = Math.max(maxGain, rmqValue);
            }

            answer.add(totalOnes + maxGain);
        }

        return answer;
    }

    private int getGain(List<Block> blocks, int[] prev1, int[] next1, int m, int l, int r) {
        Block b = blocks.get(m);
        int leftLen = b.start - Math.max(l, prev1[m] + 1);
        int rightLen = Math.min(r, next1[m] - 1) - b.end;
        return leftLen + rightLen;
    }
}
