package use_case.login;

import data_access.JsonUserDataAccessObject;
import entity.UserBuilder;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class LoginInteractorTest {
    JsonUserDataAccessObject loginUserDataAccessInterface = new JsonUserDataAccessObject("./users.json");
    UserBuilder userBuilder = new UserBuilder();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    ToSignupViewModel toSignupViewModel = new ToSignupViewModel();
    SearchCourseViewModel searchCourseViewMode = new SearchCourseViewModel();
    @Test
    void execute() {
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, toSignupViewModel, loginViewModel, searchCourseViewMode);
        LoginInteractor loginInteractor = new LoginInteractor(loginUserDataAccessInterface,loginPresenter);
        LoginInputData goodLoginInputData = new LoginInputData("test1", "test1");
        LoginInputData badLoginInputData1 = new LoginInputData("test1", "ddd");
        try {
            loginUserDataAccessInterface.saveCurrentUser("test1");
            loginInteractor.execute(goodLoginInputData);
            loginInteractor.execute(badLoginInputData1);
        } catch (NullPointerException e){
            fail();
        }
    }
}
