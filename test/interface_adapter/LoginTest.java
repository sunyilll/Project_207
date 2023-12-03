package interface_adapter;

import entity.User;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAccessInterface;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginInteractor loginInteractor;
    private LoginPresenter loginPresenter;
    private LoginViewModel loginViewModel;
    private LoginUserDataAccessInterface userDataAccessObject;
    private ViewManagerModel viewManagerModel;
    private ToSignupViewModel toSignupViewModel;
    private SearchCourseViewModel searchCourseViewModel;
    private LoginController loginController;
    private LoginOutputBoundary loginOutputBoundary;
    private LoginState loginState;
    private LoginState copyLoginState;
    private boolean propertyChanged = true;

    @BeforeEach
    void setUp() {
        loginState = new LoginState();
        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
        userDataAccessObject = new MockLoginUserDataAccess();
        searchCourseViewModel = new SearchCourseViewModel();
        toSignupViewModel = new ToSignupViewModel();

        loginPresenter = new LoginPresenter(viewManagerModel, toSignupViewModel, loginViewModel, searchCourseViewModel);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginController = new LoginController(loginInteractor);

    }

    @Test
    void testPrepareSuccessView() {
        // Create a mock response that represents a successful login
        LoginOutputData response = new LoginOutputData("userId123", false);


        loginPresenter.prepareSuccessView(response);

        assertTrue(loginViewModel.getState().isLoginSuccessful());

        assertEquals("userId123", searchCourseViewModel.getState().getUserID());

        assertEquals(searchCourseViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void testLoginFailure() {
        String username = "invalidUser";
        String password = "invalidPassword";

        loginController.execute(username, password);
        assertFalse(loginViewModel.getState().isLoginSuccessful());

    }

    @Test
    void gettersAndSettersTest() {
        LoginState state = new LoginState();
        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername());

        state.setPassword("testPass");
        assertEquals("testPass", state.getPassword());

        state.setUsernameError("error");
        assertEquals("error", state.getUsernameError());

        state.setPasswordError("passError");
        assertEquals("passError", state.getPasswordError());

        state.setLoginSuccessful(true);
        assertTrue(state.isLoginSuccessful());
    }

    @Test
    void copyConstructorTest() {
        LoginState original = new LoginState();
        original.setUsername("originalUser");
        original.setPassword("originalPass");
        original.setUsernameError("originalError");
        original.setPasswordError("originalPassError");
        original.setLoginSuccessful(true);

        LoginState copy = new LoginState(original);
        assertEquals("originalUser", copy.getUsername());
        assertEquals("originalPass", copy.getPassword());
        assertEquals("originalError", copy.getUsernameError());
        assertEquals("originalPassError", copy.getPasswordError());
        assertTrue(copy.isLoginSuccessful());
    }


    @Test
    void testAddPropertyChangeListener() {
        LoginViewModel loginViewModel = new LoginViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        loginViewModel.addPropertyChangeListener(listener);

        LoginState initialState = new LoginState();
        initialState.setUsername("oldUsername");
        loginViewModel.setState(initialState);

        LoginState newState = new LoginState();
        newState.setUsername("newUsername");
        loginViewModel.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }

    public class MockLoginUserDataAccess implements LoginUserDataAccessInterface {
        private Map<String, User> users = new HashMap<>();

        public void addUser(User user) {
            users.put(user.getUserID(), user);
        }

        public boolean existsById(String username) {
            return users.containsKey(username);
        }

        public User get(String username) {
            return users.get(username);
        }

        public boolean checkPassword(String username, String password) {
            User user = get(username);
            return user != null && user.getPassword().equals(password);
        }
    }
}
