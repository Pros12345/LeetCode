import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            for (String word : dictionary) {
                if (canMatchWithinTwoEdits(query, word)) {
                    result.add(query);
                    break; // Move to the next query once a match is found
                }
            }
        }
        
        return result;
    }
    
    private boolean canMatchWithinTwoEdits(String s1, String s2) {
        int diffCount = 0;
        int len = s1.length();
        
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
                if (diffCount > 2) {
                    return false; 
                }
            }
        }
        
        return true;
    }
}
