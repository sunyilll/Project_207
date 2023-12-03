package interface_adapter.go_to_personal_profile;

import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInputData;

public class GoToPersonalProfileController {
    private final GoToPersonalProfileInputBoundary goToPersonalProfileInteractor;

    public GoToPersonalProfileController(GoToPersonalProfileInputBoundary goToPersonalProfileInteractor) {
        this.goToPersonalProfileInteractor = goToPersonalProfileInteractor;
    }


    public void execute(String userid) {
        GoToPersonalProfileInputData goToPersonalProfileInputData = new GoToPersonalProfileInputData(userid);
        goToPersonalProfileInteractor.execute(goToPersonalProfileInputData);
    }
}
