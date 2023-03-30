package math.app.dao;

import java.util.List;
import java.util.Optional;
import math.app.exepcion.DataProcessingException;
import math.app.model.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RootDaoImpl extends AbstractDao implements RootDao {
    public RootDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Root save(Root entity) {
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
            throw new DataProcessingException("Can't save Root to DB " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Root> findByRoot(Double root) {
        try (Session session = factory.openSession()) {
            Query<Root> getRoot =
                    session.createQuery("from Root  "
                                    + "where root = :root",
                            Root.class);
            getRoot.setParameter("root", root);
            return Optional.ofNullable(getRoot.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a root: " + root, e);
        }
    }

    @Override
    public List<Root> getAll() {
        try (Session session = factory.openSession()) {
            Query<Root> getAllUsersQuery = session.createQuery("from Root", Root.class);
            return getAllUsersQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all Roots from BD", e);
        }
    }
}
