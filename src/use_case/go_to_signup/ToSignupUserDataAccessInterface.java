package use_case.go_to_signup;

import entity.User;

public interface ToSignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

}
