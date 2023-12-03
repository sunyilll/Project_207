package use_case;

import entity.User;

public interface GetUserDataAccessInterface {
    User get(String userid);
    User getCurrentUser();
    void save(User user);
}
