package interface_adapter.refresh_chat_page;

import interface_adapter.ViewManagerModel;
import use_case.refresh_chat_page.RefreshChatPageOutputBoundary;
import use_case.refresh_chat_page.RefreshChatPageOutputData;

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
        refreshChatPageState.setRefreshSuccessful(false);
        refreshChatPageState.setErrorMessage(error);
        refreshChatPageViewModel.firePropertyChanged();
    }
}
