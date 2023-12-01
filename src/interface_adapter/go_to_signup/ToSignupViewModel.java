package interface_adapter.go_to_signup;

public class ToSignupViewModel {
    public static final String TOSIGNUP_BUTTON_LABEL = "To sign up";

    private static ToSignupState state = new ToSignupState();

    public static ToSignupState getState() {
        return state;
    }

}
