package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String nickname;



    final private String repeatPassword;

    public SignupInputData(String username, String nickname, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getNickname() {
        return nickname;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
