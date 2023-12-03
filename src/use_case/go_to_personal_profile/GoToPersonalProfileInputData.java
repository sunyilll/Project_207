package use_case.go_to_personal_profile;

import entity.User;

public class GoToPersonalProfileInputData {
    final private entity.User User;

    public GoToPersonalProfileInputData(User user) {
        this.User = user;
    }

    boolean isUserLoggedIn() {
        return User != null;
    }

    public User getUser() {
        return User;
    }
}
