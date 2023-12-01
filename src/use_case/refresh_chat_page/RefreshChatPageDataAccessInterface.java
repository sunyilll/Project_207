package use_case.refresh_chat_page;

import kotlin.Pair;
import entity.ChatChannel;

import java.util.ArrayList;

public interface RefreshChatPageDataAccessInterface {
    ArrayList<Pair<String, String>> getMessageList(ChatChannel channel) throws RuntimeException;
}
