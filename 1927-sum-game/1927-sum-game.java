class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        // Step 1: Count sums and '?' for both halves
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') leftQ++;
                else leftSum += c - '0';
            } else {
                if (c == '?') rightQ++;
                else rightSum += c - '0';
            }
        }
        return 2 * (leftSum - rightSum) != 9 * (rightQ - leftQ);
    }
}
