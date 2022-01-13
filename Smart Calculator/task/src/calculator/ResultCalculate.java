package calculator;

import java.math.BigInteger;
import java.util.Stack;

public class ResultCalculate {

    private BigInteger calculate (BigInteger number1, BigInteger number2, String operator) {
        if (operator.contains("+")) {
            return number2.add(number1);
        } else if (operator.contains("-")) {
            return number2.subtract(number1);
        } else if (operator.contains("*")) {
            return number2.multiply(number1);
        } else {
            return number2.divide(number1);
        }
    }

    public void run(String input) {
        String[] split = input.split("[ ]+");
        Stack<BigInteger> stack = new Stack<>();
         for (int i = 0; i < split.length; i++) {
            if (split[i].matches("-?[0-9]+")) {
               stack.push(new BigInteger(split[i]));
            } else {
                BigInteger result = calculate(stack.pop(), stack.pop(), split[i]);
                stack.push(result);
            }
        }
        System.out.println(stack.pop());
    }
}
