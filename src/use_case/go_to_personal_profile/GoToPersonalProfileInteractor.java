package use_case.go_to_personal_profile;

import entity.User;

public class GoToPersonalProfileInteractor implements GoToPersonalProfileInputBoundary {
    final private GoToPersonalProfileOutputBoundary presenter;
    final private GoToPersonalProfileDataAccessInterface dataAccessObject;

    public GoToPersonalProfileInteractor(GoToPersonalProfileOutputBoundary presenter, GoToPersonalProfileDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }
    @Override
    public void execute() {
        User user = dataAccessObject.getCurrentUser();
        if (user == null) {
            presenter.prepareFailView("User not logged in.");
        } else {
            GoToPersonalProfileOutputData outputData = new GoToPersonalProfileOutputData(user);
            presenter.prepareSuccessView(outputData);
        }
    }
}
