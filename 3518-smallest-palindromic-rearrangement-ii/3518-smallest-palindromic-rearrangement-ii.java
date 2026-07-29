import java.util.*;

class Solution {

    private static final long LIMIT = 1_000_001;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        String middle = "";

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];

            if ((freq[i] & 1) == 1) {
                middle = String.valueOf((char) ('a' + i));
            }
        }

        if (countWays(half) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0) {
                    continue;
                }

                half[ch]--;

                long ways = countWays(half);

                if (k > ways) {
                    k -= ways;
                    half[ch]++;
                } else {
                    left.append((char) ('a' + ch));
                    break;
                }
            }
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + middle + right;
    }

    private long countWays(int[] cnt) {

        int total = 0;

        for (int x : cnt) {
            total += x;
        }

        long ans = 1;
        int remaining = total;

        for (int x : cnt) {

            if (x == 0) {
                continue;
            }

            ans *= nCrLimited(remaining, x);

            if (ans >= LIMIT) {
                return LIMIT;
            }

            remaining -= x;
        }

        return ans;
    }

    private long nCrLimited(int n, int r) {

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res >= LIMIT) {
                return LIMIT;
            }
        }

        return res;
    }
}