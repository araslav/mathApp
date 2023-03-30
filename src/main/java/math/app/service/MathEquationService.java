package math.app.service;

import java.util.List;
import math.app.model.MathEquation;

public interface MathEquationService {
    MathEquation save(MathEquation mathEquation);

    MathEquation update(MathEquation mathEquation);

    List<MathEquation> findByRoot(Double root);

    MathEquation findByEquation(String equation);
}
