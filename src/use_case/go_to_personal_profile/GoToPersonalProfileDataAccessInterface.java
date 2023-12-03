package use_case.go_to_personal_profile;

import entity.User;

public interface GoToPersonalProfileDataAccessInterface {
    User get(String userid);
}
