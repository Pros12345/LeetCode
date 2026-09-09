class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        long commasPerNumber = 1;
        
        while (threshold <= n) {
            long nextThreshold = threshold * 1000;
            long count = Math.min(n, nextThreshold - 1) - threshold + 1;
            totalCommas += count * commasPerNumber;
            
            threshold = nextThreshold;
            commasPerNumber++;
        }
        
        return totalCommas;
    }
}
