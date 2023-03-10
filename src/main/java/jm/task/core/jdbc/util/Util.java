package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/USERS";


    public static Connection myJdbcConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        return connection;
    }
    public static SessionFactory myHibernateSessionFactory() {
            Properties prop= new Properties();
            prop.setProperty("hibernate.connection.url", connectionUrl);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.connection.username", userName);
            prop.setProperty("hibernate.connection.password", password);
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("show_sql", "true");
            prop.setProperty("hibernate.current_session_context_class", "thread");
            SessionFactory sessionFactory = new Configuration().addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            return sessionFactory;
        }

}
