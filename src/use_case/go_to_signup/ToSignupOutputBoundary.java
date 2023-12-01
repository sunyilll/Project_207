package use_case.go_to_signup;

public interface ToSignupOutputBoundary {
    void prepareSuccessView(ToSignupOutputData user);

    void prepareFailView(String error);

}