package app;

import entity.UserFactory;
import interface_adapter.To_signup.ToSignupPresenter;
import interface_adapter.To_signup.ToSignupViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.signup.SignupViewModel;

import interface_adapter.To_signup.ToSignupController;

import use_case.To_signup.ToSignupInputBoundary;
import use_case.To_signup.ToSignupInteractor;
import use_case.To_signup.ToSignupOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;




import view.LoginView;

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
