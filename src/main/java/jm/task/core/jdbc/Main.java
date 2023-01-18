package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ioan", "Brave", (byte) 20);
        userService.saveUser("Zim", "Crazy", (byte) 27);
        userService.saveUser("Earn", "Greedy", (byte) 18);
        userService.saveUser("Weed", "Fool", (byte) 20);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
