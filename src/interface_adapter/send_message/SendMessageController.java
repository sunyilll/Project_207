package interface_adapter.send_message;

import entity.ChatChannel;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

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
