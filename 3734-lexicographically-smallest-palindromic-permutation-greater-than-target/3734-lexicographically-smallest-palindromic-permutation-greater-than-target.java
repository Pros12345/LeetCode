import java.util.*;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int m = n / 2;
        
        // Step 1: Count character frequencies
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        // Step 2: Validate if a palindrome can be formed
        int oddCount = 0;
        char midChar = ' ';
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        
        if (oddCount > 1) {
            return ""; // Impossible to form a palindrome
        }
        
        String mid = oddCount == 1 ? String.valueOf(midChar) : "";
        
        // Counts available for the first half
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = count[i] / 2;
        }
        
        // Step 3: Try exact match for the first half
        boolean canMatch = true;
        int[] tempCounts = halfCounts.clone();
        for (int i = 0; i < m; i++) {
            int idx = target.charAt(i) - 'a';
            if (tempCounts[idx] > 0) {
                tempCounts[idx]--;
            } else {
                canMatch = false;
                break;
            }
        }
        
        if (canMatch) {
            String half = target.substring(0, m);
            String p = half + mid + new StringBuilder(half).reverse().toString();
            if (p.compareTo(target) > 0) {
                return p;
            }
        }
        
        // Step 4: Find the largest index i to diverge and make half[i] > target[i]
        for (int i = m - 1; i >= 0; i--) {
            int[] prefixCounts = new int[26];
            boolean possible = true;
            for (int j = 0; j < i; j++) {
                prefixCounts[target.charAt(j) - 'a']++;
            }
            
            // Check if target[0...i-1] can be formed
            for (int j = 0; j < 26; j++) {
                if (halfCounts[j] < prefixCounts[j]) {
                    possible = false;
                    break;
                }
            }
            
            if (!possible) {
                continue;
            }
            
            // Calculate remaining counts after using prefix target[0...i-1]
            int[] remCounts = new int[26];
            for (int j = 0; j < 26; j++) {
                remCounts[j] = halfCounts[j] - prefixCounts[j];
            }
            
            // Find the smallest character greater than target.charAt(i)
            int targetIdx = target.charAt(i) - 'a';
            int bestIdx = -1;
            for (int j = targetIdx + 1; j < 26; j++) {
                if (remCounts[j] > 0) {
                    bestIdx = j;
                    break;
                }
            }
            
            if (bestIdx != -1) {
                // Construct the lexicographically smallest half
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + bestIdx));
                remCounts[bestIdx]--;
                
                // Fill the rest in ascending order
                for (int j = 0; j < 26; j++) {
                    while (remCounts[j] > 0) {
                        sb.append((char) ('a' + j));
                        remCounts[j]--;
                    }
                }
                
                String half = sb.toString();
                return half + mid + new StringBuilder(half).reverse().toString();
            }
        }
        
        return "";
    }
}
