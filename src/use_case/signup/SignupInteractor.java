package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("Username can't be empty.");
        }
        if (signupInputData.getNickname().isEmpty()) {
            userPresenter.prepareFailView("Nickname can't be empty.");
        }
        if (signupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password can't be empty.");
        }
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getNickname(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getNickname(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);}
    }
}