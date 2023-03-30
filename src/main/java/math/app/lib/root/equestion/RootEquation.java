package math.app.lib.root.equestion;

import net.objecthunter.exp4j.ExpressionBuilder;

public class RootEquation {
    private final String[] equationList;
    private final Double root;
    private static final String SPLIT_DIVIDER = "=";
    private static final String UNKNOWN = "x";


    public RootEquation(String equation, Double root) {
        this.root = root;
        equationList = split(equation);
    }

    public boolean isRootEquation() {
        return eval().equals(Double.valueOf(equationList[1]));
    }

    public Double eval() {
        return new ExpressionBuilder(equationList[0])
                .variables(UNKNOWN)
                .build()
                .setVariable(UNKNOWN, root).evaluate();
    }

    private String[] split(String equation) {
        return equation.split(SPLIT_DIVIDER);
    }
}
