package calculator;

import java.util.Optional;
import java.util.Scanner;

public class Application {
    private Validate validate;
    private RPN rpn;
    private ResultCalculate resultCalculate;
    private Scanner scanner;
    private String output;
    private Variables variables;

    public Application() {
        validate = new Validate();
        resultCalculate = new ResultCalculate();
        rpn = new RPN();
        scanner = new Scanner(System.in);
        variables = Variables.getInstance();
    }

    public void run() {
        do {
            output = scanner.nextLine();
            if (output.startsWith("/")) {
                if (output.equals("/exit")) {
                    break;
                } else if (output.equals("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    System.out.println("Unknown command");
                }
            } else if (output.contains("=")) {
                variables.addVariable(output);
            } else if (!output.isEmpty()) {
                if (output.matches("^-?[0-9]+$")) {
                    System.out.println(output);
                } else if (output.matches("^[A-Za-z]+$")) {
                    Optional <String> value = Optional.ofNullable(variables.getValue(output.trim()));
                    value.ifPresentOrElse(System.out::println,
                            () -> System.out.println("Unknown variable"));
                } else {
                String validatedOutput = validate.start(output);
                    if (validatedOutput.contains("Invalid expression")) {
                        System.out.println("Invalid expression");
                    } else {
                        StringBuilder rpnOutput = rpn.convert(validatedOutput);
                        if (String.valueOf(rpnOutput).equals("Invalid expression")) {
                            System.out.println(rpnOutput);
                        } else {
                            resultCalculate.run(String.valueOf(rpnOutput));
                        }
                    }
                }
            }
        }while (true);
        System.out.println("Bye!");
    }
}
