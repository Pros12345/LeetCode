class Solution {
    // Changed the name from findValidSequence to validSequence
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] last = new int[n + 1];
        int w2Idx = m - 1;

        last[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            if (w2Idx >= 0 && word1.charAt(i) == word2.charAt(w2Idx)) {
                w2Idx--;
            }
            last[i] = w2Idx + 1;
        }

        int[] result = new int[m];
        int j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;
                j++;
            } else if (!changed && last[i + 1] <= j + 1) {
                result[j] = i;
                j++;
                changed = true;
            }
        }

        // If we successfully matched all characters of word2, return the sequence
        return (j == m) ? result : new int[0];
    }
}
