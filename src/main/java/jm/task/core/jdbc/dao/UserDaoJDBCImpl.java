package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;

    {
        try {
            util = new Util();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            util.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    "user(userId BIGINT NOT NULL AUTO_INCREMENT," +
                    "userName CHAR(30), userLastName CHAR(30), userAge TINYINT, " +
                    "PRIMARY KEY (userId));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            util.getStatement().executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            util.getStatement().executeUpdate("insert into " +
                    "user(userName, userLastName, userAge) " +
                    "value(\'"+name+"\', \'"+lastName+"\', \'"+age+"\')");
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            util.getStatement().executeUpdate("DELETE FROM user WHERE userId="+id+";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = util.getStatement().executeQuery("SELECT * FROM user");
            while (rs.next()) {
                list.add(new User(rs.getLong("userId"),
                        rs.getString("userName"),
                        rs.getString("userLastName"),
                        rs.getByte("userAge")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            util.getStatement().executeUpdate("DELETE FROM user;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
