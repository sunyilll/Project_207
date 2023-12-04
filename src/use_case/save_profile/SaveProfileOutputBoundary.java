package use_case.save_profile;

public interface SaveProfileOutputBoundary {
    void prepareSuccessView(SaveProfileOutputData saveProfileOutputData);

    void prepareFailView(String error);
}
