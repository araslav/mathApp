package math.app.lib.validation;

import java.io.IOException;
import math.app.lib.validation.service.BracketsValidationImpl;
import math.app.lib.validation.service.Validation;
import math.app.lib.validation.service.OperationValidationImpl;

public class MathEquationValidation {
    public static void validateBrackets(String script) throws IOException {
        Validation bracketsValidation = new BracketsValidationImpl(script);
        if (!bracketsValidation.isValid()) {
            throw new IOException("Check brackets!");
        }
    }

    public static void validateMathOperations(String script) throws IOException {
        Validation operationValidation = new OperationValidationImpl(script);
        if (operationValidation.isValid()) {
            throw new IOException("Check mathematical operations!");
        }
    }
}
