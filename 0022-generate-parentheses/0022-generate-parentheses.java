import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Use StringBuilder for efficient string manipulation during backtracking
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: If the current string reaches the maximum required length (2 * n)
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Rule 1: We can always add an opening parenthesis if we haven't hit the limit 'n'
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack step
        }

        // Rule 2: We can only add a closing parenthesis if it matches a preceding unclosed open parenthesis
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack step
        }
    }
}
