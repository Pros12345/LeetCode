import java.util.*;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null)
            return result;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(s);
        visited.add(s);
        boolean foundValidAtThisLevel = false;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                if (isValid(current)) {
                    result.add(current);
                    foundValidAtThisLevel = true;
                }
                if (foundValidAtThisLevel)
                    continue;
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    if (c != '(' && c != ')')
                        continue;
                    String candidate = current.substring(0, j) + current.substring(j + 1);
                    if (!visited.contains(candidate)) {
                        visited.add(candidate);
                        queue.add(candidate);
                    }
                }
            }
            if (foundValidAtThisLevel) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }
}
