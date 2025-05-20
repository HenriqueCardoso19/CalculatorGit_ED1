import java.util.Stack;

public class ExpressionEvaluator {

    public static double evaluateInfix(String infix) {
        String postfix = ExpressionConverter.infixToPostfix(infix);
        return evaluatePostfix(postfix);
    }

    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (Utils.isOperator(c)) {
                double b = stack.pop();
                double a = stack.pop();
                double res = Utils.applyOperator(a, b, c);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    public static double evaluatePrefix(String prefix) {
        Stack<Double> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (Utils.isOperator(c)) {
                double a = stack.pop();
                double b = stack.pop();
                double res = Utils.applyOperator(a, b, c);
                stack.push(res);
            }
        }
        return stack.pop();
    }
}
