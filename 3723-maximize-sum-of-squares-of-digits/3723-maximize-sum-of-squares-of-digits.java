public class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Base edge case: If the sum is impossible to achieve with the given number of digits
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Calculate the maximum number of 9s we can use
        int nines = sum / 9;
        int remainder = sum % 9;
        
        // Step 1: Append all the 9s at the front to maximize both score and value
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        // Step 2: Append the leftover remainder if it's greater than 0
        if (remainder > 0) {
            sb.append(remainder);
        }
        
        // Step 3: Fill up the remaining positions with '0's to satisfy the 'num' digits requirement
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
