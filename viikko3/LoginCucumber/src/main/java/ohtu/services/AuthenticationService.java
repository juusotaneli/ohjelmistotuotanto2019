package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;


import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;
    private int username_min_length = 3;
    private int password_min_length = 8;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }
        
        if (invalid(username, password)) {
            return false;

        }
        userDao.add(new User(username, password));

        return true;
        
    }
    private boolean userNameisValid (String username) {
        if (username.length() >= username_min_length) {
            return userNameNotOnlyLetters(username);
        }
        return false;

    }
    private boolean userNameNotOnlyLetters (String username) {
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetter(username.charAt(i))) {
                return false;
            }
        }
        return true;

    }
    private boolean passwordNotOnlyLetters(String password) {
        boolean foundLetters = false;
        boolean foundDigits = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                foundDigits = true;
                break;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                foundLetters = true;
                break;
            }
        }
        if (foundLetters == true && foundDigits == true) {
            return true;
        }
        return false;
    

    }
    private boolean passwordIsValid (String password) {
        if (password.length() >= password_min_length) {
            return passwordNotOnlyLetters(password);
        }
        return false;

    }
    private boolean invalid(String username, String password) {
        if (userNameisValid(username) && passwordIsValid(password)) {
            return false;
        }

        return true;
    }
}
