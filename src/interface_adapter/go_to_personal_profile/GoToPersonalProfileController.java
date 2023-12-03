package interface_adapter.go_to_personal_profile;

import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;

public class GoToPersonalProfileController {
    final private GoToPersonalProfileInputBoundary goToPersonalProfileInteractor;

    public GoToPersonalProfileController(GoToPersonalProfileInputBoundary goToPersonalProfileInteractor) {
        this.goToPersonalProfileInteractor = goToPersonalProfileInteractor;
    }

    public void execute() {
        goToPersonalProfileInteractor.execute();
    }
}
