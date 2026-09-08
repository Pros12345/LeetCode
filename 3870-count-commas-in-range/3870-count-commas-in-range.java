class Solution {
    public int countCommas(int n) {
        // Numbers from 1 to 999 have 0 commas.
        if (n < 1000) {
            return 0;
        }
        // From 1000 to n, each number has at least 1 comma. 
        // For larger constraints (like up to 10^15), a digit-range loop is used.
        int totalCommas = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            int len = s.length();
            if (len >= 4) {
                totalCommas += (len - 1) / 3;
            }
        }
        return totalCommas;
    }
}
