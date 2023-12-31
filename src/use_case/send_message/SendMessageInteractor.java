package use_case.send_message;

import entity.ChatChannel;

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
            sendMessagePresenter.prepareFailView("Failed to send.");
        } else {
            SendMessageOutputData sendMessageOutputData = new SendMessageOutputData("Sent successfully.");
            sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
        }
    }
}
