import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        if (dfs(0, true, count, sb, target, n)) {
            return sb.toString();
        }
        return "";
    }

    private boolean dfs(int idx, boolean isPrefix, int[] count, StringBuilder sb, String target, int n) {
        if (idx == n) {
            return !isPrefix;
        }

        int start = isPrefix ? (target.charAt(idx) - 'a') : 0;
        for (int c = start; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;
                sb.append((char) ('a' + c));

                boolean nextPrefix = isPrefix && (c == start);

                if (!nextPrefix) {
                    fillRemaining(count, sb);
                    return true;
                } else {
                    if (dfs(idx + 1, nextPrefix, count, sb, target, n)) {
                        return true;
                    }
                }

                sb.setLength(sb.length() - 1);
                count[c]++;
            }
        }
        return false;
    }

    private void fillRemaining(int[] count, StringBuilder sb) {
        for (int c = 0; c < 26; c++) {
            while (count[c] > 0) {
                count[c]--;
                sb.append((char) ('a' + c));
            }
        }
    }
}
