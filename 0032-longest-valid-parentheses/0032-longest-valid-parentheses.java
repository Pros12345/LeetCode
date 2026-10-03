import java.util.Stack;

public class Solution {
    public int longestValidParentheses(String s) {
        // Edge case: empty or null string
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        // Push -1 as the base boundary for length calculation
        stack.push(-1);
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // Store the index of the opening bracket
                stack.push(i);
            } else {
                // Encountered ')', pop the matching/previous element
                stack.pop();

                if (stack.isEmpty()) {
                    // Current ')' is unmatched; it becomes the new base boundary
                    stack.push(i);
                } else {
                    // Calculate length and update max
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }
}
