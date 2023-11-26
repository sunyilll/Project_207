package main.java.interface_adapter.login;

import main.java.interface_adapter.To_signup.ToSignupController;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.interface_adapter.To_signup.ToSignupViewModel;

import main.java.use_case.login.LoginOutputBoundary;
import main.java.use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ToSignupViewModel toSignupViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ToSignupViewModel toSignupViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.toSignupViewModel = toSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the signup view.
//        SignupState signupState = SignupViewModel.getState();
//        signupState.setUsername(response.getUsername());
//        this.signupViewModel.setState(signupState);
//        this.signupViewModel.firePropertyChanged();
//        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
