package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(int id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();

        }
        return session.get(User.class, Long.valueOf(id));
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(User user) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            User currentUser = session.get(User.class, user.getId());
            if (user != null) {
                session.delete(currentUser);
            }
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
            User currentUser = session.get(User.class, user.getId());
            if (user != null) {
                session.delete(currentUser);
            }
            session.close();
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        //TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u JOIN FETCH u.car");
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelAndSerial(String model, int serial) {
        String hql = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :serial";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("serial", serial);
        return query.getSingleResult();
    }

}
