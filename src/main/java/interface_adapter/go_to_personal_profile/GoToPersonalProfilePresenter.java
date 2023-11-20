package main.java.interface_adapter.go_to_personal_profile;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputData;

public class GoToPersonalProfilePresenter implements GoToPersonalProfileOutputBoundary {
    @Override
    public void prepareSuccessView(GoToPersonalProfileOutputData goToPersonalProfileOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
