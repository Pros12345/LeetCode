import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack to store operators: ',' or '{'
        Stack<Character> opStack = new Stack<>();
        // Stack to store operands: Set of strings
        Stack<Set<String>> setStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '{') {
                // If the previous character implies a concatenation, push a multiply signal
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLowerCase(expression.charAt(i - 1)))) {
                    opStack.push('*');
                }
                opStack.push(ch);
            } else if (ch == ',') {
                // Process existing concatenations inside the current brace level first
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
                opStack.push(ch);
            } else if (ch == '}') {
                // Process everything up to the matching '{'
                while (!opStack.isEmpty() && opStack.peek() != '{') {
                    evaluate(opStack, setStack);
                }
                opStack.pop(); // Remove '{'

                // After processing the group, check if it needs to be concatenated with an upcoming element
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
            } else if (Character.isLowerCase(ch)) {
                // If the previous character implies a concatenation, push a multiply signal
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLowerCase(expression.charAt(i - 1)))) {
                    opStack.push('*');
                }

                // Create a singleton set for the character
                Set<String> currentSet = new HashSet<>();
                currentSet.add(String.valueOf(ch));
                setStack.push(currentSet);

                // Collapse immediate concatenations
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
            }
        }

        // Process any remaining operations left in the stack
        while (!opStack.isEmpty()) {
            evaluate(opStack, setStack);
        }

        // The remaining set contains all unique words. Sort them.
        List<String> result = new ArrayList<>(setStack.peek());
        Collections.sort(result);
        return result;
    }

    private void evaluate(Stack<Character> opStack, Stack<Set<String>> setStack) {
        char op = opStack.pop();
        Set<String> set2 = setStack.pop();
        Set<String> set1 = setStack.pop();
        Set<String> result = new HashSet<>();

        if (op == '*') {
            // Concatenation (Cartesian Product)
            for (String str1 : set1) {
                for (String str2 : set2) {
                    result.add(str1 + str2);
                }
            }
        } else if (op == ',') {
            // Union
            result.addAll(set1);
            result.addAll(set2);
        }

        setStack.push(result);
    }
}
