package use_case.go_to_signup;

public class ToSignupOutputData {
    private final String username;

    public ToSignupOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
