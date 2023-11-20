package main.java.use_case.refresh_chat_page;

import main.java.entity.ChatChannel;

public class RefreshChatPageInputData {
    final private ChatChannel channel;
    public RefreshChatPageInputData(ChatChannel channel){
        this.channel = channel;
    }

    public ChatChannel getChannel() {
        return channel;
    }
}
