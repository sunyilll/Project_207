package use_case.save_profile;

import entity.User;

public interface SaveProfileDataAccessInterface {
   void save(User user);
   User getCurrentUser();
}
