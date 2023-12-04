package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;

public class EditProfileController {
    final private EditProfileInputBoundary editProfileInteractor;

    public EditProfileController(EditProfileInputBoundary editProfileInteractor) {
        this.editProfileInteractor = editProfileInteractor;
    }

    public void execute() {
        editProfileInteractor.execute();
    }
}
