package data_access;

import api.SendBirdAPI;
import entity.ChatChannel;
import use_case.send_message.SendMessageDataAccessInterface;

import java.io.IOException;

public class SendMessageDataAccessObject implements SendMessageDataAccessInterface {
    private String apiURL;
    private String applicationID;


    public SendMessageDataAccessObject(String apiURL, String applicationID) throws IOException{
        this.apiURL = apiURL;
        this.applicationID = applicationID;


    }

    @Override
    public boolean sendMessage(String message, String user_id, ChatChannel channel) {
        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
                "0ecfef313ab2989479b70e30e3ee37a1d105b770");
        try {
            return sendBirdAPIObject.sendMessage(user_id, message, channel);

        } catch (RuntimeException e) {
            return false;
        }

    }


}
