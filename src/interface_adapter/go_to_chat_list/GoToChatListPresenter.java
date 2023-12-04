package interface_adapter.go_to_chat_list;

import interface_adapter.ViewManagerModel;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.go_to_chat_list.GoToChatListOutputData;

public class GoToChatListPresenter implements GoToChatListOutputBoundary {
    private final GoToChatListViewModel goToChatListViewModel;
    private ViewManagerModel viewManagerModel;
    public GoToChatListPresenter(GoToChatListViewModel GoToChatListViewModel, ViewManagerModel viewManagerModel) {
        this.goToChatListViewModel = GoToChatListViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(GoToChatListOutputData goToChatListOutputData) {
        GoToChatListState GoToChatListState = goToChatListViewModel.getState();
        GoToChatListState.setSuccess(true);
        GoToChatListState.setChatChannels(goToChatListOutputData.getChatChannels());
        GoToChatListState.setUser(goToChatListOutputData.getUser());

        this.goToChatListViewModel.setState(GoToChatListState);
        goToChatListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("chatList");
        viewManagerModel.firePropertyChanged();
        System.out.println("I am at step 5");
        System.out.println(viewManagerModel.getActiveView());
    }

    public void prepareFailView(String error) {
        GoToChatListState GoToChatListState = goToChatListViewModel.getState();
        GoToChatListState.setErrorMessage(error);
        GoToChatListState.setSuccess(false);
        this.goToChatListViewModel.setState(GoToChatListState);
        goToChatListViewModel.firePropertyChanged();
    }
}
