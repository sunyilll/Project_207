package main.java.interface_adapter.go_to_personal_profile;

import main.java.entity.User;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputData;

public class GoToPersonalProfileController {
    final GoToPersonalProfileInputBoundary goToPersonalProfileInteractor;

    public GoToPersonalProfileController(GoToPersonalProfileInputBoundary goToPersonalProfileInteractor) {
        this.goToPersonalProfileInteractor = goToPersonalProfileInteractor;
    }

    public void excute(User user) {
        GoToPersonalProfileInputData goToPersonalProfileInputData = new GoToPersonalProfileInputData(user);
        goToPersonalProfileInteractor.execute(goToPersonalProfileInputData);
    }
}
