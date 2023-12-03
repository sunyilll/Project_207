package use_case.signup;

import entity.User;
import entity.UserBuilder;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserBuilder userBuilder;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserBuilder userBuilder) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userBuilder = userBuilder;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupInputData.getUserID().isEmpty()) {
            userPresenter.prepareFailView("Username can't be empty.");
        }
        if (signupInputData.getNickname().isEmpty()) {
            userPresenter.prepareFailView("Nickname can't be empty.");
        }
        if (signupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password can't be empty.");
        }
        if (userDataAccessObject.existsById(signupInputData.getUserID())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            userBuilder.create(signupInputData.getUserID(), signupInputData.getNickname(), signupInputData.getPassword());
            User user = userBuilder.getUser();
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUserID(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);}
    }
}