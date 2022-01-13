package calculator;

import java.util.EmptyStackException;
import java.util.Stack;

public class RPN {
    private Stack<String> stack;
    private StringBuilder output;
    private Variables variables;

    public RPN() {
        stack = new Stack<>();
        output = new StringBuilder();
        variables = Variables.getInstance();
    }

    private int priorityOperator(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")){
            return 2;
        } else {
            return -1;
        }
    }

    public StringBuilder convert(String input) {
        output.setLength(0);
        stack.clear();
        String[] split = input.split("[ ]+");
                for (int i = 0; i < split.length; i++) {
            if (split[i].matches("[0-9]+")) {
                output.append(split[i] + " ");
            } else if (split[i].matches("[A-Za-z]+")) {
                output.append(variables.getValue(split[i])+ " ");
            } else if (split[i].equals("(")) {
                stack.push(split[i]);
            } else if (split[i].equals(")")) {
                String temp = stack.pop();
                while(!temp.equals("(")){
                    output.append(temp + " ");
                    try {
                        temp = stack.pop();
                    } catch (EmptyStackException e) {
                        output = new StringBuilder("Invalid expression");
                        break;
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(split[i]);
                } else {
                    while(!stack.isEmpty() && priorityOperator(stack.peek()) >= priorityOperator(split[i])){
                        output.append(stack.pop() + " ");
                    }
                    stack.push(split[i]);
                }
            }
        }

        while(!stack.isEmpty()) {
            if (stack.peek().equals(")") || stack.peek().equals("(")) {
                output = new StringBuilder("Invalid expression");
                break;
            } else {
            output.append(stack.pop() + " ");
            }
        }
        return output;
    }
}
