package main.java.use_case.refresh_chat_page;

import kotlin.Pair;
import main.java.entity.ChatChannel;

import java.util.ArrayList;

public interface RefreshChatPageDataAccessInterface {
    ArrayList<Pair<String, String>> getMessageList(ChatChannel channel) throws RuntimeException;
}
