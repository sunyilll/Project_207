package main.java.interface_adapter.signup;

import main.java.use_case.signup.SignupInputBoundary;
import main.java.use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String nickname, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, nickname, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
