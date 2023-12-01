package main.java.use_case.to_signup;

import main.java.entity.User;

public interface ToSignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

}
