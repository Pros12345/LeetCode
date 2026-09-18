import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];

        for (int i = 0; i < 26; i++) {
            left[i] = n;
            right[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }

        List<String> ans = new ArrayList<>();
        int lastRight = -1;

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (i != left[idx])
                continue;

            int newRight = getValidEnd(s, i, left, right);
            if (newRight != -1) {
                if (i <= lastRight && !ans.isEmpty()) {
                    ans.set(ans.size() - 1, s.substring(i, newRight + 1));
                } else {
                    ans.add(s.substring(i, newRight + 1));
                }
                lastRight = newRight;
            }
        }

        return ans;
    }

    private int getValidEnd(String s, int start, int[] left, int[] right) {
        int end = right[s.charAt(start) - 'a'];
        for (int j = start; j <= end; j++) {
            int charIdx = s.charAt(j) - 'a';
            if (left[charIdx] < start) {
                return -1;
            }
            end = Math.max(end, right[charIdx]);
        }
        return end;
    }
}