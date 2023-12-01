package use_case.To_signup;

public interface ToSignupOutputBoundary {
    void prepareSuccessView(ToSignupOutputData user);


    void prepareFailView(String error);

}