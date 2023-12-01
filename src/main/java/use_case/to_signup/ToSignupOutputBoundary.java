package main.java.use_case.to_signup;

public interface ToSignupOutputBoundary {
    void prepareSuccessView(ToSignupOutputData user);


    void prepareFailView(String error);

}