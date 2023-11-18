package main.java.use_case.refresh_chat_page;

import java.util.ArrayList;

public class RefreshChatPageOutputData {
    private final ArrayList<String> messageList;
    public RefreshChatPageOutputData(ArrayList<String> messageList){
        this.messageList = messageList;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }
}
