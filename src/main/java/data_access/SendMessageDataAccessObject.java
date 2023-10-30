package main.java.data_access;

import main.java.entity.ChatChannel;
import main.java.use_case.send_message.SendMessageDataAccessInterface;
import main.java.use_case.send_message.SendMessageInputData;

import java.io.IOException;

public class SendMessageDataAccessObject implements SendMessageDataAccessInterface {
    private String apiKey;
    private String applicationID;
    private Se

    public SendMessageDataAccessObject(String apiKey, String applicationID) throws IOException{
        this.apiKey = apiKey;
        this.applicationID = applicationID;


    }

    @Override
    public boolean sendMessage(String message, String user_id, ChatChannel channel) {


    }


}
