package main.java.use_case.signup;

import main.java.entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

}
