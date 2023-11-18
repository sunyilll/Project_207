package main.java.use_case.refresh_chat_page;

import main.java.entity.ChatChannel;

public interface RefreshChatPageInputBoundary {
    void execute(RefreshChatPageInputData refreshChatPageInputData);
}
