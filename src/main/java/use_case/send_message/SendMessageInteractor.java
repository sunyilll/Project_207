package main.java.use_case.send_message;

import main.java.entity.ChatChannel;
import main.java.interface_adapter.send_message.SendMessagePresenter;

public class SendMessageInteractor implements SendMessageInputBoundary{
    final SendMessageDataAccessInterface sendMessageDataAccessObject;

    final SendMessageOutputBoundary sendMessagePresenter;

    public SendMessageInteractor(SendMessageDataAccessInterface sendMessageDataAccessInterface,
                                 SendMessageOutputBoundary sendMessageOutputBoundary) {
        this.sendMessageDataAccessObject = sendMessageDataAccessInterface;
        this.sendMessagePresenter = sendMessageOutputBoundary;
    }

    @Override
    public void execute(SendMessageInputData sendMessageInputData){
        //TODO
        String message = sendMessageInputData.getMessage();
        String user_id = sendMessageInputData.getUserId();
        ChatChannel channel = sendMessageInputData.getChannel();
        if (!sendMessageDataAccessObject.sendMessage(message, user_id, channel)) {
            SendMessagePresenter.prepareFailView("Failed to send.");
        } else {
            
        }
    }
}
