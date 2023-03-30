package math.app.service;

import java.util.List;
import math.app.dao.MathEquationDao;
import math.app.model.MathEquation;

public class MathEquationServiceImpl implements MathEquationService {
    private final MathEquationDao mathEquationDao;

    public MathEquationServiceImpl(MathEquationDao mathEquationDao) {
        this.mathEquationDao = mathEquationDao;
    }

    @Override
    public MathEquation save(MathEquation mathEquation) {
        if(mathEquation.getId() == null) {
            return mathEquationDao.save(mathEquation);
        } else {
            return mathEquationDao.update(mathEquation);
        }
    }

    @Override
    public MathEquation update(MathEquation mathEquation) {
        return mathEquationDao.update(mathEquation);
    }

    @Override
    public List<MathEquation> findByRoot(Double root) {
        return mathEquationDao.findByRoot(root);
    }

    @Override
    public MathEquation findByEquation(String equation) {
        return mathEquationDao.findByEquation(equation)
                .orElse(new MathEquation());
    }
}
