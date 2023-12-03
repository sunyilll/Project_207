package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelPresenter;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.refresh_chat_page.RefreshChatPageController;
import interface_adapter.refresh_chat_page.RefreshChatPagePresenter;
import interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;
import use_case.go_to_channel.GoToChannelDataAccessInterface;
import use_case.go_to_channel.GoToChannelInputBoundary;
import use_case.go_to_channel.GoToChannelInteractor;
import use_case.go_to_channel.GoToChannelOutputBoundary;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInputBoundary;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.refresh_chat_page.RefreshChatPageDataAccessInterface;
import use_case.refresh_chat_page.RefreshChatPageInputBoundary;
import use_case.refresh_chat_page.RefreshChatPageInteractor;
import use_case.refresh_chat_page.RefreshChatPageOutputBoundary;
import use_case.send_message.SendMessageDataAccessInterface;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import view.ChannelView.ChannelView;

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
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            GoToChannelViewModel goToChannelViewModel
    ) {
        try {
            SendMessageController sendMessageController = createSendMessageUseCase(viewManagerModel, sendMessageViewModel,sendMessageDataAccessObject);
            RefreshChatPageController refreshChatPageController = createRefreshUseCase(viewManagerModel, refreshChatPageViewModel, refreshChatPageDataAccessObject);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel, goToChatListViewModel, goToChatListDataAccessObject);
            GoToChannelController goToChannelController = createGoToChannelUseCase(viewManagerModel, goToChannelViewModel);

            return new ChannelView(sendMessageViewModel, sendMessageController, refreshChatPageViewModel, refreshChatPageController, goToChatListViewModel, goToChatListController, viewManagerModel, goToChannelViewModel, goToChannelController);
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

    private static GoToChannelController createGoToChannelUseCase(
            ViewManagerModel viewManagerModel,
            GoToChannelViewModel goToChannelViewModel
    ) throws IOException {
        GoToChannelOutputBoundary goToChannelOutputBoundary = new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);
        GoToChannelInputBoundary goToChannelInteractor = new GoToChannelInteractor(goToChannelOutputBoundary);
        return new GoToChannelController(goToChannelInteractor);
    }
}

