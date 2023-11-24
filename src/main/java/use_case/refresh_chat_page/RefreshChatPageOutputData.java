package main.java.use_case.refresh_chat_page;

import kotlin.Pair;

import java.util.ArrayList;

public class RefreshChatPageOutputData {
    private final ArrayList<Pair<String, String>> messageList;
    public RefreshChatPageOutputData(ArrayList<Pair<String, String>> messageList){
        this.messageList = messageList;
        System.out.println(this.messageList);
        System.out.println("OutputData");
    }

    public ArrayList<Pair<String, String>> getMessageList() {
        return messageList;
    }
}
