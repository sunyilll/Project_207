package data_access;

import kotlin.Pair;
import api.SendBirdAPI;
import entity.ChatChannel;
import use_case.refresh_chat_page.RefreshChatPageDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class RefreshChatPageDataAccessObject implements RefreshChatPageDataAccessInterface {

    public RefreshChatPageDataAccessObject() {
    }

    @Override
    public ArrayList<Pair<String, String>> getMessageList(ChatChannel channel) {
        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
                "0ecfef313ab2989479b70e30e3ee37a1d105b770");
        try {
            return sendBirdAPIObject.getMessageListFromNovTenth(channel);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
