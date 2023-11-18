package main.java.use_case.go_to_personal_profile;

import main.java.entity.User;

public class GoToPersonalProfileInputData {
    final private main.java.entity.User User;

    public GoToPersonalProfileInputData(User user) {
        this.User = user;
    }

    String getNickname() {
        return User.getNickname();
    }
}
