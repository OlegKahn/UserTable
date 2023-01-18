package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    {
        try {
            util = new Util(false);
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
                    "user(id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name CHAR(30), lastName CHAR(30), age TINYINT, " +
                    "PRIMARY KEY (id));");
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
                    "user(name, lastName, age) " +
                    "value('"+name+"', '"+lastName+"', '"+age+"')");
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            util.getStatement().executeUpdate("DELETE FROM user WHERE id="+id+";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (ResultSet rs = util.getStatement().executeQuery("SELECT * FROM user")){
            while (rs.next()) {
                User user =new User(
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age"));
                user.setId(rs.getLong("id"));
                list.add(user);
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
