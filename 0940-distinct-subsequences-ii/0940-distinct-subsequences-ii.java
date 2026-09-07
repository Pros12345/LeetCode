class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1_000_000_007;
        long[] endsWith = new long[26];
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            long total = 0;
            for (long count : endsWith) {
                total = (total + count) % mod;
            }
            // New count for this character is total of all previous subsequences + 1 (the single character itself)
            endsWith[index] = (total + 1) % mod;
        }
        
        long totalDistinct = 0;
        for (long count : endsWith) {
            totalDistinct = (totalDistinct + count) % mod;
        }
        
        return (int) totalDistinct;
    }
}
