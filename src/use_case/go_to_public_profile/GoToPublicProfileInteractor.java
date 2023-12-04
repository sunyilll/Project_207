package use_case.go_to_public_profile;

import entity.User;


public class GoToPublicProfileInteractor implements GoToPublicProfileInputBoundary {
    final private GoToPublicProfileOutputBoundary presenter;
    final private GoToPublicProfileDataAccessInterface dataAccessObject;

    public GoToPublicProfileInteractor(GoToPublicProfileOutputBoundary presenter,
                                       GoToPublicProfileDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }
    @Override
    public void execute(GoToPublicProfileInputData inputData) {
        User user = dataAccessObject.get(inputData.getTargetUserid());
        if (user == null) {
            presenter.prepareFailView("User not logged in.");
            return;
        } else {
            GoToPublicProfileOutputData outputData = new GoToPublicProfileOutputData(user);
            presenter.prepareSuccessView(outputData);
        }
    }
}