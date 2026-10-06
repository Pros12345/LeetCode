class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // This open parenthesis will eventually need a closing one
                closeNeeded++;
            } else {
                // We encountered a closing parenthesis ')'
                if (closeNeeded > 0) {
                    // Match it with a previously open parenthesis
                    closeNeeded--;
                } else {
                    // No open parenthesis is available, so we must add one
                    openNeeded++;
                }
            }
        }

        // Total additions required
        return openNeeded + closeNeeded;
    }
}
