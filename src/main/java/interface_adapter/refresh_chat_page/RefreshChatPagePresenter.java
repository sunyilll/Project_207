package main.java.interface_adapter.refresh_chat_page;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.refresh_chat_page.RefreshChatPageOutputBoundary;
import main.java.use_case.refresh_chat_page.RefreshChatPageOutputData;

public class RefreshChatPagePresenter implements RefreshChatPageOutputBoundary {
    private final RefreshChatPageViewModel refreshChatPageViewModel;
    private ViewManagerModel viewManagerModel;
    public RefreshChatPagePresenter(RefreshChatPageViewModel refreshChatPageViewModel, ViewManagerModel viewManagerModel) {
        this.refreshChatPageViewModel = refreshChatPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(RefreshChatPageOutputData messageList) {
        RefreshChatPageState refreshChatPageState = refreshChatPageViewModel.getState();
        refreshChatPageState.setRefreshSuccessful(true);
        refreshChatPageState.setMessageList(messageList.getMessageList());
        this.refreshChatPageViewModel.setState(refreshChatPageState);
        refreshChatPageViewModel.firePropertyChanged();
    }
    public void prepareFailView(String error) {
        RefreshChatPageState refreshChatPageState = refreshChatPageViewModel.getState();
        refreshChatPageState.setErrorMessage(error);
        refreshChatPageViewModel.firePropertyChanged();
    }
}
