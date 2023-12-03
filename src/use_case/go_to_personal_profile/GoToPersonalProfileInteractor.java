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
    public void execute(GoToPersonalProfileInputData inputData) {
        if (! inputData.isUserLoggedIn()) {
            presenter.prepareFailView("You are not logged in");
            return;
        }
        User user = dataAccessObject.get(inputData.getUserId());
        GoToPersonalProfileOutputData outputData = new GoToPersonalProfileOutputData(user);
        presenter.prepareSuccessView(outputData);
    }
}
