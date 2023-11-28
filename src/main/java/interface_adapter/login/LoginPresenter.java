package main.java.interface_adapter.login;

import main.java.interface_adapter.To_signup.ToSignupController;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.search.SearchState;
import main.java.interface_adapter.search.SearchViewModel;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.interface_adapter.To_signup.ToSignupViewModel;

import main.java.use_case.login.LoginOutputBoundary;
import main.java.use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final SearchViewModel searchViewModel;

    private final ToSignupViewModel toSignupViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ToSignupViewModel toSignupViewModel,
                          LoginViewModel loginViewModel,
                          SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.toSignupViewModel = toSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the search view.
        SearchState searchState = searchViewModel.getState();
        searchState.setUsername(response.getUserID());
        searchViewModel.setState(searchState);

        searchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println(1112);
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
