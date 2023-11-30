package main.java.app;

import main.java.data_access.GoToChatListDataAccessObject;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageController;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPagePresenter;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageState;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import main.java.interface_adapter.send_message.SendMessageController;
import main.java.interface_adapter.send_message.SendMessagePresenter;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListInputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListInteractor;
import main.java.use_case.go_to_chat_list.GoToChatListOutputBoundary;
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
            RefreshChatPageDataAccessInterface refreshChatPageDataAccessObject,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject
    ) {
        try {
            SendMessageController sendMessageController = createSendMessageUseCase(viewManagerModel, sendMessageViewModel,sendMessageDataAccessObject);
            RefreshChatPageController refreshChatPageController = createRefreshUseCase(viewManagerModel, refreshChatPageViewModel, refreshChatPageDataAccessObject);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel, goToChatListViewModel, goToChatListDataAccessObject);

            return new ChannelView(sendMessageViewModel, sendMessageController, refreshChatPageViewModel, refreshChatPageController, goToChatListViewModel, goToChatListController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not send message");
        }
        return null;
    }

    private static GoToChatListController createGoToChatListUseCase(
            ViewManagerModel viewManagerModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject
    ) throws IOException {
        GoToChatListOutputBoundary goToChatListOutputBoundary = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
        GoToChatListInputBoundary goToChatListInteractor = new GoToChatListInteractor(goToChatListDataAccessObject, goToChatListOutputBoundary);
        return new GoToChatListController(goToChatListInteractor);
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

