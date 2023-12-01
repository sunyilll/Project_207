package use_case.send_message;

import entity.ChatChannel;

public interface SendMessageDataAccessInterface {
    boolean sendMessage(String message, String user_id, ChatChannel channel); //同时send message以及return有没有成功sent
}
