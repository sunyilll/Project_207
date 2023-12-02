package interface_adapter.go_to_personal_profile;

import entity.User;

public class GoToPersonalProfileState {
    private User user;
    private boolean goToPersonalProfileSuccess = false;

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
    public boolean isGoToPersonalProfileSuccess() {
        return goToPersonalProfileSuccess;
    }
    public void setGoToPersonalProfileSuccess(boolean goToPersonalProfileSuccess) {
        this.goToPersonalProfileSuccess = goToPersonalProfileSuccess;
    }
}
