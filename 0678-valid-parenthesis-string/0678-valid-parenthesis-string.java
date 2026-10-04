class Solution {
    public boolean checkValidString(String s) {
        // cmin represents the minimum possible open left parentheses '('
        // cmax represents the maximum possible open left parentheses '('
        int cmin = 0, cmax = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin--;
                cmax--;
            } else { // c == '*'
                cmin--; // If '*' is treated as ')'
                cmax++; // If '*' is treated as '('
            }
            
            // If cmax becomes negative, there are too many closing brackets ')'
            // even if we treat all '*' as opening brackets '('.
            if (cmax < 0) {
                return false;
            }
            
            // cmin cannot be negative because we can't have a negative number 
            // of open brackets. If it drops below 0, it means we chose to convert 
            // too many '*' to ')' earlier. We must reset it to 0 (meaning we treat them as "").
            cmin = Math.max(cmin, 0);
        }
        
        // The string is valid if we can perfectly close all open brackets (cmin == 0)
        return cmin == 0;
    }
}
