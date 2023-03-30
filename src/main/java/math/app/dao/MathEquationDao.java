package math.app.dao;

import java.util.List;
import java.util.Optional;
import math.app.model.MathEquation;

public interface MathEquationDao {
    MathEquation save(MathEquation mathEquation);

    MathEquation update(MathEquation mathEquation);

    List<MathEquation> findByRoot(Double root);

    Optional<MathEquation> findByEquation(String equation);

    List<MathEquation> findAllWhereRootCountEqualsOne();
}
