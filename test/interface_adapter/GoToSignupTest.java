package interface_adapter;

import interface_adapter.go_to_signup.ToSignupController;
import interface_adapter.go_to_signup.ToSignupPresenter;
import interface_adapter.go_to_signup.ToSignupState;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.go_to_signup.ToSignupOutputData;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class GoToSignupTest {
    private ToSignupController toSignupController;
    private ToSignupPresenter toSignupPresenter;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        toSignupPresenter = new ToSignupPresenter(viewManagerModel, new ToSignupViewModel(), signupViewModel, loginViewModel);
        toSignupController = new ToSignupController();
    }

    @Test
    void testTransitionToSignup() {
        toSignupController.execute();

        toSignupPresenter.prepareSuccessView(new ToSignupOutputData("exampleUsername"));

        SignupState signupState = signupViewModel.getState();
        assertEquals("exampleUsername", signupState.getUsername());
        assertEquals(signupViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void testPrepareFailView() {
        String expectedError = null;
        ToSignupPresenter presenter = new ToSignupPresenter(
                viewManagerModel,
                new ToSignupViewModel(),
                signupViewModel,
                loginViewModel
        );

        presenter.prepareFailView(expectedError);

        assertEquals(expectedError, signupViewModel.getState().getUsernameError());
    }

    @Test
    void testGetState() {
        ToSignupViewModel viewModel = new ToSignupViewModel();
        ToSignupState expectedState = new ToSignupState();
        viewModel.setState(expectedState);


        ToSignupState actualState = viewModel.getState();
        assertSame(expectedState, actualState);
    }


}


