package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageController;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPagePresenter;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageState;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import main.java.interface_adapter.send_message.SendMessageController;
import main.java.interface_adapter.send_message.SendMessagePresenter;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.use_case.refresh_chat_page.RefreshChatPageDataAccessInterface;
import main.java.use_case.refresh_chat_page.RefreshChatPageInputBoundary;
import main.java.use_case.refresh_chat_page.RefreshChatPageInteractor;
import main.java.use_case.refresh_chat_page.RefreshChatPageOutputBoundary;
import main.java.use_case.send_message.SendMessageDataAccessInterface;
import main.java.use_case.send_message.SendMessageInputBoundary;
import main.java.use_case.send_message.SendMessageInteractor;
import main.java.use_case.send_message.SendMessageOutputBoundary;
import main.java.view.ChannelView.ChannelView;

import javax.swing.*;
import java.io.IOException;

public class ChannelUseCasesFactory {
    private ChannelUseCasesFactory(){}

    public static ChannelView create(
            ViewManagerModel viewManagerModel,
            SendMessageViewModel sendMessageViewModel,
            SendMessageDataAccessInterface sendMessageDataAccessObject,
            RefreshChatPageViewModel refreshChatPageViewModel,
            RefreshChatPageDataAccessInterface refreshChatPageDataAccessObject
    ) {
        try {
            SendMessageController sendMessageController = createSendMessageUseCase(viewManagerModel, sendMessageViewModel,sendMessageDataAccessObject);
            RefreshChatPageController refreshChatPageController = createRefreshUseCase(viewManagerModel, refreshChatPageViewModel, refreshChatPageDataAccessObject);
            return new ChannelView(sendMessageViewModel, sendMessageController, refreshChatPageViewModel, refreshChatPageController);
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


    private static RefreshChatPageController createRefreshUseCase(
            ViewManagerModel viewManagerModel,
            RefreshChatPageViewModel refreshChatPageViewModel,
            RefreshChatPageDataAccessInterface refreshChatPageDataAccessobject
    ) throws IOException {
        RefreshChatPageOutputBoundary refreshChatPageOutputBoundary = new RefreshChatPagePresenter(refreshChatPageViewModel, viewManagerModel);
        RefreshChatPageInputBoundary refreshChatPageInteractor = new RefreshChatPageInteractor(refreshChatPageDataAccessobject, refreshChatPageOutputBoundary);
        return new RefreshChatPageController(refreshChatPageInteractor);
    }
}

