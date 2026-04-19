package service;

import dao.UserDAO;
import model.User;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public void register(String name, String email, String password) {
        User user = new User(name, email, password);
        if (userDAO.registerUser(user)) {
            System.out.println("User Registered Successfully!");
        } else {
            System.out.println("Registration Failed!");
        }
    }

    public boolean login(String email, String password) {
        return userDAO.login(email, password);
    }
}