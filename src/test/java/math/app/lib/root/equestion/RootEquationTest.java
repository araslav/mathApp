package math.app.lib.root.equestion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RootEquationTest {
    @Test
    public void eval_Ok() {
        Double expected = 22d;
        Double root = 6d;
        RootEquation rootEquation = new RootEquation("2*(x+5)", root);
        Assertions.assertEquals(rootEquation.eval(), expected);
    }

    @Test
    public void eval_NotOk() {
        Double expected = 22d;
        Double root = 5d;
        RootEquation rootEquation = new RootEquation("2*(x+4)", root);
        Assertions.assertNotEquals(rootEquation.eval(), expected);
    }

    @Test
    public void isRootEquation_Ok() {
        Double root = 6d;
        RootEquation rootEquation = new RootEquation("2*(x+5)=22", root);
        Assertions.assertTrue(rootEquation.isRootEquation());
    }

    @Test
    public void isRootEquation_NotOk() {
        Double root = 5d;
        RootEquation rootEquation = new RootEquation("2*(x+5)=22", root);
        Assertions.assertFalse(rootEquation.isRootEquation());
    }
}