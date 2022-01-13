package calculator;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private Map<String, String> variables;
    private static Variables instance;

    private Variables() {
        variables = new HashMap<>();
    }

    public static Variables getInstance() {
        if (instance == null) {
            instance = new Variables();
        }
        return instance;
    }

    public void addVariable(String output) {
        if (output.split("=").length == 2) {
            String key = output.split("=")[0].trim();
            String value = output.split("=")[1].trim();
                if (key.matches("(?=.*?[^A-Za-z]).{1,}")) {
                    System.out.println("Invalid identifier");
                } else if (value.matches("^(?=.*?[a-zA-Z])(?=.*?[0-9]).{2,}$")) {
                    System.out.println("Invalid assignment");
                } else {
                    if (value.matches("-?\\d+")) {
                        variables.remove(key);
                        variables.put(key, value);
                    } else {
                        if (variables.containsKey(value)) {
                            variables.remove(key);
                            variables.put(key, variables.get(value));
                        } else {
                            System.out.println("Unknown variable");
                        }
                    }
                }
        } else {
            System.out.println("Invalid assignment");
        }
    }

    public String getValue(String variable) {
        return variables.get(variable);
    }
}
