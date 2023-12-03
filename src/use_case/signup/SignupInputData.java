package use_case.signup;

public class SignupInputData {

    final private String userid;
    final private String password;
    final private String nickname;

    final private String repeatPassword;

    public SignupInputData(String userid, String nickname, String password, String repeatPassword) {
        this.userid = userid;
        this.password = password;
        this.nickname = nickname;
        this.repeatPassword = repeatPassword;
    }

    String getUserID() {
        return userid;
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
