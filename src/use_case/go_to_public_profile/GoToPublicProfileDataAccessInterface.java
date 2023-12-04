package use_case.go_to_public_profile;

import entity.User;

public interface GoToPublicProfileDataAccessInterface {
    User get(String target_userid);
}
