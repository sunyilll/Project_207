package use_case.go_to_personal_profile;

import entity.User;

public class GoToPersonalProfileInputData {
    final private String userid;

    public GoToPersonalProfileInputData(String userid) {
        this.userid = userid;
    }

    boolean isUserLoggedIn() {
        return userid != null;
    }

    public String getUserId() {
        return userid;
    }
}
