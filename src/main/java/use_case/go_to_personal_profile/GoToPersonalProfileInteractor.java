package main.java.use_case.go_to_personal_profile;

public class GoToPersonalProfileInteractor implements GoToPersonalProfileInputBoundary {
    final private GoToPersonalProfileOutputBoundary presenter;

    public GoToPersonalProfileInteractor(GoToPersonalProfileOutputBoundary presenter) {
        this.presenter = presenter;
    }
    @Override
    public void execute(GoToPersonalProfileInputData inputData) {
        if (! inputData.isUserLoggedIn()) {
            presenter.prepareFailView("You are not logged in");
            return;
        }
        GoToPersonalProfileOutputData outputData = new GoToPersonalProfileOutputData(inputData.getUser());
        presenter.prepareSuccessView(outputData);
    }
}
