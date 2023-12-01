package interface_adapter.To_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.To_signup.ToSignupOutputBoundary;
import use_case.To_signup.ToSignupOutputData;

public class ToSignupPresenter implements ToSignupOutputBoundary {

    private final ToSignupViewModel toSignupViewModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public ToSignupPresenter(ViewManagerModel viewManagerModel,
                           ToSignupViewModel toSignupViewModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.toSignupViewModel = toSignupViewModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ToSignupOutputData response) {
        SignupState signupState = SignupViewModel.getState();
        signupState.setUsername(response.getUsername());
        this.signupViewModel.setState(signupState);
        this.signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
