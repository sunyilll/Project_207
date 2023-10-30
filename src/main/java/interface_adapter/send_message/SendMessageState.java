package main.java.interface_adapter.send_message;

public class SendMessageState {
    private boolean messageSentSuccessful = false;

    private String errorMessage = null;

    public SendMessageState(SendMessageState copy) {
        this.messageSentSuccessful = copy.messageSentSuccessful;
        this.errorMessage = copy.errorMessage;
    }

    public SendMessageState() {
    }

    public boolean isMessageSentSuccessful() {
        return messageSentSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setMessageSentSuccessful(boolean messageSentSuccessful) {
        this.messageSentSuccessful = messageSentSuccessful;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "SendMessageState{" +
                "messageSentSuccessful=" + messageSentSuccessful +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
