package math.app.dao;

import java.util.List;
import java.util.Optional;
import math.app.exepcion.DataProcessingException;
import math.app.model.MathEquation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MathEquationDaoImpl extends AbstractDao implements MathEquationDao {
    public MathEquationDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public MathEquation save(MathEquation entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save mathEquation to DB " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MathEquation update(MathEquation entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            MathEquation merge = session.merge(entity);
            transaction.commit();
            return merge;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update a mathEquation: " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<MathEquation> findByEquation(String equation) {
        try (Session session = factory.openSession()) {
            Query<MathEquation> getMathEquation =
                    session.createQuery("from MathEquation  "
                                    + "where equation = :equation",
                            MathEquation.class);
            getMathEquation.setParameter("equation", equation);
            return Optional.ofNullable(getMathEquation.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a MathEquation by equation: " + equation, e);
        }
    }

    @Override
    public List<MathEquation> findAllWhereRootCountEqualsOne() {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("SELECT me.* " +
                            "FROM math_app.math_equation me " +
                            "INNER JOIN math_app.math_equations_roots mer ON (mer.math_equation_id = me.id) " +
                            "INNER JOIN math_app.roots r ON (r.id = mer.root_id) " +
                            "GROUP BY me.equation " +
                            "HAVING COUNT(mer.root_id) = 1",
                            MathEquation.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get MathEquation", e);
        }
    }

    @Override
    public List<MathEquation> findByRoot(Double root) {
        try (Session session = factory.openSession()) {
            Query<MathEquation> query = session.createQuery("from MathEquation me "
                    + "JOIN FETCH me.root r "
                    + "WHERE r.root = :root", MathEquation.class);
            query.setParameter("root", root);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get MathEquation by root: " + root, e);
        }
    }
}
