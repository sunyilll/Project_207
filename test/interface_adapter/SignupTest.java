package interface_adapter;

import entity.User;
import entity.UserBuilder;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupUserDataAccessInterface;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SignupTest {

    private SignupController signupController;
    private SignupPresenter signupPresenter;
    private SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupUserDataAccessInterface userDataAccessObject;
    private UserBuilder userBuilder;
    private SignupState signupState;
    private boolean propertyChanged;


    @BeforeEach
    void setUp() {
        signupState = new SignupState();
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        propertyChanged = true;

        userDataAccessObject = new MockSignupUserDataAccess();
        userBuilder = new UserBuilder();

        signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary userSignupUseCaseInteractor = new SignupInteractor(userDataAccessObject, signupPresenter, userBuilder);

        signupController = new SignupController(userSignupUseCaseInteractor);


    }

    @Test
    void testSignupSuccess() {
        String username = "newUser";
        String nickname = "newNickname";
        String password1 = "password";
        String password2 = "password";

        signupController.execute(username, nickname, password1, password2);

        assertTrue(signupViewModel.getState().isSignupSuccessful());
        assertNull(signupViewModel.getState().getUsernameError());
        assertNull(signupViewModel.getState().getNicknameError());
        assertNull(signupViewModel.getState().getPasswordError());
        assertNull(signupViewModel.getState().getRepeatPasswordError());
    }

    @Test
    void testSignupFailure() {
        String username = "newUser";
        String nickname = "newNickname";
        String password1 = "password";
        String password2 = "differentPassword";

        signupController.execute(username, nickname, password1, password2);

        assertFalse(signupViewModel.getState().isSignupSuccessful());
    }

    @Test
    void gettersAndSettersTest() {
        signupState.setUsername("testUsername");
        assertEquals("testUsername", signupState.getUsername());

        signupState.setNickname("testNickname");
        assertEquals("testNickname", signupState.getNickname());

        signupState.setPassword("testPassword");
        assertEquals("testPassword", signupState.getPassword());

        signupState.setRepeatPassword("testRepeatPassword");
        assertEquals("testRepeatPassword", signupState.getRepeatPassword());

        signupState.setUsernameError("username error");
        assertEquals("username error", signupState.getUsernameError());

        signupState.setNicknameError("nickname error");
        assertEquals("nickname error", signupState.getNicknameError());

        signupState.setPasswordError("password error");
        assertEquals("password error", signupState.getPasswordError());

        signupState.setRepeatPasswordError("repeat password error");
        assertEquals("repeat password error", signupState.getRepeatPasswordError());

        signupState.setSignupSuccessful(true);
        assertTrue(signupState.isSignupSuccessful());
    }

    @Test
    void copyConstructorTest() {
        SignupState originalState = new SignupState();
        originalState.setUsername("testUsername");
        originalState.setNickname("testNickname");
        originalState.setPassword("testPassword");
        originalState.setRepeatPassword("testRepeatPassword");
        originalState.setUsernameError("username error");
        originalState.setNicknameError("nickname error");
        originalState.setPasswordError("password error");
        originalState.setRepeatPasswordError("repeat password error");
        originalState.setSignupSuccessful(true);

        // Use the copy constructor
        SignupState copiedState = new SignupState(originalState);

        // Verify that the copied state has the same values as the original
        assertEquals(originalState.getUsername(), copiedState.getUsername());
        assertEquals(originalState.getNickname(), copiedState.getNickname());
        assertEquals(originalState.getPassword(), copiedState.getPassword());
        assertEquals(originalState.getRepeatPassword(), copiedState.getRepeatPassword());
        assertEquals(originalState.getUsernameError(), copiedState.getUsernameError());
        assertEquals(originalState.getNicknameError(), copiedState.getNicknameError());
        assertEquals(originalState.getPasswordError(), copiedState.getPasswordError());
        assertEquals(originalState.getRepeatPasswordError(), copiedState.getRepeatPasswordError());
        assertEquals(originalState.isSignupSuccessful(), copiedState.isSignupSuccessful());
    }

    @Test
    void toStringTest() {
        // Set some values
        signupState.setUsername("testUsername");
        signupState.setNickname("testNickname");
        signupState.setPassword("testPassword");
        signupState.setRepeatPassword("testRepeatPassword");

        // Check the toString method
        String expectedString = "SignupState{" +
                "username='testUsername', " +
                "nickname='testNickname', " +
                "password='testPassword', " +
                "repeatPassword='testRepeatPassword'}";
        assertEquals(expectedString, signupState.toString());
    }

    @Test
    void testAddPropertyChangeListener() {
        SignupViewModel signupViewModel = new SignupViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        signupViewModel.addPropertyChangeListener(listener);

        SignupState initialState = new SignupState();
        initialState.setUsername("oldUsername");
        signupViewModel.setState(initialState);

        SignupState newState = new SignupState();
        newState.setUsername("newUsername");
        signupViewModel.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }

    // Mock implementation for testing purposes
    public class MockSignupUserDataAccess implements SignupUserDataAccessInterface {
        private Map<String, User> users = new HashMap<>();

        @Override
        public boolean existsById(String identifier) {
            // This method should return true if the identifier matches an existing user.
            return false;
        }

        @Override
        public void save(User user) {
            users.put(user.getUserID(), user);
        }
    }

}
