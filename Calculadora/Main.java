import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a notação da expressão (infixa, posfixa, prefixa):");
        String notation = scanner.nextLine().trim().toLowerCase();

        System.out.println("Digite a expressão:");
        String expression = scanner.nextLine().trim();

        String infix = "";
        String postfix = "";
        String prefix = "";
        double result = 0;

        try {
            switch (notation) {
                case "infixa":
                    infix = expression;
                    postfix = ExpressionConverter.infixToPostfix(infix);
                    prefix = ExpressionConverter.infixToPrefix(infix);
                    result = ExpressionEvaluator.evaluateInfix(infix);
                    break;
                case "posfixa":
                    postfix = expression;
                    infix = ExpressionConverter.postfixToInfix(postfix);
                    prefix = ExpressionConverter.infixToPrefix(infix);
                    result = ExpressionEvaluator.evaluatePostfix(postfix);
                    break;
                case "prefixa":
                    prefix = expression;
                    infix = ExpressionConverter.prefixToInfix(prefix);
                    postfix = ExpressionConverter.infixToPostfix(infix);
                    result = ExpressionEvaluator.evaluatePrefix(prefix);
                    break;
                default:
                    System.out.println("Notação inválida.");
                    scanner.close();
                    return;
            }

            System.out.println("Resultado: " + result);
            System.out.println("Infixa: " + infix);
            System.out.println("Pós-fixa: " + postfix);
            System.out.println("Pré-fixa: " + prefix);
        } catch (Exception e) {
            System.out.println("Erro ao processar a expressão: " + e.getMessage());
        }

        scanner.close();
    }
}
