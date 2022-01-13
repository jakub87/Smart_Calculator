package calculator;

public class Validate {
    private StringBuilder validatedOutput;
    private StringBuilder operator;

    public Validate() {
        validatedOutput = new StringBuilder();
        operator = new StringBuilder();
    }

    public String start(String input) {
        validatedOutput.setLength(0);
        operator.setLength(0);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {
                if (operator.length() == 0) {
                    operator.append(input.charAt(i));
                } else {
                    if (input.charAt(i) == operator.charAt(operator.length() - 1)) {
                        operator.append(input.charAt(i));
                    }
                }
            } else if (input.charAt(i) != ' ') {
                if (operator.length() == 1) {
                    validatedOutput.append(" " + operator.charAt(0) + " ");
                } else if (operator.length() > 1) {
                    if (operator.charAt(0) == '/' || operator.charAt(0) == '*') {
                        validatedOutput.append("Invalid expression");
                        break;
                    } else if (operator.charAt(0) == '+') {
                        validatedOutput.append(" + ");
                    } else {
                        if (operator.length() % 2 == 0) {
                            validatedOutput.append(" + ");
                        } else {
                            validatedOutput.append(" - ");
                        }
                    }
                }
                operator.setLength(0);
                if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                    validatedOutput.append(" " + input.charAt(i) + " ");
                } else {
                    validatedOutput.append(input.charAt(i));
                }
            }
        }
        return String.valueOf(validatedOutput);
    }
}

