package use_case.To_signup;

import entity.User;

public interface ToSignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

}
