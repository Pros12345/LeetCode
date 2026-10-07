import java.util.*;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        // Queue for BFS and Set to avoid processing duplicate strings
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(s);
        visited.add(s);

        boolean foundValidAtThisLevel = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all strings at the current BFS level
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();

                // If it's valid, add to results and signal to stop generating next level
                if (isValid(current)) {
                    result.add(current);
                    foundValidAtThisLevel = true;
                }

                // If we already found a valid string at this level, 
                // we don't need to generate further states for the next level.
                if (foundValidAtThisLevel) continue;

                // Generate all possible next states by removing one parenthesis
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    
                    // Skip letters, only remove brackets
                    if (c != '(' && c != ')') continue;

                    // Form a new candidate string by skipping the character at index j
                    String candidate = current.substring(0, j) + current.substring(j + 1);

                    if (!visited.contains(candidate)) {
                        visited.add(candidate);
                        queue.add(candidate);
                    }
                }
            }

            // Since BFS guarantees minimum removals, if we found any valid strings 
            // at this level, we can stop exploring completely.
            if (foundValidAtThisLevel) {
                break;
            }
        }

        return result;
    }

    // Helper method to check if a string of parentheses is valid
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing brackets than opening ones
            }
        }
        return count == 0;
    }
}
