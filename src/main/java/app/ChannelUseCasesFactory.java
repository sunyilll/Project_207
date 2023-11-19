package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.send_message.SendMessageController;
import main.java.interface_adapter.send_message.SendMessagePresenter;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.use_case.send_message.SendMessageDataAccessInterface;
import main.java.use_case.send_message.SendMessageInputBoundary;
import main.java.use_case.send_message.SendMessageInteractor;
import main.java.use_case.send_message.SendMessageOutputBoundary;
import main.java.view.ChannelView;

import javax.swing.*;
import java.io.IOException;

public class ChannelUseCasesFactory {
    private ChannelUseCasesFactory(){}

    public static ChannelView create(
            ViewManagerModel viewManagerModel,
            SendMessageViewModel sendMessageViewModel,
            SendMessageDataAccessInterface sendMessageDataAccessObject
    ) {
        try {
            SendMessageController sendMessageController = createSendMessageUseCase(viewManagerModel, sendMessageViewModel,sendMessageDataAccessObject);
            return new ChannelView(sendMessageViewModel, sendMessageController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not send message");
        }
        return null;
    }




    private static SendMessageController createSendMessageUseCase(
            ViewManagerModel viewManagerModel,
            SendMessageViewModel sendMessageViewModel,
            SendMessageDataAccessInterface sendMessageDataAccessObject
    ) throws IOException {
        SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(sendMessageViewModel, viewManagerModel);
        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(sendMessageDataAccessObject, sendMessageOutputBoundary);
        return new SendMessageController(sendMessageInteractor);
    }
}

