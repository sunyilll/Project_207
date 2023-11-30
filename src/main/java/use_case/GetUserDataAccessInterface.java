package main.java.use_case;

import main.java.entity.User;

public interface GetUserDataAccessInterface {
    User get(String userid);
}
