package math.app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import math.app.dao.MathEquationDao;
import math.app.dao.MathEquationDaoImpl;
import math.app.dao.RootDao;
import math.app.dao.RootDaoImpl;
import math.app.lib.root.equestion.RootEquation;
import math.app.lib.validation.MathEquationValidation;
import math.app.model.MathEquation;
import math.app.model.Root;
import math.app.service.MathEquationService;
import math.app.service.MathEquationServiceImpl;
import math.app.service.RootService;
import math.app.service.RootServiceImpl;
import static math.app.util.HibernateUtil.getSessionFactory;

public class Main {
    public static void main(String[] args) {
        MathEquationDao mathEquationDao = new MathEquationDaoImpl(getSessionFactory());
        RootDao rootDao = new RootDaoImpl(getSessionFactory());
        MathEquationService mathEquationService = new MathEquationServiceImpl(mathEquationDao);
        RootService rootService = new RootServiceImpl(rootDao);

        System.out.println("Hi");
        Scanner scanner = new Scanner(System.in);
        String s = null;

        boolean iterate = true;
        while (iterate) {
            System.out.println("Enter mathematical equations:");
            s = scanner.nextLine();
            try {
                MathEquationValidation.validateBrackets(s);
                MathEquationValidation.validateMathOperations(s);
                iterate = false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (s.equals("")) {return;}

        MathEquation mathEquation = mathEquationService.findByEquation(s);
        mathEquation.setEquation(s);

        System.out.println("Enter Root of the equation or Push Enter:");
        String rootEquation = scanner.nextLine();

        Root root;
        if (!rootEquation.equals("")) {
            RootEquation rootEquationManager = new RootEquation(mathEquation.getEquation(),
                    Double.valueOf(rootEquation));
            if (rootEquationManager.isRootEquation()) {
                System.out.println("Root of the equation is valid!");
                root = rootService.findByRoot(Double.valueOf(rootEquation));
                root.setRoot(Double.valueOf(rootEquation));
                mathEquation.setRoot(List.of(root));
            } else {
                System.out.println("Didn't valid root of the equation!");
            }
        }

        MathEquation newMathEquation = mathEquationService.save(mathEquation);

        if (newMathEquation.getRoot() != null) {
            System.out.println("MathEquation and Root saved!");
        } else {
            System.out.println("MathEquation saved!");
        }
    }
}
