package interface_adapter.go_to_public_profile;

import use_case.go_to_public_profile.GoToPublicProfileInputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileInputData;

public class GoToPublicProfileController {
    final private GoToPublicProfileInputBoundary goToPublicProfileInteractor;

    public GoToPublicProfileController(GoToPublicProfileInputBoundary goToPublicProfileInteractor) {
        this.goToPublicProfileInteractor = goToPublicProfileInteractor;
    }

    public void execute(String targetUserid) {
        GoToPublicProfileInputData inputData = new GoToPublicProfileInputData(targetUserid);
        goToPublicProfileInteractor.execute(inputData);
    }
}
