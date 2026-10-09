class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If rightNeeded is odd, a single ')' is hanging.
                // We must balance it immediately by adding another ')'.
                if (rightNeeded % 2 == 1) {
                    insertions++;
                    rightNeeded--;
                }
                rightNeeded += 2;
            } else {
                rightNeeded--;
                // If rightNeeded drops below 0, we found an unexpected ')'.
                // We must insert a '(' before it. The new '(' adds 2 to rightNeeded.
                // Since we were at -1, -1 + 2 = 1.
                if (rightNeeded == -1) {
                    insertions++;
                    rightNeeded = 1;
                }
            }
        }
        
        // Add any remaining right parentheses needed at the end
        return insertions + rightNeeded;
    }
}
