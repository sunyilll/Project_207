package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsById(String userid);

    void save(User user);

}
