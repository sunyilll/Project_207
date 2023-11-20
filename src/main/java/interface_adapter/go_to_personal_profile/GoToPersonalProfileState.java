package main.java.interface_adapter.go_to_personal_profile;

import main.java.entity.User;

public class GoToPersonalProfileState {
    private User user;

    public GoToPersonalProfileState(GoToPersonalProfileState copy) {
        this.user = copy.getUser();
    }
    public GoToPersonalProfileState() {}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
