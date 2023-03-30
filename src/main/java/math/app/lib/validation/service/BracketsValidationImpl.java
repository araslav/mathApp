package math.app.lib.validation.service;

import java.util.Stack;

public class BracketsValidationImpl implements Validation {
    private final Stack<Character> stack = new Stack<>();
    private final String string;

    public BracketsValidationImpl(String string) {
        this.string = string;
    }

    public boolean isValid() {
        char[] chars = string.toCharArray();

        for (char aChar : chars) {
            if (aChar == ')' || aChar == '(') {
                if (aChar == '(') {stack.add(aChar);};
                if (aChar == ')' && (stack.empty() || stack.pop() != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}