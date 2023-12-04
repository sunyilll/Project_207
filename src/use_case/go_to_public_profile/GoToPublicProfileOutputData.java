package use_case.go_to_public_profile;

import entity.User;

public class GoToPublicProfileOutputData {
    public User user;

    public GoToPublicProfileOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
