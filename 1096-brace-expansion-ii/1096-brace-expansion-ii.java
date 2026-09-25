import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> opStack = new Stack<>();
        Stack<Set<String>> setStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '{') {
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLowerCase(expression.charAt(i - 1)))) {
                    opStack.push('*');
                }
                opStack.push(ch);
            } else if (ch == ',') {
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
                opStack.push(ch);
            } else if (ch == '}') {
                while (!opStack.isEmpty() && opStack.peek() != '{') {
                    evaluate(opStack, setStack);
                }
                opStack.pop();
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
            } else if (Character.isLowerCase(ch)) {
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLowerCase(expression.charAt(i - 1)))) {
                    opStack.push('*');
                }
                Set<String> currentSet = new HashSet<>();
                currentSet.add(String.valueOf(ch));
                setStack.push(currentSet);
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluate(opStack, setStack);
                }
            }
        }
        while (!opStack.isEmpty()) {
            evaluate(opStack, setStack);
        }
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
            for (String str1 : set1) {
                for (String str2 : set2) {
                    result.add(str1 + str2);
                }
            }
        } else if (op == ',') {
            result.addAll(set1);
            result.addAll(set2);
        }

        setStack.push(result);
    }
}
