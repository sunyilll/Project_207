package main.java.interface_adapter.To_signup;

import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ToSignupViewModel {
    public static final String TOSIGNUP_BUTTON_LABEL = "To sign up";

    private static ToSignupState state = new ToSignupState();

    public static ToSignupState getState() {
        return state;
    }

}
