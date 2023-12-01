package use_case.go_to_personal_profile;

import entity.User;

public class GoToPersonalProfileOutputData {
    public User user;

    public GoToPersonalProfileOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
