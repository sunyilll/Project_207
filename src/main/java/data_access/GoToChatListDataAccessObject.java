package main.java.data_access;

import main.java.api.SendBirdAPI;
import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class GoToChatListDataAccessObject implements GoToChatListDataAccessInterface {
    public GoToChatListDataAccessObject() throws IOException {
    }

    @Override
    public ArrayList<ChatChannel> getAllChatChannels(User user) throws RuntimeException {
        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
                "0ecfef313ab2989479b70e30e3ee37a1d105b770");
        try {
            return sendBirdAPIObject.getAllChatChannels(user.getUserID());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
