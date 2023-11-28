package main.java.use_case.login;

public class LoginOutputData {

    private final String userID;
    private boolean useCaseFailed;

    public LoginOutputData(String userID, boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUserID() {
        return userID;
    }
}
