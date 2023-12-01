package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String nickname = "";
    private String nicknameError = null;
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String gender = "";
    private String genderError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    public SignupState(SignupState copy) {
        username = copy.username;
        nickname = copy.nickname;
        nicknameError = copy.nicknameError;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        gender = copy.gender;
        genderError = copy.genderError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }

    public String getUsername() {
        return username;
    }
    public String getGender() {
        return null;
    }

    public String getGenderError() {
        return genderError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNicknameError() {
        return nicknameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNicknameError(String nicknameError) {
        this.nicknameError = nicknameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
