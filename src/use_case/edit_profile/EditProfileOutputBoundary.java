package use_case.edit_profile;

public interface EditProfileOutputBoundary {
    void prepareSuccessView(EditProfileOutputData editProfileOutputData);

    void prepareFailView(String error);
}
