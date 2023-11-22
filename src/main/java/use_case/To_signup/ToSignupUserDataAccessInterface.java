package main.java.use_case.To_signup;

import main.java.entity.User;

public interface ToSignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

}
