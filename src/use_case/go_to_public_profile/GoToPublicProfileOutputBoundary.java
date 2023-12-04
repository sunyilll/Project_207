package use_case.go_to_public_profile;

public interface GoToPublicProfileOutputBoundary {
    void prepareSuccessView(GoToPublicProfileOutputData goToPublicProfileOutputData);

    void prepareFailView(String error);
}
