package math.app.lib.validation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BracketsValidationImplTest {
    @Test
    public void scriptWithoutBrackets_Ok() {
        Validation validation = new BracketsValidationImpl("5/x+2");
        Assertions.assertTrue(validation.isValid());
    }

    @Test
    public void validBrackets_Ok() {
        Validation validation = new BracketsValidationImpl("(5/x+2)");
        Assertions.assertTrue(validation.isValid());
    }

    @Test
    public void validBrackets_NotOk() {
        Validation validation = new BracketsValidationImpl("(5/x)+2)");
        Assertions.assertFalse(validation.isValid());
    }

    @Test
    public void validBrackets_OneOpen_NotOk() {
        Validation validation = new BracketsValidationImpl("(5/x+2");
        Assertions.assertFalse(validation.isValid());
    }

    @Test
    public void validBrackets_OneClose_NotOk() {
        Validation validation = new BracketsValidationImpl("5/x)+2");
        Assertions.assertFalse(validation.isValid());
    }
}
