class Solution {
    public boolean stoneGameIX(int[] stones) {
        int c0 = 0, c1 = 0, c2 = 0;
        for (int s : stones) {
            int r = s % 3;
            if (r == 0) c0++;
            else if (r == 1) c1++;
            else c2++;
        }
        
        // Scenario 1: Even number of 0-remainder stones
        if (c0 % 2 == 0) {
            return c1 >= 1 && c2 >= 1;
        }
        
        // Scenario 2: Odd number of 0-remainder stones
        return Math.abs(c1 - c2) > 2;
    }
}
