package main.java.app;

import main.java.entity.UserFactory;
import main.java.interface_adapter.To_signup.ToSignupPresenter;
import main.java.interface_adapter.To_signup.ToSignupViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.login.LoginController;
import main.java.interface_adapter.login.LoginPresenter;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.signup.SignupViewModel;

import main.java.interface_adapter.To_signup.ToSignupPresenter;

import main.java.interface_adapter.To_signup.ToSignupController;

import main.java.use_case.To_signup.ToSignupInputBoundary;
import main.java.use_case.To_signup.ToSignupInteractor;
import main.java.use_case.To_signup.ToSignupOutputBoundary;
import main.java.use_case.login.LoginInputBoundary;
import main.java.use_case.login.LoginInteractor;
import main.java.use_case.login.LoginOutputBoundary;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;




import main.java.view.LoginView;

import javax.swing.*;
import java.io.IOException;


public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ToSignupViewModel toSignupViewModel,
            SignupViewModel signupViewModel,
            SearchCourseViewModel searchCourseViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupUserDataAccessInterface signupUserDataAccessInterface) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, toSignupViewModel, searchCourseViewModel,loginViewModel, userDataAccessObject);
            ToSignupController toSignupController = createUserToSignupUseCase(viewManagerModel, toSignupViewModel, signupViewModel,loginViewModel, signupUserDataAccessInterface);

            return new LoginView(loginViewModel, toSignupViewModel, signupViewModel, viewManagerModel, loginController, toSignupController, searchCourseViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            ToSignupViewModel toSignupViewModel,
            SearchCourseViewModel searchCourseViewModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,toSignupViewModel,loginViewModel, searchCourseViewModel);

        UserFactory userFactory = new UserFactory() {
        };

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
    private static ToSignupController createUserToSignupUseCase(ViewManagerModel viewManagerModel, ToSignupViewModel toSignupViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface signupUserDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ToSignupOutputBoundary toSignupOutputBoundary = new ToSignupPresenter(viewManagerModel, toSignupViewModel, signupViewModel, loginViewModel );

        UserFactory userFactory = new UserFactory() {
        };

        ToSignupInputBoundary userToSignupInteractor = new ToSignupInteractor();

        return new ToSignupController();
    }
}
