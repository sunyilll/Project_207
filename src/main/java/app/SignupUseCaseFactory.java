package main.java.app;

import main.java.entity.UserFactory;
import main.java.interface_adapter.*;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupController;
import main.java.interface_adapter.signup.SignupPresenter;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.use_case.signup.SignupInputBoundary;
import main.java.use_case.signup.SignupInteractor;
import main.java.use_case.signup.SignupOutputBoundary;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import main.java.view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private SignupUseCaseFactory() {
    }

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            SignupUserDataAccessInterface userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory() {
        };

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
