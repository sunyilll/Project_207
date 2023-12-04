package use_case.edit_profile;

import entity.User;

public class EditProfileInteractor implements EditProfileInputBoundary {
    final private EditProfileOutputBoundary presenter;
    final private EditProfileDataAccessInterface dataAccessObject;

    public EditProfileInteractor(EditProfileOutputBoundary presenter, EditProfileDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }
    @Override
    public void execute() {
        User user = dataAccessObject.getCurrentUser();
        if (user == null) {
            presenter.prepareFailView("User not logged in.");
            return;
        } else {
            EditProfileOutputData outputData = new EditProfileOutputData(user);
            presenter.prepareSuccessView(outputData);
        }
    }
}
