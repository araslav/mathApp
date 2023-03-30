package math.app.lib.validation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationValidationImplTest {
    @Test
    public void isValid_ok() {
        Validation validation = new OperationValidationImpl("4*-7");
        Assertions.assertTrue(validation.isValid());
    }

    @Test
    public void isValid_NotOk() {
        Validation validation = new OperationValidationImpl("3+*4");
        Assertions.assertFalse(validation.isValid());
    }
}
