package main.java.interface_adapter.send_message;

import main.java.entity.ChatChannel;
import main.java.use_case.send_message.SendMessageInputBoundary;
import main.java.use_case.send_message.SendMessageInputData;

public class SendMessageController {
    final SendMessageInputBoundary sendMessageUseCaseInteractor;
    public SendMessageController(SendMessageInputBoundary sendMessageUseCaseInteractor) {
        this.sendMessageUseCaseInteractor = sendMessageUseCaseInteractor;
    }
    public void execute(String message, String user_id, ChatChannel channel) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(message, user_id, channel);
        sendMessageUseCaseInteractor.execute(sendMessageInputData);
    }
}
