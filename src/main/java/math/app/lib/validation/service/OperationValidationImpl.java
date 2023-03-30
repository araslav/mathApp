package math.app.lib.validation.service;

import java.util.regex.Pattern;

public class OperationValidationImpl implements Validation {
    private final String script;
    private static final String REGEXP = "(\\+|-)(\\*|/)";

    public OperationValidationImpl(String script) {
        this.script = script;
    }

    public boolean isValid() {
        return Pattern.compile(REGEXP)
                .matcher(script).find();
    }
}
