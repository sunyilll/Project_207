package main.java.use_case.send_message;

import main.java.entity.ChatChannel;

import java.nio.channels.Channel;

public class SendMessageInputData {
    final private String message;
    final private String user_id; //user id of sender
    final private ChatChannel channel;

    public SendMessageInputData(String message, String user_id, ChatChannel channel) {
        this.message = message;
        this.user_id = user_id;
        this.channel = channel;
    }

    String getMessage() {
        return message;
    }

    String getUserId() {
        return user_id;
    }

    ChatChannel getChannel() {
        return channel;
    }
}
