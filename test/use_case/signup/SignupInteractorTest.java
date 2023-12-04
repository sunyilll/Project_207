package use_case.signup;

import data_access.JsonUserDataAccessObject;
import entity.UserBuilder;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {
    JsonUserDataAccessObject signupUserDataAccessInterface = new JsonUserDataAccessObject("./users.json");
    UserBuilder userBuilder = new UserBuilder();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    SignupViewModel signupViewModel = new SignupViewModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    @Test
    void execute() {
        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInteractor signupInteractor = new SignupInteractor(signupUserDataAccessInterface, signupPresenter, userBuilder);
        SignupInputData goodSignupInputData = new SignupInputData("test1", "test1", "test1", "test1");
        SignupInputData badSignupInputData1 = new SignupInputData("", "test1", "test1", "test1");
        SignupInputData badSignupInputData2 = new SignupInputData("test1", "", "test1", "test1");
        SignupInputData badSignupInputData3 = new SignupInputData("test1", "test1", "", "test1");
        SignupInputData badSignupInputData4 = new SignupInputData("test1", "test1", "test1", "");

        try {
            signupInteractor.execute(goodSignupInputData);
            signupInteractor.execute(badSignupInputData1);
            signupInteractor.execute(badSignupInputData2);
            signupInteractor.execute(badSignupInputData3);
            signupInteractor.execute(badSignupInputData4);
        } catch (NullPointerException e){
            fail();
        }
    }
}