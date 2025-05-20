import java.util.Stack;

public class ExpressionConverter {

    public static String infixToPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else if (Utils.isOperator(c)) {
                while (!stack.isEmpty() && Utils.precedence(c) <= Utils.precedence(stack.peek()))
                    result.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            result.append(stack.pop());
        return result.toString();
    }

    public static String infixToPrefix(String infix) {
        StringBuilder input = new StringBuilder(infix);
        input.reverse();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                input.setCharAt(i, ')');
            } else if (c == ')') {
                input.setCharAt(i, '(');
            }
        }
        String postfix = infixToPostfix(input.toString());
        StringBuilder prefix = new StringBuilder(postfix);
        prefix.reverse();
        return prefix.toString();
    }

    public static String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Utils.isOperator(c)) {
                String b = stack.pop();
                String a = stack.pop();
                String expr = "(" + a + c + b + ")";
                stack.push(expr);
            } else {
                stack.push(String.valueOf(c));
            }
        }
        return stack.pop();
    }

    public static String prefixToInfix(String prefix) {
        Stack<String> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (Utils.isOperator(c)) {
                String a = stack.pop();
                String b = stack.pop();
                String expr = "(" + a + c + b + ")";
                stack.push(expr);
            } else {
                stack.push(String.valueOf(c));
            }
        }
        return stack.pop();
    }
}
