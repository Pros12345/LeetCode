class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int bestL = -1;
        int bestChar = -1;
        int[] curCount = count.clone();

        // Step 1: Find the maximum index 'i' where we can branch to a larger character
        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';
            
            // Check if there is a strictly greater character available
            for (int c = targetChar + 1; c < 26; c++) {
                if (curCount[c] > 0) {
                    bestL = i;
                    bestChar = c;
                    break; // The smallest available larger character is always optimal
                }
            }
            
            // Try to match the target character exactly to keep extending the prefix
            if (curCount[targetChar] > 0) {
                curCount[targetChar]--;
            } else {
                break; // Cannot match target any further
            }
        }

        // If no valid branching point was found, it's impossible
        if (bestL == -1) {
            return "";
        }

        // Step 2: Reconstruct the lexicographically smallest valid permutation
        StringBuilder sb = new StringBuilder();
        
        // 1. Append the matching target prefix
        sb.append(target.substring(0, bestL));
        
        // 2. Update character counts by removing the prefix characters and the chosen branch char
        int[] finalCount = count.clone();
        for (int i = 0; i < bestL; i++) {
            finalCount[target.charAt(i) - 'a']--;
        }
        finalCount[bestChar]--;
        
        // 3. Append the strictly greater character
        sb.append((char) ('a' + bestChar));
        
        // 4. Append all remaining available characters in ascending sorted order
        for (int c = 0; c < 26; c++) {
            while (finalCount[c] > 0) {
                sb.append((char) ('a' + c));
                finalCount[c]--;
            }
        }

        return sb.toString();
    }
}
