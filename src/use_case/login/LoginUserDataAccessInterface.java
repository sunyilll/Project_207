package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsById(String userid);
    User get(String userid);
    void saveCurrentUser(String userid);
}
