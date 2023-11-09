package main.java.interface_adapter.send_message;

import main.java.entity.ChatChannel;

public class SendMessageState {
    //TODO
    private String messgae = "";
    private String messageError = null;
    private String user_id = "";
    private String user_id_error = null;
    private ChatChannel channel = new ChatChannel();
    private String channelError = null;
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
