package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util;
    private Session session;

    {
        try {
            util = new Util(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS " +
                "user(id BIGINT NOT NULL AUTO_INCREMENT," +
                "name CHAR(30), lastName CHAR(30), age TINYINT, " +
                "PRIMARY KEY (id));").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
