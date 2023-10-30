package main.java.use_case.send_message;

public class SendMessageOutputData {

    private final String message;

    public SendMessageOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
