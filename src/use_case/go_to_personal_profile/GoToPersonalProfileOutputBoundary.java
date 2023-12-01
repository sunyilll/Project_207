package use_case.go_to_personal_profile;

public interface GoToPersonalProfileOutputBoundary {
    void prepareSuccessView(GoToPersonalProfileOutputData goToPersonalProfileOutputData);

    void prepareFailView(String error);
}
